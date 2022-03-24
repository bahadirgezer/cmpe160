package elements;

/**
 * Order class for the PQoin market. <p>This is the parent class for
 * SellingOrder and BuyingOrder classes.
 * 
 * @author Bahadir Gezer
 *
 */
public class Order {
	
	/**
	 * Amount of PQoin in this order.
	 */
	private double amount;
	
	/**
	 * Price of PQoin for this order. The price is in (dollars/PQoin).
	 */
	private double price;
	
	/**
	 * ID of the trader that generated this order.
	 */
	private int traderID;
	
	/**
	 * Constructor for the Order class.
	 * 
	 * @param traderID ID of the trader that generated this order. 
	 * @param amount Amount of PQoin in this order.
	 * @param price Price of PQoins in this order. The price is in (dollars/PQoin).
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}
	
	/**
	 * Getter for the double amount field. 
	 * 
	 * @return Amount of PQoin in this order.
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Getter for the double price field. 
	 * 
	 * @return Price of PQoin for this order. The price is in (dollars/PQoin).
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Getter for the int traderID field.
	 * 
	 * @return ID of the trader that generated this order.
	 */
	public int getTraderID() {
		return traderID;
	}
	
	/**
	 * toString method for debugging. 
	 * 
	 * @return "P: "+ price+", A: "+ amount+" ID: "+ traderID
	 */
	public String toString() {
		return "P: "+ price+", A: "+ amount+" ID: "+ traderID;
	}
}
