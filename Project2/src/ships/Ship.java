
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;

import containers.Container;
import containers.containerSortID;
import interfaces.IShip;
import main.Operations;
import ports.Port;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This is the ship class. Which holds necessary information and methods for a ship object.
 * @author Bahadir Gezer
 *
 */
public class Ship implements IShip, Comparable<Ship> {
	
	/**
	 * The unique ID of this ship object.
	 */
	private final int ID;
	
	/**
	 * The current amount of fuel in this ship.
	 */
	private double fuel;
	
	/**
	 * Fuel consumption per KM value for this ship.
	 */
	@SuppressWarnings("unused")
	private final double fuelConsumptionPerKM;
	
	/**
	 * The ID of the current port which the ship is docked at.
	 */
	private int currentPortID;
	
	/**
	 * The maximum weight of all containers on this ship.
	 */
	private final int totalWeightCapacity;
	
	/**
	 * The maximum number of every container which this ship can hold.
	 */
	private final int maxNumberOfAllContainers;
	
	/**
	 * The maximum number of 'heavy' containers which this ship can hold. 'Heavy' containers
	 * include refrigerated, liquid and heavy container objects.
	 */
	private final int maxNumberOfHeavyContainers;
	
	/**
	 * The maximum number of refrigerated containers which this ship can hold.
	 */
	private final int maxNumberOfRefrigeratedContainers; 
	
	/**
	 * The maximum number of liquid containers which this ship can hold.
	 */
	private final int maxNumberOfLiquidContainers;
	
	/**
	 * Current weight of all containers on this ship. This value should not exceed the 
	 * totalWeightCapacity value.
	 */
	private int currWeight=0;
	
	/**
	 * Current number of every container on this ship. This value should not exceed the 
	 * maxNumberOfAllContainers value.
	 */
	private int currAllCont=0; 
	
	/**
	 * Current number of 'heavy' containers on this ship. 'Heavy' containers consist of
	 * refrigerated, liquid and heavy container objects. This value should not exceed
	 * the maxNumberOfHeavyContainers value.
	 */
	private int currHeavyCont=0;
	
	/**
	 * Current number of refrigerated containers on this ship. This value should not
	 * exceed the maxNumberOfRefrigeratedContainers value.
	 */
	private int currRefrigeCont=0;
	
	/**
	 * Current number of liquid containers on this ship. This value should not
	 * exceed the maxNumberOfLiquidContainers value.
	 */
	private int currLiquidCont=0;
	
	/**
	 * This variable is used when creating ship objects. It assures the ordered 
	 * assignment of ship IDs.
	 */
	private static int sequential=0;
	
	/**
	 * This field holds the current fuel consuption per KM value for this ship.
	 * This value includes the original fuelConsumptionPerKM value and the fuel
	 * consumption of every container that is currently on this ship.
	 */
	private double totalFuelConsumptionPerKM;
	
	/**
	 * This object holds the Port object where this ship is currently docked.
	 */
	@SuppressWarnings("unused")
	private Port currentPort;
	
