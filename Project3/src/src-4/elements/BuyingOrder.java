package elements;
import elements.Market.Pair;

public class BuyingOrder extends Order implements Comparable<BuyingOrder> {

	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}

	public static Pair divideBuying(BuyingOrder initial, double transactionAmount) {
		int traderID = initial.getTraderID();
		double initialAmount = initial.getAmount();
		double price = initial.getPrice();
		
		BuyingOrder transaction = new BuyingOrder(traderID, transactionAmount, price);
		BuyingOrder market = new BuyingOrder(traderID, (initialAmount - transactionAmount), price);
		
		return new Pair(transaction, market);
	}
	
	
	
	@Override
	public int compareTo(BuyingOrder o) {
		double thisPrice = this.getPrice();
		double thisAmount = this.getAmount();
		double oPrice = o.getPrice();
		double oAmount = o.getAmount();
		double thisID = this.getTraderID();
		double oID = o.getTraderID();
		
		if(thisPrice == oPrice && thisAmount == oAmount) {
			if (thisID > oID) {
				return -1;
			} else if (thisID < oID){
				return 1;
			} else {
				return 0;
			}
		}
		
		if (thisPrice == oPrice) {
			if (thisAmount > oAmount) {
				return -1;
			} else {
				return 1;
			}
		}
				
		if (thisPrice > oPrice) {
			return -1;
		} else {
			return 1;
		}
	}
}
