
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;

import interfaces.IPort;
import ships.Ship;
import containers.Container;
import java.util.ArrayList;

public class Port implements IPort {
	final int ID;
	final double X, Y;
	public ArrayList<Container> containers = new ArrayList<Container>();
	ArrayList<Ship> history = new ArrayList<Ship>();
	ArrayList<Ship> current = new ArrayList<Ship>();

	
	/**
	 * Creates a Port object. Needs the Port ID and the (x,y) coordinates of the port.
	 * @param ID 
	 * @param X  
	 * @param Y 
	 */
	public Port(int ID, double X, double Y) {
		this.ID = ID;
		this.X = X;
		this.Y = Y;
	}
	
	
	/**
	 * Returns the distance between two ports in kilometers. 
	 * @param other
	 * @return double
	 */
	public double getDistance(Port other) {
		return Math.sqrt(Math.pow(other.X - X,2) + Math.pow(other.Y - Y,2));
	}
	
	/**
	 * Port class has its own equals() method. 
	 * Which checks if two ships are the same ship. 
	 * If they are the same this method returns True, else, it returns False.
	 * @param o
	 * @return
	 */
	public boolean equals(Port o) {
		if (o.ID == this.ID) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * This method adds the incoming ship to the list which
	 * contains the current ships in the port. If the ship is 
	 * already in the list, it does nothing.
	 */
	@Override
	public void incomingShip(Ship s) {
		if (!(current.contains(s))) {
			current.add(s);
		}
	}
	

	/**
	 * This method adds the outgoing ship to the port's ship history,
	 * if it has not been to this port before.
	 * It also removes the ship from the list which contains the current
	 * ships in the port.
	 * 
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
	 * @return
	 */
	public int getID() {
		return ID;
	}
	
	public String toString() {
		return "Port " + ID + ": (" + X + ", " + Y +")";
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

