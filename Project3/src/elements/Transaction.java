package elements;

/**
 * Transaction class for the PQoin market. <p> This class holds the dependent BuyingOrder and SellingOrder
 * objects used in a transaction. This class should be considered as a container class. 
 * 
 * @author Bahadir Gezer
 *
 */
public class Transaction {
	
	/**
	 * SellingOrder object for the transaction.
	 */
	@SuppressWarnings("unused")
	private SellingOrder sellingOrder;
	
	/**
	 * BuyingOrder object for the transaction.
	 */
	@SuppressWarnings("unused")
	private BuyingOrder buyingOrder;
	
	/**
	 * Constructor for the Transaction object. 
	 * 
	 * @param sellingOrder SellingOrder object for this Transaction.
	 * @param buyingOrder BuyingOrder object for this Transaction.
	 */
	public Transaction(SellingOrder sellingOrder,  BuyingOrder buyingOrder) {
		this.sellingOrder = sellingOrder;
		this.buyingOrder = buyingOrder;
	}
	
	/**
	 * toString method for the Transaction class. This method is mainly used for debugging purposes.
	 * 
	 * @return "SellingOrder: "+sellingOrder.toString()+", BuyingOrder: "+ buyingOrder.toString()
	 */
	public String toString() {
		return "SellingOrder: "+sellingOrder.toString()+", BuyingOrder: "+ buyingOrder.toString();
	}
	
}
