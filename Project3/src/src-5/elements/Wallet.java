package elements;

public class Wallet {
	private double dollars;
	private double coins;
	private double blockedDollars;
	private double blockedCoins;
	
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
		blockedDollars = 0;
		blockedCoins = 0;
	}
	
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
	
	public void buyTransaction(double amount, double buyerPrice, double realPrice) {
		double refundPrice = buyerPrice - realPrice;
		
		blockedDollars -= amount * buyerPrice;
		dollars += amount * refundPrice;
		coins += amount;
	}
	
	public void sellTransaction(double amount, double price) {
		double volume = amount * price * (1-((double)Market.getFee()/1000));
		
		blockedCoins -= amount;
		dollars += volume;
	}
	
	public void deposit(double amount) {
		dollars += amount;
	}
	
	public void withdraw(double amount) {
		if (dollars >= amount) {
			dollars -= amount;
		} else {
			Market.invalidQueries += 1;
		}
	}
	
	public void addPQ(double amount) {
		coins += amount;
	}
	
	public String toString() {
		return String.format("%.5f", dollars+blockedDollars)+"$ "+String.format("%.5f", coins+blockedCoins)+"PQ";
	}
	
	
}
