package elements;

/**
 * Wallet class for the Trader objects in the PQoin market. <p> This class is used for holding 
 * dollar and coin amounts and operations for each Trader. It also holds blocked dollars and coins, which
 * get updated as a result of orders or transaction operations.
 * 
 * @author Bahadir Gezer
 *
 */
public class Wallet {
	
	/**
	 * The amount of dollars which this wallet holds.
	 */
	private double dollars;
	
	/**
	 * The amount of coins which this wallet holds.
	 */
	private double coins;
	
	/**
	 * The amount of blocked dollars which this wallet holds.<p> This value changes with order operations
	 * or transaction operations.
	 */
	private double blockedDollars;
	
	/**
	 * The amount of blocked coins which this wallet holds. <p> This value changes with order operations 
	 * or transaction operations.
	 */
	private double blockedCoins;
	
	/**
	 * Constructor for the Wallet object. <p> The initial amount of blocked dollars and coins are assigned
	 * as 0. 
	 * 
	 * @param dollars The initial amount of dollars which the Trader of this Wallet hold. 
	 * @param coins The initial amount of coins which the Trader of this Wallet hold.
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
		blockedDollars = 0;
		blockedCoins = 0;
	}
	
	/**
	 * Buying operation method for the Wallet class. <p> This method checks if the operation is valid. <p> If 
	 * the operation is valid, it will conduct the necessary operations -updating dollars and blocked 
	 * dollars- needed for a buying operation. If the operation is not valid it will increment the number of
	 * invalid queries.
	 * 
	 * @param amount The amount of PQoins bought by the Trader of this Wallet.
	 * @param price The price at which these PQoins are bought by this Trader. Price of PQoins is in 
	 * (dollars/PQoins).
	 * @return True if the buy operation is valid; false if the buy operation is invalid.
	 */
	public boolean buyOperation(double amount, double price) {
		double volume = amount * price;
		if (dollars >= volume) {
			dollars -= volume;
			blockedDollars += volume;
			return true;
		} else {
			Market.invalidQueries += 1;
			return false;
		}
	}

	/**
	 * Selling operation method for the Wallet class. <p> This method checks if the operation is valid. <p>If 
	 * the operation is valid, it will conduct the necessary operations -updating coins and blocked 
	 * coins- needed for a selling operation. If the operation is not valid it will increment the number of
	 * invalid queries.
	 * 
	 * @param amount The amount of PQoins sold by the Trader of this Wallet.
	 * @param price The price at which these PQoins are sold by this Trader. Price of PQoins is in 
	 * (dollars/PQoins).
	 * @return True if the sell operation is valid; false if the sell operation is invalid.
	 */
	public boolean sellOperation(double amount) {
		if (coins >= amount) {
			coins -= amount;
			blockedCoins += amount;
			return true;
		} else {
			Market.invalidQueries += 1;
			return false;
		}
	}
	
	/**
	 * Transaction method for the Trader that is buying. <p> This method will conduct the necessary wallet 
	 * operations for the buying Trader in a transaction. <p> It will update blocked dollars, dollars and
	 * coins to accomodate the output of the transaction. <p>Additionally, It will take in both the initial 
	 * BuyingOrder price which this Trader put in and the real price at which this transaction is taking 
	 * place. If there is a surplus in the initial price, it will refund the extra dollars. 
	 * 
	 * @param amount The amount of PQoins bought by the Trader of this Wallet.
	 * @param buyerPrice The initial price at which this Trader put its BuyingOrder.
	 * @param realPrice The real price at which the transaction took place. 
	 */
	public void buyTransaction(double amount, double buyerPrice, double realPrice) {
		double refundPrice = buyerPrice - realPrice;
		
		blockedDollars -= amount * buyerPrice;
		dollars += amount * refundPrice;
		coins += amount;
	}
	
	/**
	 * Transaction method for the Trader that is selling. <p> This method will conduct the necessary wallet 
	 * operations for the selling Trader in a transaction. <p> It will update blocked coins and dollars 
	 * to accomodate the output of the transaction. <p>Additionally, it will implement the market fee to the 
	 * amount of dollar which this Trader will get.
	 * 
	 * 
	 * @param amount The amount of PQoins sold by the Trader of this Wallet.
	 * @param price The price at which the transaction took place.
	 */
	public void sellTransaction(double amount, double price) {
		double volume = amount * price * (1-((double)Market.getFee()/1000));
		
		blockedCoins -= amount;
		dollars += volume;
	}
	
	/**
	 * This method will add the specified amount of dollars to this wallet.
	 * 
	 * @param amount Amount of dollars to be deposited to this wallet.
	 */
	public void deposit(double amount) {
		dollars += amount;
	}
	
	/**
	 * This method will withdraw the specified amount of dollar from this wallet.
	 * 
	 * @param amount Amount of dollars to be withdrawn from this wallet.
	 */
	public void withdraw(double amount) {
		if (dollars >= amount) {
			dollars -= amount;
		} else {
			Market.invalidQueries += 1;
		}
	}
	
	/**
	 * This method adds the specified amount of PQoins to this wallet.<p>This method is used 
	 * when giving random amounts of rewards to every Trader in the market.
	 * 
	 * @param amount Amount of PQoins to be added to this wallet.
	 */
	public void addPQ(double amount) {
		coins += amount;
	}
	
	/**
	 * toString method for the Wallet class.<p> It will return the total amount of dollars and coins with 
	 * 5 decimal places.
	 * 
	 * @return String.format("%.5f", dollars+blockedDollars)+"$ "+String.format("%.5f", coins+blockedCoins)+"PQ"
	 */
	public String toString() {
		return String.format("%.5f", dollars+blockedDollars)+"$ "+String.format("%.5f", coins+blockedCoins)+"PQ";
	}
	
}
