package CheckerAlgorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MainClass {
	public static void main(String[] args) {
		distributedCheckerExample();
		System.exit(0);
	}
	
	static void distributedCheckerExample() {
		int numAgent = 5;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i=0; i<numAgent; i++){
            ports[i] = 1100+i;
            peers[i] = host;
        }
		DistributedChecker[] agents = new DistributedChecker[numAgent];
		agents[0] = new DistributedChecker(new int[] {2,1,3}, 0, peers, ports);
		agents[1] = new DistributedChecker(new int[] {2,3,4,0}, 1, peers, ports);
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

		for(int i=0; i<numAgent; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
	}
	
	static void centralCheckerExample() {
		int numAgent = 6;
		int numHouse = 6;
        String host = "127.0.0.1";
        String[] peers = new String[numAgent];
        int[] ports = new int[numAgent];
        for(int i = 0; i < numAgent; i++){
            ports[i] = 1100+i;
            peers[i] = host;
        }
		CentralisedChecker[] agents = new CentralisedChecker[numAgent-1];
		agents[0] = new CentralisedChecker(new int[] {2,1,3}, 0, false, numAgent, numHouse, peers, ports);
		agents[1] = new CentralisedChecker(new int[] {2,3,4,0}, 1, false, numAgent, numHouse, peers, ports);
		agents[2] = new CentralisedChecker(new int[] {4}, 2, false, numAgent, numHouse, peers, ports);
		agents[3] = new CentralisedChecker(new int[] {3,1}, 3, false, numAgent, numHouse, peers, ports);
		agents[4] = new CentralisedChecker(new int[] {3,1}, 4, false, numAgent, numHouse, peers, ports);
		CentralisedChecker checkerAgent = new CentralisedChecker(null, 5, false, numAgent, numHouse, peers, ports);
		
		Queue<Integer> q = new LinkedList<Integer>();
		// Queue of all current red agents
		for(int i=0; i<numAgent-1; i++) {
			q.add(i);
		}
		Map<Integer, House> alloc = checkerAgent.getAllocation(q);
		printAllocationMap(alloc);

		for(int i=0; i<numAgent-1; i++) {
			if(agents[i] != null){
				agents[i].Kill();
			}	
		}
		checkerAgent.Kill();
	}
	
	static void printAllocationMap(Map<Integer, House> map) {
		for(Integer k : map.keySet()) {
			System.out.println("Agent: "+k+" House: "+map.get(k).houseId);
		}
	}
}

/* 0: 2->1->3 : 2
 * 1: 2->3->4->0: 0
 * 2: 4 : 4
 * 3: 3 ->1 : 3
 * 4: 3 ->1 : 1
 */

