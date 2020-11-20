package dictator;

import java.io.Serializable;

public class Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int house;
	int clock;
	int portId;
	
	public Request(int house, int clock, int portId) {
		this.house = house;
		this.clock = clock;
		this.portId = portId;
	}

}
