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
	
	private static ArrayList<Port> portList = new ArrayList<Port>();
	private static ArrayList<Ship> shipList = new ArrayList<Ship>();
	private static ArrayList<Container> containerList = new ArrayList<Container>();
	
	public static Port getPort(int portID) {
		Port port = portList.get(portID);
		return port;
	}
	
	/**
	 * Does the general operations to load a container to a ship.
	 * @param port
	 * @param ship
	 * @param container
	 */
	static void containerLoader(Port port, Ship ship, Container container) {
		port.containers.remove(container);
		ship.containers.add(container);
		ship.setTotalFuelConsumptionPerKM(container, 1);
		container.containerSetShip();
		container.setPlaceID(ship.getID());
	}
	
	/**
	 * Executes the necessary operations to load a container to a ship.
	 * Uses the ship.load(container) method to check if the loading operation 
	 * is valid.
	 * 
	 * @param shipID
	 * @param containerID
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
	 * @param port
	 * @param ship
	 * @param container
	 */
	static void containerUnloader(Port port, Ship ship, Container container) {
		port.containers.add(container);
		ship.containers.remove(container);
		ship.setTotalFuelConsumptionPerKM(container, -1);
		container.containerSetPort();
		container.setPlaceID(port.getID());
	}
	
	
	/**
	 * Executes the necessary operations to unload a container to a port.
	 * Uses the ship.unLoad(container) method to check if the unloading 
	 * operation is valid.
	 * 
	 * @param shipID
	 * @param containerID
	 */	
	static void unloading(int shipID, int containerID) {	
		Ship ship = shipList.get(shipID);
		Container container = containerList.get(containerID);
		int contType = container.getType();
		if (ship.unLoad(container)) {
			Port port = portList.get(container.getPlaceID());
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
	 * @param shipID
	 * @param portID
	 */
	static void sailing(int shipID, int portID) {
		Ship ship = shipList.get(shipID);
		Port nextPort = portList.get(portID);
		Port prevPort = ship.getCurrentPort();
		if (ship.sailTo(nextPort)) {
			ship.sailOperations(nextPort);
			prevPort.outgoingShip(ship);
			nextPort.incomingShip(ship);
		}
	}
	
	
	/**
	 * Executes the necessary operations to load more fuel
	 * into the ship.
	 * @param shipID
	 * @param fuel
	 */
	static void addFuel(int shipID, double fuel) {
		shipList.get(shipID).reFuel(fuel);
	}
	
	static void createContainer(int ID, int weight, char type) {
		if (type == 'R') {
			Container container = new RefrigeratedContainer(ID, weight);
			containerList.add(container);
			portList.get(ID).containers.add(container);
		} else if (type == 'L') {
			Container container = new LiquidContainer(ID, weight);
			containerList.add(container);
			portList.get(ID).containers.add(container);
		} else {
			if (weight<=3000) {
				Container container = new BasicContainer(ID, weight);
				containerList.add(container);
				portList.get(ID).containers.add(container);
			} else {
				Container container = new HeavyContainer(ID, weight);
				containerList.add(container);
				portList.get(ID).containers.add(container);
			}
		}
	}
	
	static void createShip(int ID, int maxWeight,
			int maxAll, int maxHeavy, int maxRefrige, 
			int maxLiquid, double fuelCon) {
		Ship ship = new Ship(ID, maxWeight, maxAll, 
				maxHeavy, maxRefrige, maxLiquid, fuelCon);
		shipList.add(ship);
		portList.get(ID).incomingShip(ship);
	}
	
	private static int sequential = 0;
	static void createPort(double X, double Y) {
		Port port = new Port(sequential, X, Y);
		sequential+=1;
		portList.add(port);
	}
	
	

	
	static void input(Scanner in) {
		int N = in.nextInt();
		for (int i=0; i<N+1; i++) {
			String input = in.nextLine();
			//System.out.println("Iteration: "+ i);
			RegularExpressions.inputParser(input);
		}
	}
	
	
	
	
	static void output(PrintStream out) {
		for (Port port: portList) {
			out.println(port.toString());
			printContainers(port.containers, out, 0);
			System.out.println(port.current);
			System.out.println(port.history);
			for (Ship ship: port.current) {
				out.println(ship.toString());
				printContainers(ship.containers, out, 1);

			}
		}
	}
	
	

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
		out.println();
	}
}
