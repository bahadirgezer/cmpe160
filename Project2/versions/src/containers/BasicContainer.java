
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public class BasicContainer extends Container{
	static double fuelConsumptionBasic = 2.50;
	static int type = 0;
	
	public BasicContainer(int ID, int weight) {
		super(ID, weight);
		type = 0;
	}

	@Override
	public double consumption() {
		return (double)weight*fuelConsumptionBasic;
	}

	@Override
	public int getType() {
		return type;
	}


}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

