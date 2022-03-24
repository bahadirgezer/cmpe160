
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * Class for HeavyContainer Objects. This class extends the Container.java class.
 * @author Bahadir Gezer
 *
 */
public class HeavyContainer extends Container {
	
	/**
	 * The fuel consumption value for every heavy container.
	 */
	private final static double fuelConsumptionHeavy = 3.00;

	/**
	 * Constructor for the HeavyContainer class. Takes in two parameters,
	 * calls the super Container() constructor.
	 * 
	 * 
	 * @param ID ID of the port where this container will be placed initially.
	 * @param weight weight of this container object.
	 */
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
		type=1;
	}

	/**
	 * Calculates the total fuel consumption for this container.
	 * 
	 * @return The total consumption of this container. The total consumption
	 * for a container is its weight times its fuelConsumption value.
	 */
	@Override
	public double consumption() {
		return (double)weight*fuelConsumptionHeavy;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

