package elements;

public class Transaction {
	
	@SuppressWarnings("unused")
	private SellingOrder sellingOrder;
	@SuppressWarnings("unused")
	private BuyingOrder buyingOrder;
	
	public Transaction(SellingOrder sellingOrder,  BuyingOrder buyingOrder) {
		this.sellingOrder = sellingOrder;
		this.buyingOrder = buyingOrder;
	}
	
	public String toString() {
		return "SellingOrder: "+sellingOrder.toString()+", BuyingOrder: "+ buyingOrder.toString();
	}
}
