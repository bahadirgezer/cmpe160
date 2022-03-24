
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public class RefrigeratedContainer extends HeavyContainer {
	static double fuelConsumptionRefrigerated = 5.00;
	
	public RefrigeratedContainer(int ID, int weight) {
		super(ID, weight);
		type=2;
	}
	
	@Override
	public double consumption() {
		return (double)weight*fuelConsumptionRefrigerated;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

