package main;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import ports.Port;
import ships.Ship;
import containers.Container;
import containers.BasicContainer;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
public class Operations{
	
	/**
	 * This ArrayList holds all port objects, in the order of creation.
	 */
	private static ArrayList<Port> portList = new ArrayList<Port>();
	
	/**
	 * This ArrayList holds all ship objects, in the order of creation.
	 */
	private static ArrayList<Ship> shipList = new ArrayList<Ship>();
	
	/**
	 * This ArrayList holds all Container objects, in the order of creation.
	 */
	private static ArrayList<Container> containerList = new ArrayList<Container>();

	/**
	 * Does the general operations needed to load a container to a ship.
	 * 
	 * @param port the port where the container is initially.
	 * @param ship the ship where the container will be placed.
	 * @param container the container which these operations will be carried on.
	 */
	static void containerLoader(Port port, Ship ship, Container container) {
		port.getContainers().remove(container);
		ship.getContainers().add(container);
		ship.setTotalFuelConsumptionPerKM(container, 1);
		container.containerSetShip();
		container.setPlaceID(ship.getID());
	}
	
	/**
	 * Executes the necessary operations to load a container to a ship.
	 * Uses the ship.load(container) method to check if the loading operation 
	 * is valid.
	 * 
	 * This is the method that interacts with the 'outside'. 
	 * It should be used to take the inputs from the RegularExpressions class.
	 * 
	 * @param shipID the ID of the ship which the container will be loaded on to.
	 * @param containerID the ID of the container that will be loaded.
	 */
	static void loading(int shipID, int containerID) {
		Ship ship = shipList.get(shipID);
		Container container = containerList.get(containerID);
		int contType = container.getType();
		if (ship.load(container)) {
			Port port = portList.get(container.getPlaceID());
			if(contType==0) {
				containerLoader(port, ship, container);
				ship.setBasicCont(1);
			} else if (contType==1) {
				containerLoader(port, ship, container);
				ship.setHeavyCont(1);
			} else if (contType==2) {
				containerLoader(port, ship, container);
				ship.setRefrigeCont(1);	
			} else if (contType==3) {
				containerLoader(port, ship, container);
				ship.setLiquidCont(1);
			}
		}
	}
	
	/**
	 * Does the necessary operations to unload a container to a port.
	 * 
	 * @param port the port where the container will be placed.
	 * @param ship the ship where the container is initially.
	 * @param container the container which these operations will be carried on.
	 */
	static void containerUnloader(Port port, Ship ship, Container container) {
		port.getContainers().add(container);
		ship.getContainers().remove(container);
		ship.setTotalFuelConsumptionPerKM(container, -1);
		container.containerSetPort();
		container.setPlaceID(port.getID());
	}
	
	/**
	 * Executes the necessary operations to unload a container to a port.
	 * Uses the ship.unLoad(container) method to check if the unloading 
	 * operation is valid.
	 * 
	 * This is the method that interacts with the 'outside'. 
	 * It should be used to take the inputs from the RegularExpressions class.
	 * 
	 * @param shipID the ID of the ship which the container will be unloaded from.
	 * @param containerID the ID of the container that will be unloaded.
	 */	
	static void unloading(int shipID, int containerID) {	
		Ship ship = shipList.get(shipID);
		Container container = containerList.get(containerID);
		int contType = container.getType();
		if (ship.unLoad(container)) {
			Port port = portList.get(ship.getCurrentPortID());
			if(contType==0) {
				containerUnloader(port, ship, container);
				ship.setBasicCont(-1);
			} else if (contType==1) {
				containerUnloader(port, ship, container);
				ship.setHeavyCont(-1);
			} else if (contType==2) {
				containerUnloader(port, ship, container);
				ship.setRefrigeCont(-1);
			} else if (contType==3) {
				containerUnloader(port, ship, container);
				ship.setLiquidCont(-1);
			}
		}
	}
	
