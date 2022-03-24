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
		double volume = amount;
		if (coins >= volume) {
			coins -= volume;
			blockedCoins+= volume;
			return true;
		} else {
			Market.invalidQueries += 1;
			return false;
		}
		
		
	}
	
	
}
