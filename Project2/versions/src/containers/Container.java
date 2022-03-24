
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public abstract class Container {
	final int ID;
	final int weight;
	//if type 0 is basic, type 1 is heavy, type 2 refrigerated, type 3 is liquid
	int shipOrPort; // 0=ship, 1=port
	int placeID; //ID of the current place,
	private int sequential=0;
	int type;
	
	public Container(int ID, int weight) {
		placeID = ID;
		this.weight = weight;
		this.ID = sequential;
		sequential+=1; //does this work?
	}
	
	public abstract double consumption();
	public abstract int getType();
	
	boolean equals(Container other) {
		if ((type == other.type) && (ID == other.ID) && (weight == other.weight)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	/**
	 * Returns true if the container is in a port.
	 * @return
	 */
	public boolean isPort() {
		if (shipOrPort == 1) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Returns true if the container is in a port.
	 * @return
	 */
	public boolean isShip() {
		if (shipOrPort == 0) {
			return true;
		} else {
			return false;
		}	
	}
	/**
	 * Returns the ID of the place where it is located.
	 * The place could be a ship or a port.
	 * So you should check that first.
	 * @return
	 */
	public int getPlaceID() {
		return placeID;
	}
	public int getWeight() {
		return weight;
	}

}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

