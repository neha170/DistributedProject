package ttcNonDetAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {
	public static void main(String[] args) {
		int numsAgents = 10;
		String host = "127.0.0.1";
        String[] peers = new String[numsAgents+1];
        int[] ports = new int[numsAgents+1];
        for(int i=0; i<numsAgents+1; i++){
            ports[i] = 2100+i;
            peers[i] = host;
        }
		CyclicBarrier barrier = new CyclicBarrier(numsAgents+1);
        ArrayList<Agent> agentList = new ArrayList<Agent> ();
        Agent root;
        root = new Agent(numsAgents, null, -1, peers, ports);
        root.isRoot = true;
//        threeAgentsMax(barrier, agentList, peers, ports); //Best case
//        fourAgentsMax(barrier, agentList, peers, ports);
//        fiveAgentsMax(barrier, agentList, peers, ports);
//        tenAgentsMax(barrier, agentList, peers, ports);
        
//        threeAgentsMin(barrier, agentList, peers, ports); // Worst case
//        fourAgentsMin(barrier, agentList, peers, ports);
//        fiveAgentsMin(barrier, agentList, peers, ports);
        tenAgentsMin(barrier, agentList, peers, ports);
        
        Agent.agents = agentList;
        
        Thread rootThread = new Thread(root);
        rootThread.start();
        
        try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e1) {
		}
        
        try {
			rootThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("THIS IS THE FINAL ANSWER");
        for (Agent a: agentList) {
        	System.out.println("Agent " + a.portNum + " house : " + a.house);
        }
        
        for (Agent a: agentList) {
			if(a != null){
				a.Kill();
			}	
		}
        root.Kill();
        System.out.println("This is the number of messages: " + Agent.NUM_MSGS);
        System.exit(0);
	}
	
	public static void threeAgentsMax(CyclicBarrier barrier, ArrayList<Agent> agentList, String[] peers, int[] ports){
		Agent[] agents = new Agent[3];
		agents[0] = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3)), 1, peers, ports);
		agents[1] = new Agent(1, new ArrayList<Integer> (Arrays.asList(2, 3, 1)), 2, peers, ports);
		agents[2] = new Agent(2, new ArrayList<Integer> (Arrays.asList(3, 1, 2)), 3, peers, ports);
		HashMap<Integer, Agent> pref = new HashMap<Integer, Agent>();
		for (int i=0; i<3; i++) {
			agentList.add(agents[i]);
			agents[i].setBarrier(barrier);
		}
		for(Agent a: agentList) {
        	pref.put(a.house, a);
        }
		for(Agent a: agentList) {
        	a.setPref(pref);
        }
	}
	
	public static void threeAgentsMin(CyclicBarrier barrier, ArrayList<Agent> agentList, String[] peers, int[] ports){
		Agent[] agents = new Agent[3];
		agents[0] = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3)), 1, peers, ports);
		agents[1] = new Agent(1, new ArrayList<Integer> (Arrays.asList(1, 2, 3)), 2, peers, ports);
		agents[2] = new Agent(2, new ArrayList<Integer> (Arrays.asList(1, 2, 3)), 3, peers, ports);
		HashMap<Integer, Agent> pref = new HashMap<Integer, Agent>();
		for (int i=0; i<3; i++) {
			agentList.add(agents[i]);
			agents[i].setBarrier(barrier);
		}
		for(Agent a: agentList) {
        	pref.put(a.house, a);
        }
		for(Agent a: agentList) {
        	a.setPref(pref);
        }
	}
	
	public static void fourAgentsMax(CyclicBarrier barrier, ArrayList<Agent> agentList, String[] peers, int[] ports){
		Agent[] agents = new Agent[4];
		agents[0] = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4)), 1, peers, ports);
		agents[1] = new Agent(1, new ArrayList<Integer> (Arrays.asList(2, 3, 4, 1)), 2, peers, ports);
		agents[2] = new Agent(2, new ArrayList<Integer> (Arrays.asList(3, 4, 1, 2)), 3, peers, ports);
		agents[3] = new Agent(3, new ArrayList<Integer> (Arrays.asList(4, 1, 2, 3)), 4, peers, ports);
		HashMap<Integer, Agent> pref = new HashMap<Integer, Agent>();
		for (int i=0; i<4; i++) {
			agentList.add(agents[i]);
			agents[i].setBarrier(barrier);
		}
		for(Agent a: agentList) {
        	pref.put(a.house, a);
        }
		for(Agent a: agentList) {
        	a.setPref(pref);
        }
	}
	
	public static void fourAgentsMin(CyclicBarrier barrier, ArrayList<Agent> agentList, String[] peers, int[] ports){
		Agent[] agents = new Agent[4];
		agents[0] = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4)), 1, peers, ports);
		agents[1] = new Agent(1, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4)), 2, peers, ports);
		agents[2] = new Agent(2, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4)), 3, peers, ports);
		agents[3] = new Agent(3, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4)), 4, peers, ports);
		HashMap<Integer, Agent> pref = new HashMap<Integer, Agent>();
		for (int i=0; i<4; i++) {
			agentList.add(agents[i]);
			agents[i].setBarrier(barrier);
		}
		for(Agent a: agentList) {
        	pref.put(a.house, a);
        }
		for(Agent a: agentList) {
        	a.setPref(pref);
        }
	}
	
	public static void fiveAgentsMin(CyclicBarrier barrier, ArrayList<Agent> agentList, String[] peers, int[] ports){
		Agent[] agents = new Agent[5];
		agents[0] = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5)), 1, peers, ports);
		agents[1] = new Agent(1, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5)), 2, peers, ports);
		agents[2] = new Agent(2, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5)), 3, peers, ports);
		agents[3] = new Agent(3, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5)), 4, peers, ports);
		agents[4] = new Agent(4, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5)), 5, peers, ports);
		HashMap<Integer, Agent> pref = new HashMap<Integer, Agent>();
		for (int i=0; i<5; i++) {
			agentList.add(agents[i]);
			agents[i].setBarrier(barrier);
		}
		for(Agent a: agentList) {
        	pref.put(a.house, a);
        }
		for(Agent a: agentList) {
        	a.setPref(pref);
        }
	}
	
	public static void fiveAgentsMax(CyclicBarrier barrier, ArrayList<Agent> agentList, String[] peers, int[] ports){
		Agent[] agents = new Agent[5];
		agents[0] = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5)), 1, peers, ports);
		agents[1] = new Agent(1, new ArrayList<Integer> (Arrays.asList(2, 3, 4, 5, 1)), 2, peers, ports);
		agents[2] = new Agent(2, new ArrayList<Integer> (Arrays.asList(3,4,5,1,2)), 3, peers, ports);
		agents[3] = new Agent(3, new ArrayList<Integer> (Arrays.asList(4,5,1,2,3)), 4, peers, ports);
		agents[4] = new Agent(4, new ArrayList<Integer> (Arrays.asList(5,1,2,3,4)), 5, peers, ports);
		HashMap<Integer, Agent> pref = new HashMap<Integer, Agent>();
		for (int i=0; i<5; i++) {
			agentList.add(agents[i]);
			agents[i].setBarrier(barrier);
		}
		for(Agent a: agentList) {
        	pref.put(a.house, a);
        }
		for(Agent a: agentList) {
        	a.setPref(pref);
        }
	}
	
	public static void tenAgentsMax(CyclicBarrier barrier, ArrayList<Agent> agentList, String[] peers, int[] ports){
		Agent[] agents = new Agent[10];
		agents[0] = new Agent(0, new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10)), 1, peers, ports);
		agents[1] = new Agent(1, new ArrayList<Integer> (Arrays.asList(2,3,4,5,6,7,8,9,10,1)), 2, peers, ports);
		agents[2] = new Agent(2, new ArrayList<Integer> (Arrays.asList(3,4,5,6,7,8,9,10,1,2)), 3, peers, ports);
		agents[3] = new Agent(3, new ArrayList<Integer> (Arrays.asList(4,5,6,7,8,9,10,1,2,3)), 4, peers, ports);
		agents[4] = new Agent(4, new ArrayList<Integer> (Arrays.asList(5,6,7,8,9,10,1,2,3,4)), 5, peers, ports);
		agents[5] = new Agent(5, new ArrayList<Integer> (Arrays.asList(6,7,8,9,10,1,2,3,4,5)), 6, peers, ports);
		agents[6] = new Agent(6, new ArrayList<Integer> (Arrays.asList(7,8,9,10,1,2,3,4,5,6)), 7, peers, ports);
		agents[7] = new Agent(7, new ArrayList<Integer> (Arrays.asList(8,9,10,1,2,3,4,5,6,7)), 8, peers, ports);
		agents[8] = new Agent(8, new ArrayList<Integer> (Arrays.asList(9,10,1,2,3,4,5,6,7,8)), 9, peers, ports);
		agents[9] = new Agent(9, new ArrayList<Integer> (Arrays.asList(10,1,2,3,4,5,6,7,8,9)), 10, peers, ports);
		
		HashMap<Integer, Agent> pref = new HashMap<Integer, Agent>();
		for (int i=0; i<10; i++) {
			agentList.add(agents[i]);
			agents[i].setBarrier(barrier);
		}
		for(Agent a: agentList) {
        	pref.put(a.house, a);
        }
		for(Agent a: agentList) {
        	a.setPref(pref);
        }
	}
	
	public static void tenAgentsMin(CyclicBarrier barrier, ArrayList<Agent> agentList, String[] peers, int[] ports){
		Agent[] agents = new Agent[10];
		agents[0] = new Agent(0, new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10)), 1, peers, ports);
		agents[1] = new Agent(1, new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10)), 2, peers, ports);
		agents[2] = new Agent(2, new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10)), 3, peers, ports);
		agents[3] = new Agent(3, new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10)), 4, peers, ports);
		agents[4] = new Agent(4, new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10)), 5, peers, ports);
		agents[5] = new Agent(5, new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10)), 6, peers, ports);
		agents[6] = new Agent(6, new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10)), 7, peers, ports);
		agents[7] = new Agent(7, new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10)), 8, peers, ports);
		agents[8] = new Agent(8, new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10)), 9, peers, ports);
		agents[9] = new Agent(9, new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10)), 10, peers, ports);
		
		HashMap<Integer, Agent> pref = new HashMap<Integer, Agent>();
		for (int i=0; i<10; i++) {
			agentList.add(agents[i]);
			agents[i].setBarrier(barrier);
		}
		for(Agent a: agentList) {
        	pref.put(a.house, a);
        }
		for(Agent a: agentList) {
        	a.setPref(pref);
        }
	}
}