	/**
	 * ArrayList for container objects that are currently on this ship.
	 */
	private ArrayList<Container> containers = new ArrayList<Container>();
	
	
	/** 
	 * Getter method for the containers ArrayList of this ship.
	 * @return
	 */
	public ArrayList<Container> getCurrentContainers(){
		Collections.sort(containers, new containerSortID());
		return containers;
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
	public int compareTo(Ship ship) {
		return ID - ship.ID;
	}
	
	/**
	 * This is a constructor for the Ship class. The parameters should be placed
	 * in the order seen below.
	 * 
	 * @param ID the specific ID for the ship
	 * @param p the port where the ship is initially
	 * @param totalWeightCapacity total weight the ship can hold
	 * @param maxNumberOfAllContainers maximum number of containers the ship can hold
	 * @param maxNumberOfHeavyContainers maximum number of heavy containers the ship can hold
	 * @param maxNumberOfRefrigeratedContainers maximum number of refrigerated containers the ship can hold
	 * @param maxNumberOfLiquidContainers maximum number of liquid containers the ship can hold
	 * @param fuelConsumptionPerKM the fuel consumption of the ship per kilometers
	 */
	public Ship(int ID, Port p,
			int totalWeightCapacity, 
			int maxNumberOfAllContainers, 
			int maxNumberOfHeavyContainers, 
			int maxNumberOfRefrigeratedContainers, 
			int maxNumberOfLiquidContainers, 
			double fuelConsumptionPerKM) {
		currentPortID = ID;
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		totalFuelConsumptionPerKM = fuelConsumptionPerKM;
		currentPort = Operations.getPort(ID);
		this.ID = sequential;
		sequential+=1;
		fuel = 0.00;
		}

	/**
	 * Checker method to see if the container can be loaded to the ship.
	 * It is used with the loading(int shipID, int containerID) method in the 
	 * Operations.java class.
	 * 
	 * 
	 * @return False if the conditions don't allow the container to be loaded,
	 * True if the conditions allow the container to be loaded.
	 */
	@Override
	public boolean load(Container cont) {
		if ((cont.isPort()) && (cont.getPlaceID() == currentPortID)) {
			int wouldBeWeight = currWeight + cont.getWeight();
			int contType = cont.getType();
			if ((wouldBeWeight <= totalWeightCapacity) && (allOK())) {
				if(contType == 0) {
					return true;
				} else if (contType == 1) {
					if(heavyOK()) {
						return true;
					} else {
						return false;
					}
				} else if (contType == 2) {
					if(refrigeOK() && heavyOK()) {
						return true;
					} else {
						return false;
					}
				} else if (contType == 3) {
					if(liquidOK() && heavyOK()) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Checker method to see if the container can be unloaded to the port.
	 * It is used with the unloading(int shipID, int containerID) method in the 
	 * Operations.java class.
	 * 
	 * 
	 * @return False if the conditions don't allow the container to be unloaded, 
	 * True if the conditions allow the container to be unloaded.
	 */
	@Override
	public boolean unLoad(Container cont) {
		if (cont.isShip() && (cont.getPlaceID() == ID)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if loading any container is OK.
	 * 
	 * @return True if it is suitable to load any container, False if it's
	 * prohibited to load any container.
	 */
	public boolean allOK() {
		if (currAllCont < maxNumberOfAllContainers) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if loading a heavy container is OK.
	 * 
	 * @return True if it is suitable to load a heavy container, False if it's 
	 * prohibited to load a heavy container.
	 */
	public boolean heavyOK() {
		if (currHeavyCont < maxNumberOfHeavyContainers) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if loading a refrigerated container is OK.
	 * 
	 * @return True if it is suitable to load a refrigerated container, False if it's
	 * prohibited to load a refrigerated container.
	 */
	public boolean refrigeOK() {
		if (currRefrigeCont < maxNumberOfRefrigeratedContainers) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if loading a liquid container is OK.
	 * 
	 * @return True if it is suitable to load a liquid container, False if it's 
	 * prohibited to load a liquid container.
	 */
	public boolean liquidOK() {
		if (currLiquidCont < maxNumberOfLiquidContainers) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Calculates and returns the distance to a port -specified
	 * in the parameter-  to the ship.
	 * 
	 * @param other port which the distance will be calculated.
	 * @return The distance to the port in the parameter.
	 */
	private double getPortDistance(Port other) {
		Port port = Operations.getPort(currentPortID);
		double distance = port.getDistance(other);
 		return distance;
	}
	
	/**
	 * Executes the necessary operations for a ship to sail.
	 * 
	 * @param p the port which the ship will sail.
	 */
	public void sailOperations(Port p) {
		fuel -= getPortDistance(p)*totalFuelConsumptionPerKM;
		currentPortID = p.getID();
		currentPort = p;
	}

	/**
	 * Checks if it is suitalbe to sail to the port specified in the parameters.
	 * 
	 * @param p the port which the ship will sail.
	 * @return True if it is suitalbe to sail to the specified port, False if it's 
	 * not suitalbe to sail to the specified port.
	 */
	@Override
	public boolean sailTo(Port p) {
		if (getPortDistance(p)*totalFuelConsumptionPerKM<=fuel) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds fuel to the ship.
	 * @param newFuel adds this amount to the total fuel onboard.
	 */
	@Override
	public void reFuel(double newFuel) {
		fuel += newFuel;
	}
	
	/**
	 * Adds the newWeight parameter to the current weight of the ship. If you want to 
	 * subtract weight from the ship; the newWeight parameter should be negative.
	 * 
	 * @param newWeight weight change in the ship. Should be negative if the ship lost weight.
	 */
	public void addCurrWeight(int newWeight) {
		currWeight+=newWeight;
	}

	/**
	 * Getter for currentPortID
	 * @return currentPortID
	 */
	public int getCurrentPortID() {
		return currentPortID;
	}
	
	/**
	 * Getter method for ship ID.
	 * @return ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Refreshes the fuel consumption per KM of a ship if a container
	 * is loaded or unloaded.
	 * 
	 * @param cont the container object which the operation is done.
	 * @param addOrSubtract specifies if the fuel should be added (1) or subtracted (-1).
	 */
	public void setTotalFuelConsumptionPerKM(Container cont, int addOrSubtract) {
		if (addOrSubtract == 1) {
			totalFuelConsumptionPerKM += cont.consumption();
		} else if (addOrSubtract == -1) {
			totalFuelConsumptionPerKM -= cont.consumption();
		}
	}
	
	/**
	 * If the addOrSubtract parameter is '1' it adds one container.
	 * If it's '-1' it subtracts one container.
	 * 
	 * @param addOrSubtract specifies if the container should be added (1) or subtracted (-1).
	 */
	public void setBasicCont(int addOrSubtract) {
		if (addOrSubtract == 1) {
			currAllCont+=1;
		} else if (addOrSubtract == -1) {
			currAllCont-=1;
		}
	}
	
	/**
	 * If the addOrSubtract parameter is '1' it adds one container.
	 * If it's '-1' it subtracts one container.
	 * 
	 * @param addOrSubtract specifies if the container should be added (1) or subtracted (-1).
	 */
	public void setHeavyCont(int addOrSubtract) {
		if (addOrSubtract == 1) {
			currAllCont+=1;
			currHeavyCont+=1;
		} else if (addOrSubtract == -1) {
			currAllCont-=1;
			currHeavyCont-=1;
		}	
	}
	
	/**
	 * If the addOrSubtract parameter is '1' it adds one container.
	 * If it's '-1' it subtracts one container.
	 * It also changes currHeavyCont since refrigerated containers are considered 
	 * to be heavy containers.
	 *  
	 * @param addOrSubtract specifies if the container should be added (1) or subtracted (-1).
	 */
	public void setRefrigeCont(int addOrSubtract) {
		if (addOrSubtract == 1) {
			currAllCont+=1;
			currHeavyCont+=1;
			currRefrigeCont+=1;
		} else if (addOrSubtract == -1) {
			currAllCont-=1;
			currHeavyCont-=1;	
			currRefrigeCont-=1;
		}	
	}
	
	/**
	 * If the addOrSubtract parameter is '1' it adds one container.
	 * If it's '-1' it subtracts one container.
	 * It also changes currHeavyCont since liquid containers are considered 
	 * to be heavy containers.
	 * 
	 * @param addOrSubtract specifies if the container should be added (1) or subtracted (-1).
	 */
	public void setLiquidCont(int addOrSubtract) {
		if (addOrSubtract == 1) {
			currAllCont+=1;
			currHeavyCont+=1;
			currLiquidCont+=1;
		} else if (addOrSubtract == -1) {
			currAllCont-=1;
			currHeavyCont-=1;	
			currLiquidCont-=1;
		}	
	}
	
	/**
	 * toString() method for outputting a ship object.
	 * @return "  Ship " + ID + ": " +  String.format("%.2f", fuel)
	 */
	public String toString() {
		return "  Ship " + ID + ": " +  String.format("%.2f", fuel);
	}
	
	/**
	 * Getter method for the list of containers that are currently on the ship. This 
	 * method is slightly different from the getCurrentContainers() method, because
	 * this method does not sort the containers every time it is called. So this method
	 * should be used when it is not necessary to sort the containers.
	 * 
	 * @return The ArrayList named as 'containers'.
	 */
	public ArrayList<Container> getContainers() {
		return containers;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

