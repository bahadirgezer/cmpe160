package elements;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Market {
	private PriorityQueue<SellingOrder> sellingOrders = new PriorityQueue<SellingOrder>();
	private PriorityQueue<BuyingOrder> buyingOrders = new PriorityQueue<BuyingOrder>();
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private static int fee;
	public static int invalidQueries = 0;
	private static int successfulTransactions = 0;
	private static int buyingMarketDollarSize = 0;
	private static int sellingMarketPQSize = 0;

	
	public Market(int fee) {
		this.fee = fee;
	}

	
	public void giveSellOrder(SellingOrder order) {
		sellingMarketPQSize += order.getAmount();
		sellingOrders.add(order);
	}

	public void giveBuyOrder(BuyingOrder order) {
		buyingMarketDollarSize += (order.getAmount() * order.getPrice());
		buyingOrders.add(order);
	}
	
	
	
	public void makeOpenMarketOperation(double price, ArrayList<Trader> traders) {
		double buyPrice = buyingOrders.peek().getPrice();
		double sellPrice = sellingOrders.peek().getPrice();
		
		
		while(buyPrice < price && price < sellPrice) {
			if (buyPrice < price) {
				
			}
			
			if (price < sellPrice) {
				
			}
			
			
			
			
		}
		
		
		
		
		
		
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
			transactions.add(transaction);
			
			successfulTransactions += 1;
			buyingMarketDollarSize -= (buyingOrder.getAmount() * buyingOrder.getAmount());
			sellingMarketPQSize -= sellingOrder.getAmount();
			
			Trader buyer = traders.get(buyingOrder.getTraderID());
			Trader seller = traders.get(sellingOrder.getTraderID());
			double price = sellingOrder.getPrice();
			double buyerPrice = buyingOrder.getPrice();
			
			//selling and buying amount is equal at this point.
			buyer.buyTransaction(sellingAmount, buyerPrice, price);
			seller.sellTransaction(sellingAmount, price);
			
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
	
	public static int getFee() {
		return fee;
	}
	
	public static double getBuyingMarketCap() {
		return buyingMarketDollarSize;
	}
	
	public static double getSellingMarketCap() {
		return sellingMarketPQSize;
	}
	
	public static int getSuccessfulTransactions() {
		return successfulTransactions;
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