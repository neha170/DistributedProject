package ttcParallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class Agent implements Runnable{
	int numAgents = 3;
	int portNum;
	public boolean active;
	boolean inCycle;
	boolean isRoot = false;
	Agent successor;
	CyclicBarrier barrier;
	int nextPref;
	Thread[] threads;
	String color;
	static int maxLen;
	int d;

	public void setBarrier(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	static ArrayList<Agent> agents;
	
	Agent next;
	boolean assigned;
	int house;
	static HashMap<Integer, Agent> pref = new HashMap<Integer, Agent>();
	ArrayList<Integer> preference = new ArrayList<Integer>();
	Agent parent;
	
	public Agent getParent() {
		return parent;
	}

	public void setParent(Agent parent) {
		this.parent = parent;
	}

	public Agent getSuccessor() {
		return successor;
	}

	public void setSuccessor(Agent successor) {
		this.successor = successor;
	}

	HashSet<Agent> children;
	
	public Agent(int portNum, ArrayList<Integer> preference, int house) {
		this.portNum = portNum;
		this.active = true;
		this.inCycle = false;
		this.children = new HashSet<Agent>();
		this.preference = preference;
		this.house = house;
	}
	
	public static void setPref(HashMap<Integer, Agent> map) {
		for (Integer k : map.keySet()) {
			Agent.pref.put(k, map.get(k));
		}
	}
		
	public void findCycle() {
		while (active == true) {
			// Graph Coloring step:
			if (this.d < successor.d) {
				this.active = false;
			}
			
			// Explore step
			if (active == true) {
				boolean succActive = this.successor.active; // Get active status from successor
				while (succActive == false) {
					children.add(successor);
					successor.setParent(this);
					this.setSuccessor(this.successor.getSuccessor());
					succActive = this.successor.active;
					if (this.successor == this) {
						break;
					}
					if(this.successor.assigned == true) {
						// I have not found any cycles, I should exit the stage
						this.children.clear();
						active = false;
						Request req = new Request(this.portNum, -1, 1);
						parent.receiveOk(req);
						return;
					}
				}
				if (this.successor == this) {
					children.remove(this); // remove itself from the set of children
					this.active = false;
				}
			}
		}

		// Notify step
		if (this.successor == this) {
			this.inCycle = true;
			for (Agent child: this.children) {
				// send cycle to child
				child.receiveCycle();
			}
		}
	}
	
	// only run for root node (env)
	public void envFn() {
		for (Agent a:agents) {
			this.children.add(a);
			a.setParent(this);
		}
		threads = new Thread[numAgents];
		for(int i=0; i<numAgents; i++) {
			threads[i] = new Thread(Agent.agents.get(i));
			threads[i].start();
		}
		
		// Joining threads
		for(int i=0; i<numAgents; i++) {
        	try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
	}
	
	@Override
	public void run() {
		if (isRoot == true) {
			this.envFn();
		} else {
			boolean keepRunning = true;
			while(keepRunning) {
				this.receiveNextStage();
				this.setD();
				try {
					barrier.await(2L, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
				} catch (BrokenBarrierException e) {
				} catch (TimeoutException e) {
				}
				this.startStage();
				keepRunning = false;
				try {
					Thread.sleep(15000); //Synchronous system of messaging is assumed
				} catch (InterruptedException e) {
					keepRunning = true;
				}
			}	
		}
	}

	public void startStage() {
		this.successor = next;
		//Execute cycle algo
		this.findCycle();
		if (this.inCycle == true) { //parent
			this.changeInCycle();
		}
	}
	
	public void changeInCycle() {
		this.house = this.nextPref;
		this.assigned = true;
		// For parallel, do remove only once
		this.removePref(this.house);
	}
	
	public void receiveCycle() {
		this.inCycle = true;
		this.changeInCycle();
		for (Agent c: this.children) {
			c.receiveCycle();
		}
		return;
	}
	
	public void receiveNextStage() {
		if (this.assigned == false) {
			this.active = true;
			for (int i =0;i<preference.size();i++) {
				int top = preference.get(i);
				if (pref.containsKey(top)) {
					this.next = pref.get(top);
					this.nextPref = top;
					break;
				}
			}
		}
		return;
	}
	
	public void receiveOk(Request req) {
		if (isRoot == true) {
			for(int i=0; i<threads.length; i++) {
				threads[i].interrupt();
			}
		}
	}

	public void removePref(int house) {
		if (pref.containsKey(house)) {
			Agent.pref.remove(house);
		}
	}

	void paint() {
		//set your own color
		color = Integer.toBinaryString(this.portNum);
		int len0 = Agent.maxLen-color.length();
		char[] chars = new char[len0];
		Arrays.fill(chars, '0');
		String s = new String(chars);
		color = s+color;
	}
	
	void setD() {
		int c = 0;
		String color2 = next.color;
		int len = color.length();
		for(int i=0; i<len; i++) {
			if(color2.charAt(len-i-1)!=color.charAt(len-i-1)) {
				c = i;
				break;
			}
		}
		this.d = 2*c + (int)color.charAt(len-c-1);
	}
}
