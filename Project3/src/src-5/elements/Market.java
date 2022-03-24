package elements;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Market class for the PQoin market. <p>This class is 'pseudo-static', in that there is 
 * only one market for the project. <p>This class holds the PriorityQueues for the buy and
 * sell orders. It also includes necesary field and methods for the market to function.
 * 
 * @author Bahadir Gezer
 *
 */
public class Market {
	
	/**
	 * Priority Queue for the SellingOrder objects that are created. <p>This queue uses the compareTo()
	 * method in the SellingOrder class for comparison between SellingOrder objects. 
	 */
	private PriorityQueue<SellingOrder> sellingOrders = new PriorityQueue<SellingOrder>();
	
	/**
	 * Priority Queue for the BuyingOrder objects that are created. <p>This queue uses the compareTo()
	 * method in the BuyingOrder class for comparison between BuyingOrder objects. 
	 */
	private PriorityQueue<BuyingOrder> buyingOrders = new PriorityQueue<BuyingOrder>();
	
	/**
	 * This ArrayList holds each Transaction that occurs in the market -Including the ones that are created 
	 * in the openMarketOperation() method.<p> This ArrayList is just a container used when there is a need 
	 * of debugging.
	 */
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	/**
	 * This is the market fee per thousand. <p>It is used when paying the seller after a 
	 * transaction. The seller will get PQoinAmount * PQoinPrice * (1-(fee/1000))
	 */
	private static int fee;
	
	/**
	 * This field holds the number of invalid queries for this market. <p>It increments by one
	 * when an invalid query is called by the inputs.
	 */
	public static int invalidQueries = 0;
	
	/**
	 * This field holds the number of successful transactions in the market.<p> It is basically the
	 * size for the transactions ArrayList.
	 */
	private static int successfulTransactions = 0;
	
	/**
	 * The name for this method is a bit misleading. At first this method held the total amount
	 * of dollars that are blocked for the buying market.<p> However, after test cases were given, 
	 * and a bit of debugging I realized that it should actually hold the amount of PQoins in the
	 * buying market.
	 */
	private static int buyingMarketDollarSize = 0;
	
	/**
	 * This method holds the total amount of PQoins in the selling market.
	 */
	private static int sellingMarketPQSize = 0;
	
	/**
	 * This method is when debugging the project. <p>It is already in its proper spot
	 * in the main method. You just need to uncommet it and the buying and selling
	 * order priority queues will be printed out for each query.
	 */
	public void printQueues() {
		System.out.println("BuyingOrders: "+buyingOrders);
		System.out.println("SellingOrders: "+sellingOrders);
		System.out.println("============================");
	}
	
	/**
	 * Constructor for the Market class. <p>Since there should be a single market object for the 
	 * whole project, this constuctor should be called once. 
	 * 
	 * @param fee Transaction fee for this market.
	 */
	@SuppressWarnings("static-access")
	public Market(int fee) {
		this.fee = fee;
	}

	/**
	 * This method completes the operations for the market side of selling orders.<p> It will
	 * update the total sellingMarketPQSize and add the SellingOrder to the sellingOrders
	 * ArrayList.
	 * 
	 * @param order The SellingOrder that will be added to the market.
	 */
	public void giveSellOrder(SellingOrder order) {
		sellingMarketPQSize += order.getAmount();
		sellingOrders.add(order);
	}

	/**
	 * This method completes the operations for the market side of buying orders. <p>It will
	 * update the total buyingMarketDollarSize and add the BuyingOrder to the buyingOrders
	 * ArrayList.
	 * 
	 * @param order The BuyingOrder that will be added to the market.
	 */
	public void giveBuyOrder(BuyingOrder order) {
		buyingMarketDollarSize += order.getAmount();
		buyingOrders.add(order);
	}
	
