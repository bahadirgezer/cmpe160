
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;

import interfaces.IPort;
import ships.Ship;
import containers.Container;
import java.util.ArrayList;

/**
 * This is the port class. Which holds necessary information and methods for a port object.
 * @author Bahadir Gezer
 *
 */
public class Port implements IPort {
	
	/**
	 * The unique ID of this port object.
	 */
	private final int ID;
	
	/**
	 * The X coordinate of this port object.
	 */
	private final double X;
	
	/**
	 * The Y coordinate of this port object.
	 */
	private final double Y;
	
	/**
	 * ArrayList for container objects that are currently on this port.
	 */
	private ArrayList<Container> containers = new ArrayList<Container>();
	
	/**
	 * ArrayList for ship objects that are currently in this port. 
	 */
	private ArrayList<Ship> current = new ArrayList<Ship>();
	
	/**
	 * ArrayList for ship objects that previously visited this port. Thi list 
	 * does not hold duplicates.
	 */
	private ArrayList<Ship> history = new ArrayList<Ship>();
	
	/**
	 * This is a constructor for the Ship class. The parameters should be placed in the order seen below.
	 * in the order seen below.
	 * 
	 * @param ID ID of the newly created port. The first port to be created should have ID of 0, then the 
	 * next ports to come should have an ID 1 up from the previous port.
	 * @param X  the X coordinate of the port.
	 * @param Y  the Y coordinate of the port.
	 */
	public Port(int ID, double X, double Y) {	
		this.ID = ID;
		this.X = X;
		this.Y = Y;
	}
	
	/**
	 * Calculates the distance between two ports.
	 * 
	 * @param other the other port which the distance will be calculated to.
	 * @return The distance between two ports in kilometers.
	 */
	public double getDistance(Port other) {
		return Math.sqrt(Math.pow(other.X - X,2) + Math.pow(other.Y - Y,2));
	}
	
	/**
	 * equals() method for the Port class to heck if two ports
	 * are the same. Since port IDs are unique, this method checks
	 * the port IDs to see if they are the same.
	 * 
	 * @param o the other port to check.
	 * @return True if two ports are the same, False if two ports are different.
	 */
	public boolean equals(Port o) {
		if (o.ID == this.ID) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Adds the incoming ship to the ArrayList current which
	 * contains the current ships that are in the port. If the ship is 
	 * already in the list, it doesn't add a duplicate.
	 */
	@Override
	public void incomingShip(Ship s) {
		if (!(current.contains(s))) {
			current.add(s);
		}
	}
	
	/**
	 * Adds the outgoing ship to the ArrayList history which
	 * contains the previos ships in the port. If the ship is 
	 * already in the list, it doesn't add a duplicate.
	 * 
	 * It will also remove ships from the ArrayList current.
	 */
	@Override
	public void outgoingShip(Ship s) {
		current.remove(s);
		if (!(history.contains(s))) {
			history.add(s);
		}
	}
	
	/**
	 * Getter method for port ID.
	 * 
	 * @return ID of the port.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * toString() method for the Port class.
	 * @return a string which consists of ("Port " + ID + ": (" + String.format("%.2f", X) + ", " + String.format("%.2f", Y) +")")
	 */
	public String toString() {
		return "Port " + ID + ": (" + String.format("%.2f", X) + ", " + String.format("%.2f", Y) +")";
	}
	
	/**
	 * Getter method for the list of ships that are currently in the port.
	 * 
	 * @return The ArrayList named as 'current'.
	 */
	public ArrayList<Ship> getCurrent() {
		return current;
	}
	
	/**
	 * Getter method for the list of containers that are currently in the port.
	 * 
	 * @return The ArrayList named as 'containers'.
	 */
	public ArrayList<Container> getContainers() {
		return containers;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