	/**
	 * Executes the necessary operations needed to sail a ship from the
	 * port where it currently is, to a port where it's specified
	 * to go.
	 * 
	 * This is the method that interacts with the 'outside'. 
	 * It should be used to take the inputs from the RegularExpressions class.
	 * 
	 * @param shipID the ID of the ship which will sail.
	 * @param portID the ID of the port which the ship will sail to.
	 */
	static void sailing(int shipID, int portID) {
		Ship ship = shipList.get(shipID);
		Port nextPort = portList.get(portID);
		Port prevPort = portList.get(ship.getCurrentPortID());
		if (ship.sailTo(nextPort)) {
			ship.sailOperations(nextPort);
			prevPort.outgoingShip(ship);
			nextPort.incomingShip(ship);
		}
	}
	
	/**
	 * Executes the necessary operations to load more fuel into the ship.
	 * 
	 * This is the method that interacts with the 'outside'. 
	 * It should be used to take the inputs from the RegularExpressions class.
	 * 
	 * @param shipID the ID of the ship which the fuel should be loaded on to.
	 * @param fuel the amount of the fuel that will be loaded on the ship.
	 */
	static void addFuel(int shipID, double fuel) {
		shipList.get(shipID).reFuel(fuel);
	}
	
	/**
	 * Executes the necessary operations needed to create a new container. It will process the 
	 * inputs and call the correct constructor. 
	 * 
	 * This is the method that interacts with the 'outside'. 
	 * It should be used to take the inputs from the RegularExpressions class.
	 * 
	 * @param ID the ID of the port where the container should be placed initially.
	 * @param weight the weight of the container.
	 * @param type the type of the container. If the type is specified as 'R' or 'L', 
	 * the container will be a refrigerated or liquid container, respectively. If it's 
	 * neither of these values, it will be either a basic or a heavy container. This depends
	 * on their wight values.
	 */
	static void createContainer(int ID, int weight, char type) {
		if (type == 'R') {
			Container container = new RefrigeratedContainer(ID, weight);
			containerList.add(container);
			portList.get(ID).getContainers().add(container);
		} else if (type == 'L') {
			Container container = new LiquidContainer(ID, weight);
			containerList.add(container);
			portList.get(ID).getContainers().add(container);
		} else {
			if (weight<=3000) {
				Container container = new BasicContainer(ID, weight);
				containerList.add(container);
				portList.get(ID).getContainers().add(container);
			} else {
				Container container = new HeavyContainer(ID, weight);
				containerList.add(container);
				portList.get(ID).getContainers().add(container);
			}
		}
	}
	
	/**
	 * Executes the necessary oeprations needed to create a new ship.
	 * 
	 * This is the method that interacts with the 'outside'. 
	 * It should be used to take the inputs from the RegularExpressions class.
	 * 
	 * @param ID the ID of the port where the ship will be placed initially.
	 * @param maxWeight the maximum weight capacity for this ship.
	 * @param maxAll the maximum number of all containers this ship can accomodate. 
	 * @param maxHeavy the maximum number of 'heavy' containers this ship can accomodate.
	 * This value includes heavy, refrigerated and liquid containers.
	 * @param maxRefrige the maximum number of refrigerated containers this ship can accomodate.
	 * @param maxLiquid the maximum number of liquid containers this ship can accomodate.
	 * @param fuelCon the fuelConsumptionPerKM value for this ship.
	 */
	static void createShip(int ID, int maxWeight,
			int maxAll, int maxHeavy, int maxRefrige, 
			int maxLiquid, double fuelCon) {
		Port port = portList.get(ID);
		Ship ship = new Ship(ID, port, maxWeight, maxAll, 
				maxHeavy, maxRefrige, maxLiquid, fuelCon);
		shipList.add(ship);
		port.incomingShip(ship);
	}
	
