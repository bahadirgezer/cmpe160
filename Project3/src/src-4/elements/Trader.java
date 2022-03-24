package elements;

public class Trader {
	private int id;
	private Wallet wallet;
	public static int numberOfUsers = 0;
	
	public Trader(double dollars, double coins) {
		wallet = new Wallet(dollars, coins);
		id = numberOfUsers;
		numberOfUsers += 1;
	}
	
	public int sell(double amount, double price, Market market) {
		if (wallet.sellOperation(amount)) {
			SellingOrder order = new SellingOrder(id, amount, price);
			market.giveSellOrder(order);
		}
		
		return 0;
	}
	
	public int buy(double amount, double price, Market market) {
		if (wallet.buyOperation(amount, price)) {
			BuyingOrder order = new BuyingOrder(id, amount, price);
			market.giveBuyOrder(order);
		}
		
		return 0;
	}
	
	public void addPQ(double amount) {
		wallet.addPQ(amount);
	}
	
	public void buyTransaction(double amount, double buyerPrice, double realPrice) {
		wallet.buyTransaction(amount, buyerPrice, realPrice);
	}
	
	public void sellTransaction(double amount, double price) {
		wallet.sellTransaction(amount, price);
	}

	public void deposit(double amount) {
		wallet.deposit(amount);
	}
	
	public void withdraw(double amount) {
		wallet.withdraw(amount);
	}
	
	public String toString() {
		return "Trader "+id+": "+wallet.toString();
	}

	
	
}
