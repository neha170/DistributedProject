package dictator;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		dictatorExampleRandom();  // change this to run a testcase
        System.exit(0);
	}
	
	static void dictatorExampleMax1() {
		int numsAgents = 3;
		String host = "127.0.0.1";
        String[] peers = new String[numsAgents];
        ArrayList<Agent> agentList = new ArrayList<Agent> ();
        int[] ports = new int[numsAgents];
        for(int i=0; i<numsAgents; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
        Agent agent1 = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3)), peers, ports);
        Agent agent2 = new Agent(1, new ArrayList<Integer> (Arrays.asList(2, 3, 1)), peers, ports);
        Agent agent3 = new Agent(2, new ArrayList<Integer> (Arrays.asList(3, 2, 1)), peers, ports);
        agentList.add(agent1);
        agentList.add(agent2);
        agentList.add(agent3);
        Thread[] thread = new Thread[numsAgents];
        for (int i=0;i<numsAgents;i++) {
        	thread[i] = new Thread(agentList.get(i));
        	thread[i].start();
        }
        for (int i=0;i<numsAgents;i++) {
        	try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        for (Agent a: agentList) {
			System.out.println("Agent: "+a.portId+" House: "+a.house);	
		}
		System.out.println("Total messages: "+ Agent.numMessages);	
        
        for (Agent a: agentList) {
			if(a != null){
				a.Kill();
			}	
		}
	}
	
	static void dictatorExampleMax2() {
		int numsAgents = 4;
		String host = "127.0.0.1";
        String[] peers = new String[numsAgents];
        ArrayList<Agent> agentList = new ArrayList<Agent> ();
        int[] ports = new int[numsAgents];
        for(int i=0; i<numsAgents; i++){
            ports[i] = 2100+i;
            peers[i] = host;
        }
        Agent agent1 = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4)), peers, ports);
        Agent agent2 = new Agent(1, new ArrayList<Integer> (Arrays.asList(2, 3, 4, 1)), peers, ports);
        Agent agent3 = new Agent(2, new ArrayList<Integer> (Arrays.asList(3, 4, 2, 1)), peers, ports);
        Agent agent4 = new Agent(3, new ArrayList<Integer> (Arrays.asList(4, 2, 1, 3)), peers, ports);
        agentList.add(agent1);
        agentList.add(agent2);
        agentList.add(agent3);
        agentList.add(agent4);
        Thread[] thread = new Thread[numsAgents];
        for (int i=0;i<numsAgents;i++) {
        	thread[i] = new Thread(agentList.get(i));
        	thread[i].start();
        }
        for (int i=0;i<numsAgents;i++) {
        	try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        for (Agent a: agentList) {
			System.out.println("Agent: "+a.portId+" House: "+a.house);	
		}
        
		System.out.println("Total messages: "+ Agent.numMessages);	
        
        for (Agent a: agentList) {
			if(a != null){
				a.Kill();
			}	
		}
	}
	
	static void dictatorExampleMax3() {
		int numsAgents = 5;
		String host = "127.0.0.1";
        String[] peers = new String[numsAgents];
        ArrayList<Agent> agentList = new ArrayList<Agent> ();
        int[] ports = new int[numsAgents];
        for(int i=0; i<numsAgents; i++){
            ports[i] = 2100+i;
            peers[i] = host;
        }
        Agent agent1 = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5)), peers, ports);
        Agent agent2 = new Agent(1, new ArrayList<Integer> (Arrays.asList(2, 3, 4, 5, 1)), peers, ports);
        Agent agent3 = new Agent(2, new ArrayList<Integer> (Arrays.asList(3, 4, 5, 1, 2)), peers, ports);
        Agent agent4 = new Agent(3, new ArrayList<Integer> (Arrays.asList(4, 5, 1, 2, 3)), peers, ports);
        Agent agent5 = new Agent(4, new ArrayList<Integer> (Arrays.asList(5, 1, 2, 3, 4)), peers, ports);
        agentList.add(agent1);
        agentList.add(agent2);
        agentList.add(agent3);
        agentList.add(agent4);
        agentList.add(agent5);
        Thread[] thread = new Thread[numsAgents];
        for (int i=0;i<numsAgents;i++) {
        	thread[i] = new Thread(agentList.get(i));
        	thread[i].start();
        }
        
        for (int i=0;i<numsAgents;i++) {
        	try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        for (Agent a: agentList) {
			System.out.println("Agent: "+a.portId+" House: "+a.house);	
		}
		System.out.println("Total messages: "+ Agent.numMessages);	
        
        for (Agent a: agentList) {
			if(a != null){
				a.Kill();
			}	
		}
	}
	
	static void dictatorExampleMax4() {
		int numsAgents = 10;
		String host = "127.0.0.1";
        String[] peers = new String[numsAgents];
        ArrayList<Agent> agentList = new ArrayList<Agent> ();
        int[] ports = new int[numsAgents];
        for(int i=0; i<numsAgents; i++){
            ports[i] = 2100+i;
            peers[i] = host;
        }
        Agent agent1 = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), peers, ports);
        Agent agent2 = new Agent(1, new ArrayList<Integer> (Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 1)), peers, ports);
        Agent agent3 = new Agent(2, new ArrayList<Integer> (Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 1, 2)), peers, ports);
        Agent agent4 = new Agent(3, new ArrayList<Integer> (Arrays.asList(4, 5, 6, 7, 8, 9, 10, 1, 2, 3)), peers, ports);
        Agent agent5 = new Agent(4, new ArrayList<Integer> (Arrays.asList(5, 6, 7, 8, 9, 10, 1, 2, 3, 4)), peers, ports);
        Agent agent6 = new Agent(5, new ArrayList<Integer> (Arrays.asList(6, 7, 8, 9, 10, 1, 2, 3, 4, 5)), peers, ports);
        Agent agent7 = new Agent(6, new ArrayList<Integer> (Arrays.asList(7, 8, 9, 10, 1, 2, 3, 4, 5, 6)), peers, ports);
        Agent agent8 = new Agent(7, new ArrayList<Integer> (Arrays.asList(8, 9, 10, 1, 2, 3, 4, 5, 6, 7)), peers, ports);
        Agent agent9 = new Agent(8, new ArrayList<Integer> (Arrays.asList(9, 10, 1, 2, 3, 4, 5, 6, 7, 8)), peers, ports);
        Agent agent10 = new Agent(9, new ArrayList<Integer> (Arrays.asList(10, 1, 2, 3, 4, 5, 6, 7, 8, 9)), peers, ports);
        agentList.add(agent1);
        agentList.add(agent2);
        agentList.add(agent3);
        agentList.add(agent4);
        agentList.add(agent5);
        agentList.add(agent6);
        agentList.add(agent7);
        agentList.add(agent8);
        agentList.add(agent9);
        agentList.add(agent10);
        Thread[] thread = new Thread[numsAgents];
        for (int i=0;i<numsAgents;i++) {
        	thread[i] = new Thread(agentList.get(i));
        	thread[i].start();
        }
        for (int i=0;i<numsAgents;i++) {
        	try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        for (Agent a: agentList) {
			System.out.println("Agent: "+a.portId+" House: "+a.house);	
		}
        
		System.out.println("Total messages: "+ Agent.numMessages);	
        
        for (Agent a: agentList) {
			if(a != null){
				a.Kill();
			}	
		}
	}
	
	static void dictatorExampleMin1() {
		int numsAgents = 3;
		String host = "127.0.0.1";
        String[] peers = new String[numsAgents];
        ArrayList<Agent> agentList = new ArrayList<Agent> ();
        int[] ports = new int[numsAgents];
        for(int i=0; i<numsAgents; i++){
            ports[i] = 2100+i;
            peers[i] = host;
        }
        Agent agent1 = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3)), peers, ports);
        Agent agent2 = new Agent(1, new ArrayList<Integer> (Arrays.asList(1, 2, 3)), peers, ports);
        Agent agent3 = new Agent(2, new ArrayList<Integer> (Arrays.asList(1, 2, 3)), peers, ports);
        agentList.add(agent1);
        agentList.add(agent2);
        agentList.add(agent3);
        Thread[] thread = new Thread[numsAgents];
        for (int i=0;i<numsAgents;i++) {
        	thread[i] = new Thread(agentList.get(i));
        	thread[i].start();
        }
        for (int i=0;i<numsAgents;i++) {
        	try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        for (Agent a: agentList) {
			System.out.println("Agent: "+a.portId+" House: "+a.house);	
		}
        
		System.out.println("Total messages: "+ Agent.numMessages);	
        
        for (Agent a: agentList) {
			if(a != null){
				a.Kill();
			}	
		}
	}
	
	static void dictatorExampleMin2() {
		int numsAgents = 4;
		String host = "127.0.0.1";
        String[] peers = new String[numsAgents];
        ArrayList<Agent> agentList = new ArrayList<Agent> ();
        int[] ports = new int[numsAgents];
        for(int i=0; i<numsAgents; i++){
            ports[i] = 2100+i;
            peers[i] = host;
        }
        Agent agent1 = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4)), peers, ports);
        Agent agent2 = new Agent(1, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4)), peers, ports);
        Agent agent3 = new Agent(2, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4)), peers, ports);
        Agent agent4 = new Agent(3, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4)), peers, ports);
        agentList.add(agent1);
        agentList.add(agent2);
        agentList.add(agent3);
        agentList.add(agent4);
        Thread[] thread = new Thread[numsAgents];
        for (int i=0;i<numsAgents;i++) {
        	thread[i] = new Thread(agentList.get(i));
        	thread[i].start();
        }
        for (int i=0;i<numsAgents;i++) {
        	try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        for (Agent a: agentList) {
			System.out.println("Agent: "+a.portId+" House: "+a.house);	
		}
		System.out.println("Total messages: "+ Agent.numMessages);	
        
        for (Agent a: agentList) {
			if(a != null){
				a.Kill();
			}	
		}
	}
	
	static void dictatorExampleMin3() {
		int numsAgents = 5;
		String host = "127.0.0.1";
        String[] peers = new String[numsAgents];
        ArrayList<Agent> agentList = new ArrayList<Agent> ();
        int[] ports = new int[numsAgents];
        for(int i=0; i<numsAgents; i++){
            ports[i] = 2100+i;
            peers[i] = host;
        }
        Agent agent1 = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5)), peers, ports);
        Agent agent2 = new Agent(1, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5)), peers, ports);
        Agent agent3 = new Agent(2, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5)), peers, ports);
        Agent agent4 = new Agent(2, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5)), peers, ports);
        Agent agent5 = new Agent(2, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5)), peers, ports);
        agentList.add(agent1);
        agentList.add(agent2);
        agentList.add(agent3);
        agentList.add(agent4);
        agentList.add(agent5);
        Thread[] thread = new Thread[numsAgents];
        for (int i=0;i<numsAgents;i++) {
        	thread[i] = new Thread(agentList.get(i));
        	thread[i].start();
        }
        for (int i=0;i<numsAgents;i++) {
        	try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        for (Agent a: agentList) {
			System.out.println("Agent: "+a.portId+" House: "+a.house);	
		}
        
		System.out.println("Total messages: "+ Agent.numMessages);	
        
        for (Agent a: agentList) {
			if(a != null){
				a.Kill();
			}	
		}
	}
	
	static void dictatorExampleMin4() {
		int numsAgents = 10;
		String host = "127.0.0.1";
        String[] peers = new String[numsAgents];
        ArrayList<Agent> agentList = new ArrayList<Agent> ();
        int[] ports = new int[numsAgents];
        for(int i=0; i<numsAgents; i++){
            ports[i] = 2100+i;
            peers[i] = host;
        }
        Agent agent1 = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), peers, ports);
        Agent agent2 = new Agent(1, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), peers, ports);
        Agent agent3 = new Agent(2, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), peers, ports);
        Agent agent4 = new Agent(3, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), peers, ports);
        Agent agent5 = new Agent(4, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), peers, ports);
        Agent agent6 = new Agent(5, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), peers, ports);
        Agent agent7 = new Agent(6, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), peers, ports);
        Agent agent8 = new Agent(7, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), peers, ports);
        Agent agent9 = new Agent(8, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), peers, ports);
        Agent agent10 = new Agent(9, new ArrayList<Integer> (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), peers, ports);
        agentList.add(agent1);
        agentList.add(agent2);
        agentList.add(agent3);
        agentList.add(agent4);
        agentList.add(agent5);
        agentList.add(agent6);
        agentList.add(agent7);
        agentList.add(agent8);
        agentList.add(agent9);
        agentList.add(agent10);
        Thread[] thread = new Thread[numsAgents];
        for (int i=0;i<numsAgents;i++) {
        	thread[i] = new Thread(agentList.get(i));
        	thread[i].start();
        }
        for (int i=0;i<numsAgents;i++) {
        	try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        for (Agent a: agentList) {
			System.out.println("Agent: "+a.portId+" House: "+a.house);	
		}
        
		System.out.println("Total messages: "+ Agent.numMessages);	
        
        for (Agent a: agentList) {
			if(a != null){
				a.Kill();
			}	
		}
	}
	
	static void dictatorExampleRandom() {
		int numsAgents = 5;
		String host = "127.0.0.1";
        String[] peers = new String[numsAgents];
        ArrayList<Agent> agentList = new ArrayList<Agent> ();
        int[] ports = new int[numsAgents];
        for(int i=0; i<numsAgents; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
        Agent agent1 = new Agent(0, new ArrayList<Integer> (Arrays.asList(1, 3, 4, 5)), peers, ports);
        Agent agent2 = new Agent(1, new ArrayList<Integer> (Arrays.asList(1, 2, 4)), peers, ports);
        Agent agent3 = new Agent(2, new ArrayList<Integer> (Arrays.asList(4, 5)), peers, ports);
        Agent agent4 = new Agent(3, new ArrayList<Integer> (Arrays.asList(2, 3, 4, 5)), peers, ports);
        Agent agent5 = new Agent(4, new ArrayList<Integer> (Arrays.asList(3, 4, 5, 1, 2)), peers, ports);
        agentList.add(agent1);
        agentList.add(agent2);
        agentList.add(agent3);
        agentList.add(agent4);
        agentList.add(agent5);
        Thread[] thread = new Thread[numsAgents];
        for (int i=0;i<numsAgents;i++) {
        	thread[i] = new Thread(agentList.get(i));
        	thread[i].start();
        }
        for (int i=0;i<numsAgents;i++) {
        	try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        for (Agent a: agentList) {
			System.out.println("Agent: "+a.portId+" House: "+a.house);	
		}
        
		System.out.println("Total messages: "+ Agent.numMessages);	
        
        for (Agent a: agentList) {
			if(a != null){
				a.Kill();
			}	
		}
	}
}
