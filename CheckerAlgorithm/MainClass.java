package CheckerAlgorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MainClass {
	public static void main(String[] args) {
		distributedCheckerExampleMax1();  // change this to run a testcase
		System.exit(0);
	}
	
	static void distributedCheckerExampleMax1() {
		int numAgent = 3;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
		DistributedChecker[] agents = new DistributedChecker[numAgent];
		agents[0] = new DistributedChecker(new int[] {1,2,3}, 0, peers, ports);
		agents[1] = new DistributedChecker(new int[] {2,3,1}, 1, peers, ports);
		agents[2] = new DistributedChecker(new int[] {3,1,2}, 2, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = new HashMap<Integer, House>();
		alloc = agents[0].start(q, alloc);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+DistributedChecker.numMessages);

		for(int i=0; i<numAgent; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
	}
	
	static void distributedCheckerExampleMax2() {
		int numAgent = 4;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
		DistributedChecker[] agents = new DistributedChecker[numAgent];
		agents[0] = new DistributedChecker(new int[] {1,2,3,4}, 0, peers, ports);
		agents[1] = new DistributedChecker(new int[] {2,3,4,1}, 1, peers, ports);
		agents[2] = new DistributedChecker(new int[] {3,4,1,2}, 2, peers, ports);
		agents[3] = new DistributedChecker(new int[] {4,1,2,3}, 3, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = new HashMap<Integer, House>();
		alloc = agents[0].start(q, alloc);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+DistributedChecker.numMessages);

		for(int i=0; i<numAgent; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
	}
	
	static void distributedCheckerExampleMax3() {
		int numAgent = 5;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
		DistributedChecker[] agents = new DistributedChecker[numAgent];
		agents[0] = new DistributedChecker(new int[] {1,2,3,4,5}, 0, peers, ports);
		agents[1] = new DistributedChecker(new int[] {2,3,4,5,1}, 1, peers, ports);
		agents[2] = new DistributedChecker(new int[] {3,4,5,1,2}, 2, peers, ports);
		agents[3] = new DistributedChecker(new int[] {4,5,1,2,3}, 3, peers, ports);
		agents[4] = new DistributedChecker(new int[] {5,1,2,3,4}, 4, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = new HashMap<Integer, House>();
		alloc = agents[0].start(q, alloc);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+DistributedChecker.numMessages);

		for(int i=0; i<numAgent; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
	}
	
	static void distributedCheckerExampleMax4() {
		int numAgent = 10;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
		DistributedChecker[] agents = new DistributedChecker[numAgent];
		agents[0] = new DistributedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 0, peers, ports);
		agents[1] = new DistributedChecker(new int[] {2,3,4,5,6,7,8,9,10,1}, 1, peers, ports);
		agents[2] = new DistributedChecker(new int[] {3,4,5,6,7,8,9,10,1,2}, 2, peers, ports);
		agents[3] = new DistributedChecker(new int[] {4,5,6,7,8,9,10,1,2,3}, 3, peers, ports);
		agents[4] = new DistributedChecker(new int[] {5,6,7,8,9,10,1,2,3,4}, 4, peers, ports);
		agents[5] = new DistributedChecker(new int[] {6,7,8,9,10,1,2,3,4,5}, 5, peers, ports);
		agents[6] = new DistributedChecker(new int[] {7,8,9,10,1,2,3,4,5,6}, 6, peers, ports);
		agents[7] = new DistributedChecker(new int[] {8,9,10,1,2,3,4,5,6,7}, 7, peers, ports);
		agents[8] = new DistributedChecker(new int[] {9,10,1,2,3,4,5,6,7,8}, 8, peers, ports);
		agents[9] = new DistributedChecker(new int[] {10,1,2,3,4,5,6,7,8,9}, 9, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = new HashMap<Integer, House>();
		alloc = agents[0].start(q, alloc);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+DistributedChecker.numMessages);

		for(int i=0; i<numAgent; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
	}
	
	static void distributedCheckerExampleMin1() {
		int numAgent = 3;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
		DistributedChecker[] agents = new DistributedChecker[numAgent];
		agents[0] = new DistributedChecker(new int[] {1,2,3}, 0, peers, ports);
		agents[1] = new DistributedChecker(new int[] {1,2,3}, 1, peers, ports);
		agents[2] = new DistributedChecker(new int[] {1,2,3}, 2, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = new HashMap<Integer, House>();
		alloc = agents[0].start(q, alloc);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+DistributedChecker.numMessages);

		for(int i=0; i<numAgent; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
	}
	
	static void distributedCheckerExampleMin2() {
		int numAgent = 4;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
		DistributedChecker[] agents = new DistributedChecker[numAgent];
		agents[0] = new DistributedChecker(new int[] {1,2,3,4}, 0, peers, ports);
		agents[1] = new DistributedChecker(new int[] {1,2,3,4}, 1, peers, ports);
		agents[2] = new DistributedChecker(new int[] {1,2,3,4}, 2, peers, ports);
		agents[3] = new DistributedChecker(new int[] {1,2,3,4}, 3, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = new HashMap<Integer, House>();
		alloc = agents[0].start(q, alloc);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+DistributedChecker.numMessages);

		for(int i=0; i<numAgent; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
	}
	
	static void distributedCheckerExampleMin3() {
		int numAgent = 5;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
		DistributedChecker[] agents = new DistributedChecker[numAgent];
		agents[0] = new DistributedChecker(new int[] {1,2,3,4,5}, 0, peers, ports);
		agents[1] = new DistributedChecker(new int[] {1,2,3,4,5}, 1, peers, ports);
		agents[2] = new DistributedChecker(new int[] {1,2,3,4,5}, 2, peers, ports);
		agents[3] = new DistributedChecker(new int[] {1,2,3,4,5}, 3, peers, ports);
		agents[4] = new DistributedChecker(new int[] {1,2,3,4,5}, 4, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = new HashMap<Integer, House>();
		alloc = agents[0].start(q, alloc);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+DistributedChecker.numMessages);

		for(int i=0; i<numAgent; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
	}
	
	static void distributedCheckerExampleMin4() {
		int numAgent = 10;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
		DistributedChecker[] agents = new DistributedChecker[numAgent];
		agents[0] = new DistributedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 0, peers, ports);
		agents[1] = new DistributedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 1, peers, ports);
		agents[2] = new DistributedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 2, peers, ports);
		agents[3] = new DistributedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 3, peers, ports);
		agents[4] = new DistributedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 4, peers, ports);
		agents[5] = new DistributedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 5, peers, ports);
		agents[6] = new DistributedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 6, peers, ports);
		agents[7] = new DistributedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 7, peers, ports);
		agents[8] = new DistributedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 8, peers, ports);
		agents[9] = new DistributedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 9, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = new HashMap<Integer, House>();
		alloc = agents[0].start(q, alloc);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+DistributedChecker.numMessages);

		for(int i=0; i<numAgent; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
	}
	
	static void centralisedCheckerExampleMax1() {
		int numAgent = 4;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
        CentralisedChecker[] agents = new CentralisedChecker[numAgent-1];
		agents[0] = new CentralisedChecker(new int[] {1,2,3}, 0, peers, ports);
		agents[1] = new CentralisedChecker(new int[] {2,3,1}, 1, peers, ports);
		agents[2] = new CentralisedChecker(new int[] {3,1,2}, 2, peers, ports);
		CentralisedChecker checkerAgent = new CentralisedChecker(null, 3, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent-1; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = checkerAgent.getAllocation(q);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+CentralisedChecker.numMessages);

		for(int i=0; i<numAgent-1; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
		checkerAgent.Kill();
	}
	
	static void centralisedCheckerExampleMax2() {
		int numAgent = 5;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
        CentralisedChecker[] agents = new CentralisedChecker[numAgent-1];
		agents[0] = new CentralisedChecker(new int[] {1,2,3,4}, 0, peers, ports);
		agents[1] = new CentralisedChecker(new int[] {2,3,4,1}, 1, peers, ports);
		agents[2] = new CentralisedChecker(new int[] {3,4,1,2}, 2, peers, ports);
		agents[3] = new CentralisedChecker(new int[] {4,1,2,3}, 3, peers, ports);
		CentralisedChecker checkerAgent = new CentralisedChecker(null, 4, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent-1; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = checkerAgent.getAllocation(q);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+CentralisedChecker.numMessages);

		for(int i=0; i<numAgent-1; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
		checkerAgent.Kill();
	}
	
	static void centralisedCheckerExampleMax3() {
		int numAgent = 6;
		String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
        CentralisedChecker[] agents = new CentralisedChecker[numAgent-1];
		agents[0] = new CentralisedChecker(new int[] {1,2,3,4,5}, 0, peers, ports);
		agents[1] = new CentralisedChecker(new int[] {2,3,4,5,1}, 1, peers, ports);
		agents[2] = new CentralisedChecker(new int[] {3,4,5,1,2}, 2, peers, ports);
		agents[3] = new CentralisedChecker(new int[] {4,5,1,2,3}, 3, peers, ports);
		agents[4] = new CentralisedChecker(new int[] {5,1,2,3,4}, 4, peers, ports);
		CentralisedChecker checkerAgent = new CentralisedChecker(null, 5, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent-1; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = checkerAgent.getAllocation(q);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+CentralisedChecker.numMessages);

		for(int i=0; i<numAgent-1; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
		checkerAgent.Kill();
	}
	
	static void centralisedCheckerExampleMax4() {
		int numAgent = 11;
		String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
        CentralisedChecker[] agents = new CentralisedChecker[numAgent-1];
		agents[0] = new CentralisedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 0, peers, ports);
		agents[1] = new CentralisedChecker(new int[] {2,3,4,5,6,7,8,9,10,1}, 1, peers, ports);
		agents[2] = new CentralisedChecker(new int[] {3,4,5,6,7,8,9,10,1,2}, 2, peers, ports);
		agents[3] = new CentralisedChecker(new int[] {4,5,6,7,8,9,10,1,2,3}, 3, peers, ports);
		agents[4] = new CentralisedChecker(new int[] {5,6,7,8,9,10,1,2,3,4}, 4, peers, ports);
		agents[5] = new CentralisedChecker(new int[] {6,7,8,9,10,1,2,3,4,5}, 5, peers, ports);
		agents[6] = new CentralisedChecker(new int[] {7,8,9,10,1,2,3,4,5,6}, 6, peers, ports);
		agents[7] = new CentralisedChecker(new int[] {8,9,10,1,2,3,4,5,6,7}, 7, peers, ports);
		agents[8] = new CentralisedChecker(new int[] {9,10,1,2,3,4,5,6,7,8}, 8, peers, ports);
		agents[9] = new CentralisedChecker(new int[] {10,1,2,3,4,5,6,7,8,9}, 9, peers, ports);
		CentralisedChecker checkerAgent = new CentralisedChecker(null, 10, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent-1; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = checkerAgent.getAllocation(q);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+CentralisedChecker.numMessages);

		for(int i=0; i<numAgent-1; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
		checkerAgent.Kill();
	}
	
	static void centralisedCheckerExampleMin1() {
		int numAgent = 4;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
        CentralisedChecker[] agents = new CentralisedChecker[numAgent-1];
		agents[0] = new CentralisedChecker(new int[] {1,2,3}, 0, peers, ports);
		agents[1] = new CentralisedChecker(new int[] {1,2,3}, 1, peers, ports);
		agents[2] = new CentralisedChecker(new int[] {1,2,3}, 2, peers, ports);
		CentralisedChecker checkerAgent = new CentralisedChecker(null, 3, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent-1; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = checkerAgent.getAllocation(q);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+CentralisedChecker.numMessages);

		for(int i=0; i<numAgent-1; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
		checkerAgent.Kill();
	}
	
	static void centralisedCheckerExampleMin2() {
		int numAgent = 5;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
        CentralisedChecker[] agents = new CentralisedChecker[numAgent-1];
		agents[0] = new CentralisedChecker(new int[] {1,2,3,4}, 0, peers, ports);
		agents[1] = new CentralisedChecker(new int[] {1,2,3,4}, 1, peers, ports);
		agents[2] = new CentralisedChecker(new int[] {1,2,3,4}, 2, peers, ports);
		agents[3] = new CentralisedChecker(new int[] {1,2,3,4}, 3, peers, ports);
		CentralisedChecker checkerAgent = new CentralisedChecker(null, 4, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent-1; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = checkerAgent.getAllocation(q);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+CentralisedChecker.numMessages);

		for(int i=0; i<numAgent-1; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
		checkerAgent.Kill();
	}
	
	static void centralisedCheckerExampleMin3() {
		int numAgent = 6;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
        CentralisedChecker[] agents = new CentralisedChecker[numAgent-1];
		agents[0] = new CentralisedChecker(new int[] {1,2,3,4,5}, 0, peers, ports);
		agents[1] = new CentralisedChecker(new int[] {1,2,3,4,5}, 1, peers, ports);
		agents[2] = new CentralisedChecker(new int[] {1,2,3,4,5}, 2, peers, ports);
		agents[3] = new CentralisedChecker(new int[] {1,2,3,4,5}, 3, peers, ports);
		agents[4] = new CentralisedChecker(new int[] {1,2,3,4,5}, 4, peers, ports);
		CentralisedChecker checkerAgent = new CentralisedChecker(null, 5, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent-1; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = checkerAgent.getAllocation(q);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+CentralisedChecker.numMessages);

		for(int i=0; i<numAgent-1; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
		checkerAgent.Kill();
	}
	
	static void centralisedCheckerExampleMin4() {
		int numAgent = 11;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
        CentralisedChecker[] agents = new CentralisedChecker[numAgent-1];
		agents[0] = new CentralisedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 0, peers, ports);
		agents[1] = new CentralisedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 1, peers, ports);
		agents[2] = new CentralisedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 2, peers, ports);
		agents[3] = new CentralisedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 3, peers, ports);
		agents[4] = new CentralisedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 4, peers, ports);
		agents[5] = new CentralisedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 5, peers, ports);
		agents[6] = new CentralisedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 6, peers, ports);
		agents[7] = new CentralisedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 7, peers, ports);
		agents[8] = new CentralisedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 8, peers, ports);
		agents[9] = new CentralisedChecker(new int[] {1,2,3,4,5,6,7,8,9,10}, 9, peers, ports);
		CentralisedChecker checkerAgent = new CentralisedChecker(null, 10, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent-1; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = checkerAgent.getAllocation(q);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+CentralisedChecker.numMessages);

		for(int i=0; i<numAgent-1; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
		checkerAgent.Kill();
	}
	
	static void centralisedCheckerRandom1() {
		int numAgent = 6;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
        CentralisedChecker[] agents = new CentralisedChecker[numAgent-1];
		agents[0] = new CentralisedChecker(new int[] {2,1,3}, 0, peers, ports);
		agents[1] = new CentralisedChecker(new int[] {2,3,4,5}, 1, peers, ports);
		agents[2] = new CentralisedChecker(new int[] {4}, 2, peers, ports);
		agents[3] = new CentralisedChecker(new int[] {3,1}, 3, peers, ports);
		agents[4] = new CentralisedChecker(new int[] {3,1}, 4, peers, ports);
		CentralisedChecker checkerAgent = new CentralisedChecker(null, 5, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent-1; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = checkerAgent.getAllocation(q);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+CentralisedChecker.numMessages);

		for(int i=0; i<numAgent-1; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
		checkerAgent.Kill();
	}
	
	static void distributedCheckerRandom1() {
		int numAgent = 5;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1700+i;
            peers[i] = host;
        }
        DistributedChecker[] agents = new DistributedChecker[numAgent];
		agents[0] = new DistributedChecker(new int[] {2,1,3}, 0, peers, ports);
		agents[1] = new DistributedChecker(new int[] {2,3,4,5}, 1, peers, ports);
		agents[2] = new DistributedChecker(new int[] {4}, 2, peers, ports);
		agents[3] = new DistributedChecker(new int[] {3,1}, 3, peers, ports);
		agents[4] = new DistributedChecker(new int[] {3,1}, 4, peers, ports);
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = new HashMap<Integer, House>();
		alloc = agents[0].start(q, alloc);
		printAllocationMap(alloc);
		System.out.println("Total messages: "+DistributedChecker.numMessages);

		for(int i=0; i<numAgent; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
	}
	
	static void printAllocationMap(Map<Integer, House> map) {
		System.out.println("Final Allocation Map");
		for(Integer k : map.keySet()) {
			System.out.println("Agent: "+k+" House: "+map.get(k).houseId);
		}
	}
}