	/**
	 * This variable is used when creating port Objects. It assures the ordered 
	 * assignment of port IDs.
	 */
	private static int sequential = 0;
	/**
	 * Executes the necessary operations needed to create a new port.
	 * 
	 * This is the method that interacts with the 'outside'. 
	 * It should be used to take the inputs from the RegularExpressions class.
	 * 
	 * @param X the x coordinate of the port.
	 * @param Y the y coordinate of the port.
	 */
	static void createPort(double X, double Y) {
		Port port = new Port(sequential, X, Y);
		sequential+=1;
		portList.add(port);
	}
	
	/**
	 * Executes the necessary input taking operations. This method should be 
	 * used in the main method when taking inputs. This is the only method 
	 * you need to call for the port management system to take its inputs.
	 * 
	 * This method will take the inputs line by line, then pass it on to the
	 * RegularExpressions class, which will parse the input and extract the 
	 * significant parts from it. These significant parts will be used for calling
	 * the relevant functions in the Operations class. The functions in the Operations 
	 * class will connect the 'outside' inputs to the inner workings of the code. Thus
	 * runnning the port management system.
	 * 
	 * @param in the Scanner object which the inputs will be taken from.
	 */
	static void input(Scanner in) {
		int N = in.nextInt();
		for (int i=0; i<N+1; i++) {
			String input = in.nextLine();
			RegularExpressions.inputParser(input);
		}
	}

	/**
	 * Executes the necessary output operations. This method should be 
	 * used in the main method when taking outputs. This is the only method 
	 * you need to call for the port management system to print its outputs.
	 * 
	 * This method will iterate through every port, then through every ship and print 
	 * out their necessary information and their containers. This method uses the
	 * printContainers() method for every ship and port. With this method, the containers will be 
	 * first sorted by their types, then by their IDs. This is the arrangement needed
	 * for the output.
	 * 
	 * @param out the PrintStream object which the outputs will be taken to.
	 */
	static void output(PrintStream out) {
		for (Port port: portList) {
			out.println(port.toString());
			printContainers(port.getContainers(), out, 0);
			Collections.sort(port.getCurrent());
			for (Ship ship: port.getCurrent()) {
				out.println(ship.toString());
				printContainers(ship.getContainers(), out, 1);
			}
		}
	}
	
	/**
	 * This method prints the containers of a ship or a port in a specified way. It is used
	 * with the output() method for every ship and port. 
	 * 
	 * @param containers the ArrayList of containers that will be printed.
	 * @param out the PrintStream which the outputs will be taken to.
	 * @param shipOrPort the integer value to specify if the containers 
	 * belong to a ship or a port. If the value is 1, the containers belongs to
	 * a ship; if the value is 0, the containers belong to a port.
	 */
	static void printContainers(ArrayList<Container> containers, PrintStream out, int shipOrPort) {
		Collections.sort(containers);
		int currentType = -1;
		for (Container container: containers) {
			if (currentType == container.getType()) {
				currentType = container.getType();
				out.print(" "+container.getID());
			} else {
				if (currentType != -1) {
					out.println();
				}
				currentType = container.getType();
				if(shipOrPort==1) {
					out.print("    ");
				} else if (shipOrPort==0) {
					out.print("  ");
				}
				if (currentType == 0) {
					out.print("BasicContainer: "+ container.getID());
				} else if (currentType == 1) {
					out.print("HeavyContainer: "+ container.getID());
				} else if (currentType == 2) {
					out.print("RefrigeratedContainer: "+ container.getID());
				} else if (currentType == 3) {
					out.print("LiquidContainer: "+ container.getID());
				}
			}
		}
		if(!containers.isEmpty()) {
			out.println();
		}
	}
	
	/**
	 * Getter method for a port by its ID.
	 * 
	 * @param portID ID of the port that is demanded.
	 * @return the Port object that is demanded.
	 */
	public static Port getPort(int portID) {
		return portList.get(portID);
	}	
}
