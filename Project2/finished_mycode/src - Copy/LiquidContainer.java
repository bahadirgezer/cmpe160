
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * Class for LiquidContainer Objects. This class extends the Container.java class.
 * @author Bahadir Gezer
 *
 */
public class LiquidContainer extends HeavyContainer {
	
	/**
	 * The fuel consumption value for every liquid container.
	 */
	private final static double fuelConsumptionLiquid = 4.00;
	
	/**
	 * Constructor for the LiquidContainer class. Takes in two parameters,
	 * calls the super Container() constructor.
	 * 
	 * 
	 * @param ID ID of the port where this container will be placed initially.
	 * @param weight weight of this container object.
	 */
	public LiquidContainer(int ID, int weight) {
		super(ID, weight);
		type=3;
	}
	
	/**
	 * Calculates the total fuel consumption for this container.
	 * 
	 * @return The total consumption of this container. The total consumption
	 * for a container is its weight times its fuelConsumption value.
	 */
	@Override
	public double consumption() {
		return (double)weight*fuelConsumptionLiquid;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

