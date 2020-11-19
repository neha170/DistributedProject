package CheckerAlgorithm;

import java.io.Serializable;

public class House implements Serializable{
    static final long serialVersionUID=5L;

	int houseId;
	int pref;
	
	public House(int houseId, int pref) {
		this.houseId = houseId;
		this.pref = pref;
	}
}
