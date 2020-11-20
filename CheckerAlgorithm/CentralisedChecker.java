/*
 * This class represents both the agent and the checker process.
 * Communication is via the message passing interface
 */
package CheckerAlgorithm;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class CentralisedChecker implements RMIInterface{
	
	int agentId;
	int[] pref;
	int currPref = 0;
	static int numMessages = 0;

	String[] peers;
	int[] ports;
	RMIInterface stub;
	Registry registry;
	
	public CentralisedChecker(int[] pref, int id, String[] peers, int[] ports) {
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
	
	public Response Call(String rmi, Request req, int id){
        Response callReply = null;

        RMIInterface stub;
        try{
            Registry registry=LocateRegistry.getRegistry(this.ports[id]);
            stub=(RMIInterface) registry.lookup("Agent");
            if(rmi.equals("getPref")) {
            	CentralisedChecker.numMessages++;
                callReply = stub.getPref();
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

	@Override
	public Response getPref() throws RemoteException {
		Response response = null;
		if(currPref < pref.length) {
			response = new Response(agentId, pref[currPref], currPref);
			currPref++;
		}
		return response;
	}
	
	public Map<Integer, House> getAllocation(Queue<Integer> q) {
		// allocation maps agentId to currently assigned houseId
		Map<Integer, House> allocation = new HashMap<Integer, House>();

		while(!q.isEmpty()) {
			int nextId = q.poll();
			boolean alreadyAllocated = false;
			//get the next pref from the agent
			Response response = Call("getPref", null, nextId);
			if(response == null) {
				//this agent has exhausted all preferences
				continue; //this will never happen
			}
			for(Integer key: allocation.keySet()) {
				House h = allocation.get(key);
				if(h.houseId == response.houseId) {
					alreadyAllocated = true;
					if(h.pref <= response.pref) {
						q.add(nextId);
					}
					else {
						q.add(key);
						allocation.put(nextId, h);
						allocation.remove(key);
					}
					break;
				}
			}
			if(alreadyAllocated == false) {
				//this preferred house has not been allocated yet
				allocation.put(nextId, new House(response.houseId, response.pref));
			}
		}
		return allocation;
	}

	@Override
	public Response getAllocation(Request request) throws RemoteException {
		// not used here.
		return null;
	}
}
