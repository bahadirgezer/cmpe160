
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;

import containers.Container;
import interfaces.IShip;
import main.Operations;
import ports.Port;
import java.util.ArrayList;


public class Ship implements IShip, Comparable<Ship> {
	final int ID;
	private double fuel;
	final double fuelConsumptionPerKM;
	private Port currentPort;
	private int currentPortID;
	private final int totalWeightCapacity, maxNumberOfAllContainers, maxNumberOfHeavyContainers, maxNumberOfRefrigeratedContainers, maxNumberOfLiquidContainers;
	public ArrayList<Container> containers = new ArrayList<Container>();
	private int currWeight=0, currAllCont=0, currHeavyCont=0, currRefrigeCont=0, currLiquidCont=0;
	private static int sequential=0;
	double totalFuelConsumptionPerKM;
	

	///
	ArrayList<Container> getCurrentContainers(){
		return containers;
	}
	///
	
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
	public Ship(int ID,
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
		currentPort = Operations.getPort(currentPortID);
		totalFuelConsumptionPerKM = fuelConsumptionPerKM;
		this.ID = sequential;
		sequential+=1;
		fuel = 0.00;
		
		System.out.println("currentID"+currentPortID+" currentPort "+ currentPort);
	}

	
	
	@Override
	public boolean sailTo(Port p) {
		if (currentPort.getDistance(p)*totalFuelConsumptionPerKM<=fuel) {
			return true;
		} else {
			return false;
		}
	}
	
	public void sailOperations(Port p) {
		fuel -= currentPort.getDistance(p)*totalFuelConsumptionPerKM;
		currentPort = p;
		currentPortID = p.getID();
	}
	
	/**
	 * Adds fuel to the ship.
	 */
	@Override
	public void reFuel(double newFuel) {
		fuel += newFuel;
	}

	
	
	
	/**
	 * Checker method to see if the container can be loaded to the ship.
	 * It is used with the loadingOK(int shipID, int containerID) method in the 
	 * Operations.java class.
	 * 
	 * 
	 * @return False if the conditions don't allow the container to be loaded.
	 * True if the conditions allow the container to be loaded.
	 */
	
	public boolean load(Container cont) {
		if ((cont.isPort()) && (cont.getPlaceID() == currentPort.getID())) {
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
					if(refrigeOK()) {
						return true;
					} else {
						return false;
					}
				} else if (contType == 3) {
					if(liquidOK()) {
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
	

	@Override
	public boolean unLoad(Container cont) {
		if (cont.isShip() && (cont.getPlaceID() == ID)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Getter method for ship ID.
	 * @return
	 */
	public int getID() {
		return ID;
	}
	
	public int getCurrentPortID() {
		return currentPort.getID();
	}
	
	
	
	public boolean allOK() {
		if (currAllCont < maxNumberOfAllContainers) {
			return true;
		} else {
			return false;
		}
	}
	public boolean heavyOK() {
		if (currHeavyCont < maxNumberOfHeavyContainers) {
			return true;
		} else {
			return false;
		}
	}
	public boolean refrigeOK() {
		if (currRefrigeCont < maxNumberOfRefrigeratedContainers) {
			return true;
		} else {
			return false;
		}
	}
	public boolean liquidOK() {
		if (currLiquidCont < maxNumberOfLiquidContainers) {
			return true;
		} else {
			return false;
		}
	}

	public Port getCurrentPort() {
		return currentPort;
	}
	public int getCurrWeight() {
		return currWeight;
	}
	public int getCurrAllCont() {
		return currAllCont;
	}
	public int getCurrHeavyCont() {
		return currHeavyCont;
	}
	public int getCurrRefrigeCont() {
		return currRefrigeCont;
	}
	public int getCurrLiquidCont() {
		return currLiquidCont;
	}
	public int getTotalWeightCapacity() {
		return totalWeightCapacity;
	}
	public int getMaxNumberOfAllContainers() {
		return maxNumberOfAllContainers;
	}
	public int getMaxNumberOfHeavyContainers() {
		return maxNumberOfHeavyContainers;
	}
	public int getMaxNumberOfRefrigeratedContainers(){
		return maxNumberOfRefrigeratedContainers;
	}
	public int getMaxNumberOfLiquidContainers(){
		return maxNumberOfLiquidContainers;
	}
	
	
	/**
	 * Adds the parameter to the current weight. If you want to subtract weight 
	 * from the ship; the newWeight parameter should be negative.
	 * @param newWeight
	 */
	public void addCurrWeight(int newWeight) {
		currWeight+=newWeight;
	}
	
	/**
	 * Refreshes the fuel consumption per KM of a ship if a container
	 * is loaded or unloaded.
	 * 
	 * @param cont
	 * @param addOrSubtract
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
	 * @param addOrSubtract
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
	 * @param addOrSubtract
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
	 * 
	 * @param addOrSubtract
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
	 * 
	 * @param addOrSubtract
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
	
	public String toString() {
		return "  Ship " + ID + ": " +  String.format("%.2f", fuel);
	}

	
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

