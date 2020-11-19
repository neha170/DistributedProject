/*
 * This logic implements token to rotate checker process among all the agents.
 * Communication is via the message passing interface
 */
package CheckerAlgorithm;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Queue;

public class DistributedChecker implements RMIInterface{
	
	int agentId;
	int[] pref;
	int currPref = 0;

	String[] peers;
	int[] ports;
	RMIInterface stub;
	Registry registry;
	
	//for checker process
	boolean isChecker = false;
	
	public DistributedChecker(int[] pref, int id, String[] peers, int[] ports) {
		this.pref = pref;
		this.agentId = id;
		this.peers = peers;
		this.ports = ports;
		
		try {
			System.setProperty("java.rmi.server.hostname", this.peers[agentId]);
			registry = LocateRegistry.createRegistry(this.ports[agentId]);
			stub = (RMIInterface) UnicastRemoteObject.exportObject(this, this.ports[agentId]);
			registry.rebind("Agent", stub);
		} 
		catch (RemoteException e) {
			e.printStackTrace();
		} 
	}
	
	Request runRequest;
	Response runResponse;
	
	public void setRunRequest(Request runRequest) {
		this.runRequest = runRequest;
	}
	
	public Response getRunResponse() {
		return this.runResponse;
	}
	
	public Map<Integer, House> start(Queue<Integer> q, Map<Integer, House> allocation) {
		if(!q.isEmpty()) {
			int nextId = q.poll();
			Request nextRequest = new Request(q, allocation);
			Response response = this.Call("getAllocation", nextRequest, nextId);
			if(response == null ) {
			}
			allocation = response.allocation;
			q = response.queue;
			
		}
		return allocation;
	}
	
	public Response Call(String rmi, Request req, int id){
        Response callReply = null;
        RMIInterface stub;
        try{
            Registry registry=LocateRegistry.getRegistry(this.ports[id]);
            stub=(RMIInterface) registry.lookup("Agent");
            if(rmi.equals("getAllocation")) {
                callReply = stub.getAllocation(req);
                return callReply;
            }
            else
                System.out.println("Wrong parameters!");
        } catch(Exception e){
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

	public Response getNextPref() {
		Response response = null;
		if(currPref < pref.length) {
			response = new Response(agentId, pref[currPref], currPref);
			currPref++;
		}
		return response;
	}

	@Override
	public Response getAllocation(Request request) throws RemoteException {
		Queue<Integer> q = request.queue;
		Map<Integer, House> allocation = request.allocation;
		boolean done = false;
		while(done != true) {
			boolean alreadyAllocated = false;
			Response prefResponse = getNextPref();
			if(prefResponse == null) {
				//I have exhausted all preferences
				done = true;
			}
			else {
				for(Integer key: allocation.keySet()) {
					House h = allocation.get(key);
					if(h.houseId == prefResponse.houseId) {
						alreadyAllocated = true;
						if(h.pref <= prefResponse.pref) {
							// ask myself for the next pref
							done = false;
						}
						else {
							q.add(key);
							allocation.put(agentId, h);
							allocation.remove(key);
							done = true;
						}
						break;
					}
				}
				if(alreadyAllocated == false) {
					//this preferred house has not been allocated yet
					allocation.put(agentId, new House(prefResponse.houseId, prefResponse.pref));
					done = true;
				}
			}
		}
		for(; !q.isEmpty();) {
			int nextId = q.poll();
			Request nextRequest = new Request(q, allocation);
			Response response = this.Call("getAllocation", nextRequest, nextId);
			if(response == null ) {
				//TODO: ???
			}
			allocation = response.allocation;
			q = response.queue;
		}
		Response retResponse = new Response(q, allocation);
		return retResponse;
	}

	@Override
	public Response getPref() throws RemoteException {
		return null;
	}
}
