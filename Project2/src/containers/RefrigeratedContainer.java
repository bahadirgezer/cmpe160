
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * Class for REfrigeratedContainer Objects. This class extends the Container.java class.
 * @author Bahadir Gezer
 *
 */
public class RefrigeratedContainer extends HeavyContainer {
	
	/**
	 * The fuel consumption value for every refrigerated container.
	 */
	private final static double fuelConsumptionRefrigerated = 5.00;
	
	/**
	 * Constructor for the RefrigeratedContainer class. Takes in two parameters,
	 * calls the super Container() constructor.
	 * 
	 * 
	 * @param ID ID of the port where this container will be placed initially.
	 * @param weight weight of this container object.
	 */
	public RefrigeratedContainer(int ID, int weight) {
		super(ID, weight);
		type=2;
	}
	
	/**
	 * Calculates the total fuel consumption for this container.
	 * 
	 * @return The total consumption of this container. The total consumption
	 * for a container is its weight times its fuelConsumption value.
	 */
	@Override
	public double consumption() {
		return (double)weight*fuelConsumptionRefrigerated;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

