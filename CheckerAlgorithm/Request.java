package CheckerAlgorithm;

import java.io.Serializable;
import java.util.Map;
import java.util.Queue;

public class Request implements Serializable{
    static final long serialVersionUID=1L;
    
    Map<Integer, House> allocation;
    Queue<Integer> queue;
   
    public Request(Queue<Integer> queue, Map<Integer, House> allocation) {
    	this.allocation = allocation;
    	this.queue = queue;
	}
}
