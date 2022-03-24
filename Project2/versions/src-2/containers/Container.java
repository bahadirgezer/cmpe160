
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public abstract class Container implements Comparable<Container> {
	final int ID;
	final int weight;
	//if type 0 is basic, type 1 is heavy, type 2 refrigerated, type 3 is liquid
	private int shipOrPort; // 0=ship, 1=port
	private int placeID; //ID of the current place,
	private static int sequential=0;
	protected int type;
	
	
	public Container(int ID, int weight) {
		placeID = ID;
		shipOrPort = 1;
		this.weight = weight;
		this.ID = sequential;
		sequential+=1;
	}
	
	public String toString() {
		return "Container "+ ID;
	}
	
	/**
	 * Created for the Comparable interface. Sorts the containers first
	 * by type, then by ID. Thus, it's used when creating the output for the 
	 * containers of a port or a ship.
	 * 
	 * @param container
	 * @return The necessary integers for sorting the containers in the specified way.
	 */
	@Override
	public int compareTo(Container container) {
		if (container.type < type) {
			return 1;
		} else if (container.type > type) {
			return -1;
		} else {
			return ID - container.ID;
		}
	}
	
	public abstract double consumption();
	
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
	public int getID() {
		return ID;
	}
	public void setType(int type) {
		this.type = 0;
	}
	public int getType() {
		return type;
	}
	public int getWeight() {
		return weight;
	}
	
	public void containerSetPort() {
		shipOrPort = 1;
	}
	
	public void containerSetShip() {
		shipOrPort = 0;
	}
	
	public void setPlaceID(int ID) {
		placeID = ID;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

