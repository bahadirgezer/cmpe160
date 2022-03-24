package elements;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Market {
	private PriorityQueue<SellingOrder> sellingOrders = new PriorityQueue<SellingOrder>();
	private PriorityQueue<BuyingOrder> buyingOrders = new PriorityQueue<BuyingOrder>();
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private final int fee;
	public static int invalidQueries = 0;

	
	public Market(int fee) {
		this.fee = fee;
	}

	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
	}

	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
	}
	
	public void makeOpenMarketOperation(double price) {
		
	}
	 
	public void checkTransactions(ArrayList<Trader> traders) {
		if (buyingOrders.isEmpty() || sellingOrders.isEmpty()) {
			return;
		}
		
		while(sellingOrders.peek().getPrice() <= buyingOrders.peek().getPrice()) {
			SellingOrder sellingOrder = sellingOrders.poll();
			BuyingOrder buyingOrder = buyingOrders.poll();

			double sellingAmount = sellingOrder.getAmount();
			double buyingAmount = buyingOrder.getAmount();
			
			if (sellingAmount > buyingAmount) {
				Pair pair = SellingOrder.divideSelling(sellingOrder, buyingAmount);
				sellingOrders.add(pair.getMarketSelling());
				sellingOrder = pair.getTransactionSelling();
			}
			
			if (sellingAmount < buyingAmount) {
				Pair pair = BuyingOrder.divideBuying(buyingOrder, sellingAmount);
				buyingOrders.add(pair.getMarketBuying());
				buyingOrder = pair.getTransactionBuying();
			}

			Transaction transaction = new Transaction(sellingOrder, buyingOrder);
			transaction.conductTransaction(traders);
			transactions.add(transaction);
		}
	}
	
	public double getSellingPrice() {
		SellingOrder sellingOrder = sellingOrders.peek();
		
		if (sellingOrder == null) {
			return 0;
		} else {
			return sellingOrder.getPrice();
		}
	}

	public double getBuyingPrice() {
		BuyingOrder buyingOrder = buyingOrders.peek();

		if (buyingOrder == null) {
			return 0;
		} else {
			return buyingOrder.getPrice();
		}
	}
	
	public double getMarketPrice() {
		double buyingPrice = getBuyingPrice();
		double sellingPrice = getSellingPrice();
		return (buyingPrice + sellingPrice) / 2.00;	
	}
	
	
	
	
	
	
	
	protected static class Pair {
		private BuyingOrder transactionBuying, marketBuying;
		private SellingOrder transactionSelling, marketSelling;

	    public Pair(BuyingOrder orderTransaction, BuyingOrder orderMarket) {
	    	transactionBuying = orderTransaction;
	    	marketBuying = orderMarket;
	    }
	    
	    public Pair(SellingOrder orderTransaction, SellingOrder orderMarket) {
	    	transactionSelling = orderTransaction;
	    	marketSelling = orderMarket;
	    }

	    
	    public BuyingOrder getTransactionBuying() {
	    	return transactionBuying;
	    }	

	    public BuyingOrder getMarketBuying() {
	    	return marketBuying;
	    }		    
	    
	    public SellingOrder getTransactionSelling() {
	    	return transactionSelling;
	    }	

	    public SellingOrder getMarketSelling() {
	    	return marketSelling;
	    }		    
	}
	
	
	
	
	
}