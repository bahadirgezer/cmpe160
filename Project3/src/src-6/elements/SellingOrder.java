package elements;
import elements.Market.Pair;

/**
 * This class extends the Order class and implements the Caomparable interface.
 * The comparable interface is used for the sellingOrders PriortiyQueue
 * in the market. 
 * <p>
 * This class is used as a container for sell orders.
 * 
 * @author Bahadir Gezer
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder> {
	
	/**
	 * Constructor for the SellingOrder class. Calls the super 
	 * constructor of the parent Order class.
	 * 
	 * @param traderID ID of the trader that generated this order. 
	 * @param amount Amount of PQoin in this order.
	 * @param price Price of PQoins in this order. The price is in (dollars/PQoin).
	 */
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	
	/**
	 * This method is used for dividing an order in an indicated amount.
	 * <p>When an order needs to be split for a transaction to occur, this method 
	 * should be used.
	 * 
	 * @param initial The SellingOrder that needs to be divided.
	 * @param transactionAmount The amount of PQoin which the corresponding
	 * BuyingOrder object needs.
	 * @return a Pair of SellingOrder objects. These orders are 
	 * accessed using the methods in the Pair class. 
	 */
	public static Pair divideSelling(SellingOrder initial, double transactionAmount) {
		int traderID = initial.getTraderID();
		double initialAmount = initial.getAmount();
		double price = initial.getPrice();
		
		SellingOrder transaction = new SellingOrder (traderID, transactionAmount, price);
		SellingOrder market = new SellingOrder (traderID, (initialAmount - transactionAmount), price);
		
		return new Pair(transaction, market);
	}
	
	/**
	 * compareTo method for SellingOrder objects. <p>
	 * It will sort SellingOrder objects first by their price in ascending order.
	 * If prices are equal, the order with the higher amount goes to the front.
	 * If the amounts are also equal, the order with the lower traderID 
	 * goes to the front.
	 */
	@Override
	public int compareTo(SellingOrder o) {
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
			return 1;
		} else {
			return -1;
		}
		
	}
	
}
