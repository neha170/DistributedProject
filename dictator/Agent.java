package dictator;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Agent implements DictatorRMI, Runnable{
	
	int clock = 0;
	int portId;
	int proposalMax = 0;
	int countProposals = 0;
	int house = -1;
	int[] ports;
	String[] peers;
	boolean assigned = false;
	ArrayList<Request> finalQueue = new ArrayList<Request>();
	ArrayList<Integer> preference = new ArrayList<Integer>();
	DictatorRMI stub;
	Registry registry;
	int[] suggestProposal;
	int[] gotProposal;
	boolean[] sendProposal;
	
	static int numMessages = 0;
	
	public Agent(int portId, ArrayList<Integer> preference, String[] peers, int[] ports) {
		this.portId = portId;
		this.ports = ports;
		this.peers = peers;
		this.preference = preference;
		this.suggestProposal = new int[peers.length];
		this.gotProposal = new int[peers.length];		
		this.sendProposal = new boolean[peers.length];		
		
		try {
			System.setProperty("java.rmi.server.hostname", this.peers[portId]);
			registry = LocateRegistry.createRegistry(this.ports[portId]);
			stub = (DictatorRMI) UnicastRemoteObject.exportObject(this, this.ports[portId]);
			registry.rebind("Dictator", stub);
		} 
		catch (RemoteException e) {
			e.printStackTrace();
		} 
	}
	
	public Response Call(String rmi, Request req, int id){
        Response callReply = null;
        DictatorRMI stub;
        try {
            Registry registry=LocateRegistry.getRegistry(this.ports[id]);
            stub=(DictatorRMI) registry.lookup("Dictator");
            if(rmi.equals("receiveFinal")) {
            	Agent.numMessages++;
                stub.receiveFinal(req);
            }
            else if(rmi.equals("receiveProposal")) {
            	Agent.numMessages++;
                stub.receiveProposal(req);
            }
            else if(rmi.equals("receiveTentative")) {
            	Agent.numMessages++;
                stub.receiveTentative(req);
            }
            else if(rmi.equals("receiveDeliver")) {
            	Agent.numMessages++;
                stub.receiveDeliver(req);
            }
            else
                System.out.println("Wrong parameters!");
        } catch(Exception e){
        	e.printStackTrace();
        }
        return callReply;
    }
	
	// Receive final
	@Override
	public void receiveFinal(Request req) {
		Request finalReq = new Request(-1, req.clock, req.portId);//House denoted as -1
		finalQueue.add(finalReq);
	}

	// Message delivered
	@Override
	public void receiveDeliver(Request req) {
		// remove the allocated house from your preference list
		if (preference.contains(req.house)) {
			int index = this.preference.indexOf(req.house);
			this.preference.remove(index);
		}
		// remove the allocated agent from queue
		Iterator<Request> iter = finalQueue.iterator();
		while(iter.hasNext()) {
			Request qRequest = iter.next();
			if(qRequest.portId == req.portId) {
				iter.remove();
				break;
			}
		}
	}

	// Receive first message from a process and send back proposal
	@Override
	public void receiveTentative(Request req) {
		synchronized(this){
			this.clock++;
			this.clock = Math.max(this.clock, req.clock);
			this.sendProposal[req.portId] = true;
			this.suggestProposal[req.portId] = this.clock;
		}
	}
	
	// Receive proposals from other processes to which you sent message
	@Override
	public void receiveProposal(Request req) {
		countProposals++;
		proposalMax = Math.max(req.clock, proposalMax);
		this.clock = Math.max(this.clock, proposalMax);
		Request updatedRequest = new Request(req.house, proposalMax, this.portId);
		if (countProposals == this.peers.length) {
			for (int i=0;i<this.peers.length; i++) {
				this.Call("receiveFinal", updatedRequest, i);
			}
			countProposals = 0;
			proposalMax = 0;
		}
	}
	
	//this is called only when your message is the top on in the finalQueue
	public void digestMsg() {
		this.house = preference.get(0);
		this.assigned = true;
		this.finalQueue.remove(0);
		//send deliver msgs to everyone
		Request req = new Request(house, clock, portId);
		for (int i=0;i<this.peers.length; i++) {
			if (i != this.portId) {
				this.Call("receiveDeliver", req, i);
			}
		}
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
	public void run() {
		int currHouse = preference.get(0);
		this.clock++;
	    int max = this.clock + 15;
	    int min = this.clock;
	    int randomNum = min + (int) (Math.random() * (max - min));
	    
		this.clock+=1+randomNum;
		// Send messages to all the processes
		Request req = new Request(currHouse, this.clock, this.portId);
		for (int i=0;i<this.peers.length;i++) {
			this.Call("receiveTentative", req, i);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		boolean canLeave = false;
		while (true) {
			if(finalQueue.size() == 0 && canLeave == true) {
				break;
			}
			if (finalQueue.size() > 0) {
				Collections.sort(finalQueue, new RequestComparator());
				Request top = finalQueue.get(0);
				if (top.portId == this.portId) {
					digestMsg();
					canLeave = true;
				}
			}
			for(int i=0; i<peers.length; i++) {
				if(sendProposal[i] == true) {
					sendProposal[i] = false;
					Request proposalReq = new Request(-1, suggestProposal[i], this.portId);
					this.Call("receiveProposal", proposalReq, i);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	class RequestComparator implements Comparator<Request>{
		@Override
		public int compare(Request o1, Request o2) {
			int clock1 = o1.clock;
			int clock2 = o2.clock;
			if(clock1 == clock2) {
				return(o1.portId-o2.portId);
			}
			return (clock1 - clock2);
		}
	}
}
