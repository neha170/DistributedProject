package CheckerAlgorithm;

import java.io.Serializable;
import java.util.Map;
import java.util.Queue;

public class Response implements Serializable {
    static final long serialVersionUID=2L;

	int agentId;
	int houseId;
	int pref;
	Map<Integer, House> allocation;
    Queue<Integer> queue;
	
	public Response(int agentId, int houseId, int pref) {
		this.agentId = agentId;
		this.houseId = houseId;
		this.pref = pref;
	}
	
	public Response(Queue<Integer> queue, Map<Integer, House> allocation) {
		this.queue = queue;
		this.allocation = allocation;
	}
}
