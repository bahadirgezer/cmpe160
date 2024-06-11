
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public abstract class Container implements Comparable<Container> {
	
	/**
	 * The unique ID of this ship object.
	 */
	private final int ID;
	
	/**
	 * The weight of this container.
	 */
	protected final int weight;
	
	/**
	 * This field specifies if this container object is in a port or on 
	 * a ship. If this field is 0, this container is on a ship; if this 
	 * field is 1, this container is in a port.
	 */
	private int shipOrPort; //0=ship, 1=port
	
	/**
	 * The ID of the place where this container currently is. This number 
	 * does not tell the entire information for the whereabouts of this 
	 * container. To check if this container is in a port or on a ship, 
	 * use the shipOrPort field.
	 */
	private int placeID; 
	
	/**
	 * This variable is used when creating container objects. It assures the ordered 
	 * assignment of container IDs.
	 */
	private static int sequential=0;
	
	/**
	 * This field holds the information for the type of this container. If this field
	 * is 0, the container is a basic container; if it is 1, the container is a heavy 
	 * container; if it is 2, the container is a refrigerated container; if it is 3,
	 * the container is a liquid container.
	 */
	protected int type; //0=basic, 1=heavy, 2=refrigerated, 3=liquid
	
	public Container(int ID, int weight) {
		placeID = ID;
		shipOrPort = 1;
		this.weight = weight;
		this.ID = sequential;
		sequential+=1;
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
	
	/**
	 * equals() method for the Container class. 
	 * Checks if two containenrs are equal by checking their ID, weight and type.
	 * 
	 * @param other container to check.
	 * @return True if the two containers are equal, False if the two containers are different.
	 */
	boolean equals(Container other) {
		if ((type == other.type) && (ID == other.ID) && (weight == other.weight)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to check if the container is in a ship.
	 * 
	 * @return True if the container is in a port, False if the container is in a ship.
	 */
	public boolean isPort() {
		if (shipOrPort == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method to check if the container is in a ship.
	 * 
	 * @return True if the container is in a ship, Flase if the container is in a port.
	 */
	public boolean isShip() {
		if (shipOrPort == 0) {
			return true;
		} else {
			return false;
		}	
	}
	
	/**
	 * Getter method for the placeID of this container.
	 * This method should be used with the isShip() or isPort() method to check the type
	 * of place where it is located, since the placeID
	 * does not convey the whole location of this container.
	 * 
	 * @return the ID of the place where this container is located.
	 */
	public int getPlaceID() {
		return placeID;
	}
	
	/**
	 * Getter method for the ID of this container.
	 *
	 * @return the ID of this container.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Getter method for the type of this container.
	 * type 0 is a basic container,
	 * type 1 is a heavy container,
	 * type 2 is a refrigerated container,
	 * type 3 is a liquid container.
	 * 
	 * @return the type of this container.
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Getter method for the weight of this container.
	 * @return the weight of this container.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Sets the container location as port. 
	 * This method should be used with the setPlaceID() method to fully change the location
	 * of the container.
	 */
	public void containerSetPort() {
		shipOrPort = 1;
	}
	
	/**
	 * Sets the container location as ship. 
	 * This method should be used with the setPlaceID() method to fully change the location
	 * of the container.
	 */
	public void containerSetShip() {
		shipOrPort = 0;
	}
	
	/**
	 * Upadates the placeID with the given parameter.
	 * @param ID the ID for the new place. 
	 */
	public void setPlaceID(int ID) {
		placeID = ID;
	}
	
	/**
	 * toString method for container objects. 
	 * 
	 * @return String consisting of ("Container "+ID+", type "+type)
	 */
	public String toString() {
		return "Container "+ID+", type "+type;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

