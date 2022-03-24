
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public class LiquidContainer extends HeavyContainer {
	static double fuelConsumptionLiquid = 4.00;
	static int type = 3;
	
	public LiquidContainer(int ID, int weight) {
		super(ID, weight);
		type = 3;
	}
	
	@Override
	public double consumption() {
		return (double)weight*fuelConsumptionLiquid;
	}

	@Override
	public int getType() {
		return type;
	}


}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