	/**
	 * This method is used in the makeOpenMarketOperation() method.<p> This method will return 
	 * true if the loop needs to be initiated one more time; false if the loop should end.
	 * 
	 * @param price The price value which the openMarketOperation() method takes in.
	 * @return True if the loop should continiue; false if it should end.
	 */
	public boolean openMarketLoopChecker(double price) {
		if (buyingOrders.isEmpty() && sellingOrders.isEmpty()) {
			return false;
		}
		
		double buyPrice = 0;
		double sellPrice = price + 1;
		
		if (buyingOrders.peek() != null) {
			buyPrice = buyingOrders.peek().getPrice();
		}
		
		if (sellingOrders.peek() != null) {
			sellPrice = sellingOrders.peek().getPrice();
		}
		
		if (buyPrice < price && price < sellPrice) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * This method will conduct open market operations until the buying price is lower and 
	 * the selling price is higher than the desired market price. It achieves this by creating 
	 * corresponding buying or selling orders. 
	 * <p>
	 * If the market price is lower or equal to the buying price, then it will create a selling
	 * order that corresponds to the top buying order.  If the market price is higher or equal 
	 * to the selling price, then it will create a buying order that correspinds to the top 
	 * selling order.
	 * <p>
	 * After necessary orders are created the checkTransactions() method is called. This will create
	 * transactions for the newly created orders that ovelap with eachother and it will remove these 
	 * orders from the market, thus chaning the market price. 
	 * <p>
	 * These operations are all done inside a loop. When the loop condition -which is checked by the 
	 * openMarketLoopChecker() method- is reached, the open market 
	 * operation is considered to be successful and the loop will terminate.
	 * 
	 * @param price The desired price of the market. This price will be between the buying and selling 
	 * prices after this method is called.
	 * @param traders The ArrayList of all Trader objects in the market. 
	 */
	public void makeOpenMarketOperation(double price, ArrayList<Trader> traders) {
		
		while(openMarketLoopChecker(price)) {
			double buyPrice = 0;
			double sellPrice = price +1;
			SellingOrder sellingOrder = sellingOrders.peek();
			BuyingOrder buyingOrder = buyingOrders.peek();
			
			if (buyingOrder != null) {
				buyPrice = buyingOrder.getPrice();
			}
			if (sellingOrder != null) {
				sellPrice = sellingOrder.getPrice();
			}
			
			if (sellPrice <= price) {
				double amount = sellingOrder.getAmount();
				BuyingOrder newBuyingOrder = new BuyingOrder(0, amount, sellPrice);
				this.giveBuyOrder(newBuyingOrder);
			}
			
			if (price <= buyPrice) {
				double amount = buyingOrder.getAmount();
				SellingOrder newSellingOrder = new SellingOrder(0, amount, sellPrice);
				this.giveSellOrder(newSellingOrder);
			}
			
			this.checkTransactions(traders);
		}
	}
	 
	/**
	 * This method will checks if any buying and selling orders overlaps. <p> This method is 
	 * called in the main method after a market changing query takes place. <p>If there is overlap
	 * -peek() price of buyingOrders is higher or equal to the peek() price of sellingOrders-
	 * It will create Transaction objects and call the necesary transaction operations until 
	 * there is no order overlap in the market. 
	 * 
	 * @param traders ArrayList of all Trader objects in the market. 
	 */
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
			
			System.out.println(transactions);
			
			successfulTransactions += 1;
			buyingMarketDollarSize -= buyingOrder.getAmount();
			sellingMarketPQSize -= sellingOrder.getAmount();
			
			Trader buyer = traders.get(buyingOrder.getTraderID());
			Trader seller = traders.get(sellingOrder.getTraderID());
			double price = sellingOrder.getPrice();
			double buyerPrice = buyingOrder.getPrice();
			
			//selling and buying amount is equal at this point.
			buyer.buyTransaction(sellingAmount, buyerPrice, price);
			seller.sellTransaction(sellingAmount, price);
			
			if (buyingOrders.isEmpty() || sellingOrders.isEmpty()) {
				return;
			}
		}
	}
	
	/**
	 * This method gets the peek() price in the sellingOrders PriorityQueue.<p> It is used
	 * when the selling price is needed or when query '505' and '11' are called. 
	 * 
	 * @return If the sellingOrders queue is empty, the sellingPrice is returned as 0; 
	 * else, the peek() price of the sellingOrders is returned.
	 */
	public double getSellingPrice() {
		SellingOrder sellingOrder = sellingOrders.peek();
		
		if (sellingOrder == null) {
			return 0;
		} else {
			return sellingOrder.getPrice();
		}
	}

	/**
	 * This method gets the peek() price in the buyingOrders PriorityQueue.<p> It is used
	 * when the buying price is needed or when query '505' and '21' are called. 
	 * 
	 * @return If the buyingOrders queue is empty, the buyingPrice is returned as 0; 
	 * else, the peek() price of the buyingOrders is returned.
	 */
	public double getBuyingPrice() {
		BuyingOrder buyingOrder = buyingOrders.peek();

		if (buyingOrder == null) {
			return 0;
		} else {
			return buyingOrder.getPrice();
		}
	}
	
	/**
	 * This method will return the current market price. <p> It is calculated by taking the avreage
	 * of the top prices in the buyingOrders and sellingOrders queues. If a queue is empty, the 
	 * value for that queue is taken as 0.
	 * 
	 * @return The current market price. Which is the average of the buying and selling prices. 
	 */
	public double getMarketPrice() {
		double buyingPrice = getBuyingPrice();
		double sellingPrice = getSellingPrice();
		return (buyingPrice + sellingPrice) / 2.00;	
	}
	
	/**
	 * Getter method for the market fee. 
	 * 
	 * @return fee value per thousand for this market. 
	 */
	public static int getFee() {
		return fee;
	}
	
	/**
	 * Getter method for the 'total market capitalization' for the buying orders in this market.
	 * <p> This value is calculated by adding the PQoin amounts of every buy order in the market.
	 * 
	 * @return The total amount of PQoins in the buyingOrders PriorityQueue.
	 */
	public static double getBuyingMarketCap() {
		return buyingMarketDollarSize;
	}
	
	/**
	 * Getter method for the 'total market capitalization' for the selling orders in this market.
	 * <p> This value is calculated by adding the PQoin amounts of every sell order in the market.
	 * 
	 * @return The total amount of PQoins in the sellingOrders PriorityQueue.
	 */
	public static double getSellingMarketCap() {
		return sellingMarketPQSize;
	}
	
	/**
	 * Getter method for the total successful transactions that occured in this market. <p>
	 * This value includes the transactions that are conducted when open market operations are
	 * conducted. 
	 * 
	 * @return The total successful transactions in this market.
	 */
	public static int getSuccessfulTransactions() {
		return successfulTransactions;
	}
	
	/**
	 * Pair class for returning pairs of orders. <p> This method is used when dividing Orders, which
	 * is done when two transaction amounts are not equal. 
	 * 
	 * @author Bahadir Gezer
	 *
	 */
	protected static class Pair {
		private BuyingOrder transactionBuying, marketBuying;
		private SellingOrder transactionSelling, marketSelling;
		
		/**
		 * Constructor for the Pair class.
		 * @param orderTransaction
		 * @param orderMarket
		 */
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