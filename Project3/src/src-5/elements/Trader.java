package elements;

/**
 * Trader class for the PQoin market. <p>This class holds all relevant information 
 * and methods for traders.
 * 
 * @author Bahadir Gezer
 *
 */
public class Trader {
	
	/**
	 * The ID of this trader. <p>This value is unique for each trader in the market.
	 */
	private int id;
	
	/**
	 * Wallet object for this trader. <p>This wallet holds all information methods 
	 * about the dollars and coins of this trader.
	 */
	private Wallet wallet;
	
	/**
	 * The purpose of this field is to ensure the sequential assignment of traderIDs.
	 * <p>It is incremented each time when a new trader is created.
	 */
	public static int numberOfUsers = 0;
	
	/**
	 * Constructor for the Trader class. <p>This method will create the wallet of this 
	 * trader. It will add the initial amount of dollars and coins that this trader
	 * brings in.
	 * 
	 * @param dollars Initial amount of dollars that this trader brings in to the market.
	 * @param coins Initial amount of coins that this trader brings in to the market.
	 */
	public Trader(double dollars, double coins) {
		wallet = new Wallet(dollars, coins);
		id = numberOfUsers;
		numberOfUsers += 1;
	}
	
	/**
	 * Sell method for this trader class. <p>This method is called from the main class.
	 * This method will call the wallet.sellOperation() and market.giveSellOrder() 
	 * methods. <p>The sellOperation() method will return true if the sell operation is 
	 * valid, if it is invalid, the number of invalid operations will be incremented.
	 * <p>The giveSellOrder() method will pass on the SellingOrder object to the market 
	 * class. After that, the market class will do the relevant operations for this 
	 * sell order.
	 * 
	 * 
	 * @param amount Amount of PQoins for the selling operation.
	 * @param price Price for the selling operation. This price value is in (dollars/PQoins).
	 * @param market Market im which the selling operation is conducted.
	 * @return A default value of 0, I did not use the return value to check validity.
	 */
	public int sell(double amount, double price, Market market) {
		if (wallet.sellOperation(amount)) {
			SellingOrder order = new SellingOrder(id, amount, price);
			market.giveSellOrder(order);
		}
		
		return 0;
	}
	
	/**
	 * Buy method for this trader class. <p>This method is called from the main class.
	 * This method will call the wallet.buyOperation() and market.giveBuyOrder() 
	 * methods. <p>The buyOperation() method will return true if the buy operation is 
	 * valid, if it is invalid, the number of invalid operations will be incremented.
	 * <p>The giveBuyOrder method will pass on the BuyingOrder object to the market 
	 * class. After that, the market class will do the relevant operations for this 
	 * buy order.
	 * 
	 * 
	 * @param amount Amount of PQoins for the buying operation.
	 * @param price Price for the buying operation. This price value is in (dollars/PQoins).
	 * @param market Market im which the buying operation is conducted.
	 * @return A default value of 0, I did not use the return value to check validity.
	 */
	public int buy(double amount, double price, Market market) {
		if (wallet.buyOperation(amount, price)) {
			BuyingOrder order = new BuyingOrder(id, amount, price);
			market.giveBuyOrder(order);
		}
		
		return 0;
	}
	
	/**
	 * This method adds the specified amount of PQoins to the wallet of this trader.
	 * 
	 * @param amount Amount of PQoins to be added to the wallet of this trader.
	 */
	public void addPQ(double amount) {
		wallet.addPQ(amount);
	}
	
	/**
	 * This method is called when a transaction takes place. <p>It should be called for the
	 * trader that is buying. Since transactions concern the amount of coins and dollars 
	 * in the wallet, this methods will call the wallet.buyTransaction() method which will
	 * do the necessary operations for a buying transaction.
	 * 
	 * @param amount Amount of PQoin that is bought in the transaction.
	 * @param buyerPrice The original amount which this trader payed when creating
	 * this buyingOrder. If the buyerPrice is higher than the realPrice, the excess 
	 * amount will be refunded.
	 * @param realPrice The price of PQoins for this transaction.
	 */
	public void buyTransaction(double amount, double buyerPrice, double realPrice) {
		wallet.buyTransaction(amount, buyerPrice, realPrice);
	}
	
	/**
	 * This method is called when a transaction takes place.<p> It should be called for the
	 * trader that is selling. Since transactions concern the amount of coins and dollars 
	 * in the wallet, this methods will call the wallet.sellTransaction() method which will
	 * do the necessary operations for a selling transaction.
	 * 
	 * @param amount Amount of PQoin that is sold in the transaction.
	 * @param price The price of PQoins for this transaction.
	 */
	public void sellTransaction(double amount, double price) {
		wallet.sellTransaction(amount, price);
	}

	/**
	 * This method will add the specified amount of dollars to the wallet of this trader.
	 * 
	 * @param amount Amount of dollars to be deposited to the wallet of this trader.
	 */
	public void deposit(double amount) {
		wallet.deposit(amount);
	}
	
	/**
	 * This method will withdraw the specified amount of dollar from the wallet of this trader.
	 * 
	 * @param amount Amount of dollars to be withdrawn from the wallet of this trader.
	 */
	public void withdraw(double amount) {
		wallet.withdraw(amount);
	}
	
	/**
	 * toString method for this trader.<p> It calls the wallet.toString() method, which will
	 * return the dollar and PQoin amounts as a string in the necessary format. 
	 * 
	 * @return"Trader "+id+": "+wallet.toString()
	 */
	public String toString() {
		return "Trader "+id+": "+wallet.toString();
	}
}
