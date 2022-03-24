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
	
	
	
	
	
}
