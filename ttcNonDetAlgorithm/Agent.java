package ttcNonDetAlgorithm;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Agent implements AgentRMI, Runnable{
	// To count the total number of messages in a run
	static int NUM_MSGS = 0;
	
	int numAgents = 3;
	int[] ports;
	String[] peers;
	AgentRMI stub;
	Registry registry;
	
	public boolean active;
	public int portNum;
	boolean inCycle;
	boolean alreadySent = false;
	boolean isRoot = false;
	boolean coinValue;
	Agent successor;
	CyclicBarrier barrier;
	int nextPref;
	Thread[] threads;

	public void setBarrier(CyclicBarrier barrier) {
		this.barrier = barrier;
	}
	
	public void setCoinValue() {
		double num = Math.random();
		if (num < 0.5) {
			coinValue = true;
		} else {
			coinValue = false;
		}
	}

	static ArrayList<Agent> agents = new ArrayList<Agent>(); //remove
	
	Agent next;
	boolean assigned;
	int house;
	HashMap<Integer, Agent> pref;
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
	
	public Agent(int portNum, ArrayList<Integer> preference, int house, String[] peers, int[] ports) {
		this.active = true;
		this.inCycle = false;
		this.children = new HashSet<Agent>();
		this.portNum = portNum;
		this.preference = preference;
		this.house = house;
		this.ports = ports;
		this.peers = peers;
		this.pref = new HashMap<Integer, Agent>();
		
		try {
			System.setProperty("java.rmi.server.hostname", this.peers[portNum]);
			registry = LocateRegistry.createRegistry(this.ports[portNum]);
			stub = (AgentRMI) UnicastRemoteObject.exportObject(this, this.ports[portNum]);
			registry.rebind("Agent", stub);
		} 
		catch (RemoteException e) {
			e.printStackTrace();
		} 
	}
	
	public void setPref(HashMap<Integer, Agent> map) {
		for (Integer k : map.keySet()) {
			this.pref.put(k, map.get(k));
		}
	}
	
	public boolean flipCoin() {
		double randNum = Math.random();
		boolean coin = false;
		if (randNum >= 0.5) {
			coin = true;
		}
		return coin;
	}
	
	public void findCycle() {
		while (active == true) {
			
			// Coin flip step:
			this.coinValue = flipCoin();
			boolean succCoin = successor.coinValue;
			if (this.coinValue == true && succCoin == false) {
				this.active = false;
			}
			
			// Explore step
			if (active == true) {
//				System.out.println("i am active "+portNum);
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
						this.Call("receiveOk", req, this.parent.portNum); //TODO: parent
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
//			System.out.println("found cycle "+portNum);
//			System.out.println("my children size is "+this.children.size());
			this.inCycle = true;
			for (Agent child: this.children) {
				// send cycle to child
				this.Call("receiveCycle", null, child.portNum);
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
				this.setCoinValue();
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
//		System.out.println("i am in change cycle "+portNum);
		this.house = this.nextPref;
		this.assigned = true;
		// Broadcast removePref to all
		for (Agent a: agents) {
			Request req = new Request(this.portNum, this.house, -1);
			this.Call("removePref", req, a.portNum);
		}
	}
	
	@Override
	public void receiveCycle() {
		this.inCycle = true;
//		System.out.println("got cycle from parent "+portNum);
		this.changeInCycle();
		for (Agent c: this.children) {
			this.Call("receiveCycle", null, c.portNum);
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
	
	@Override
	public void receiveOk(Request req) {
		if (isRoot == true) {
			for(int i=0; i<threads.length; i++) {
				threads[i].interrupt();
			}
		}
	}
	
	@Override
	public void removePref(Request request) {
		int house = request.house;
		this.pref.remove(house);
	}
	
	public Response Call(String rmi, Request req, int id){
		NUM_MSGS++;
        Response callReply = null;
        AgentRMI stub;
        try {
            Registry registry=LocateRegistry.getRegistry(this.ports[id]);
            stub=(AgentRMI) registry.lookup("Agent");
            if(rmi.equals("receiveCycle")) {
                stub.receiveCycle();
            }
            else if(rmi.equals("removePref")) {
                stub.removePref(req);
            }
            else if(rmi.equals("receiveOk")) {
                stub.receiveOk(req);
            }
            else
                System.out.println("Wrong parameters!");
        } catch(Exception e){
        	System.out.println("- - - --- - -caught exception in call " + e);
            return null;
        }
        return callReply;
    }
	
	public void Kill(){
        if(this.registry != null){
            try {
                UnicastRemoteObject.unexportObject(this.registry, true);
            } catch(Exception e){
                System.out.println("None reference");
            }
        }
    }

}
