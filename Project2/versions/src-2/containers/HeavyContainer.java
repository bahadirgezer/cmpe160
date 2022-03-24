
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public class HeavyContainer extends Container {
	static double fuelConsumptionHeavy = 3.00;

	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
		type=1;
	}

	@Override
	public double consumption() {
		return (double)weight*fuelConsumptionHeavy;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

