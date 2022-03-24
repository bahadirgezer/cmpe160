package elements;

public class Order {
	private double amount;
	private double price;
	private int traderID;
	
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getTraderID() {
		return traderID;
	}
	
	public String toString() {
		return "P: "+ price+", A: "+ amount+" ID: "+ traderID;
	}
}
