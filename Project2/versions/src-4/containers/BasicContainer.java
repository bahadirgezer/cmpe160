
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * Class for BasicContainer Objects. This class extends the Container.java class.
 * @author Bahadir Gezer
 *
 */
public class BasicContainer extends Container{
	
	/**
	 * The fuel consumption value for every basic container.
	 */
	private final static double fuelConsumptionBasic = 2.50;
	
	/**
	 * Constructor for the BasicContainer class. Takes in two parameters,
	 * calls the super Container() constructor.
	 * 
	 * 
	 * @param ID ID of the port where this container will be placed initially.
	 * @param weight weight of this container object.
	 */
	public BasicContainer(int ID, int weight) {
		super(ID, weight);
		type=0;
	}
	
	/**
	 * Calculates the total fuel consumption for this container.
	 * 
	 * @return The total consumption of this container. The total consumption
	 * for a container is its weight times its fuelConsumption value.
	 */
	@Override
	public double consumption() {
		return (double)weight*fuelConsumptionBasic;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

