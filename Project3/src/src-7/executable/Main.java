package executable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import elements.Market;
import elements.Trader;

/**
 * Main class for the PQoin market. Only contains the main method and 
 * necessary fields. 
 *  
 * @author Bahadir Gezer
 *
 */
public class Main {
	
	/**
	 * Market object. There is only one market for our PQoin-dollar market.
	 * <p>This object is used mainly for managing and keeping track of 
	 * buying and selling orders.
	 */
	private static Market market;
	
	/**
	 * Random object. There is only one random object for our market. <p> It is used
	 * when the market uses query '777', which gives random amounts of PQoin rewards
	 * to all traders in the market.
	 * This object takes in a seed given in the inputs.
	 */
	public static Random myRandom;
	
	/**
	 * This is the seed value used in the Random myRandom object.
	 */
	private static int seed;
	
	/**
	 * This is the market fee per thousand. <p> It is used when paying the seller after a 
	 * transaction. The seller will get PQoinAmount * PQoinPrice * (1-(fee/1000))
	 */
	private static int fee;
	
	/**
	 * This is the number of traders in the market. <p> It is used in the for loop when taking
	 * the inputs for trader objects.
	 */
	private static int users;
	
	/**
	 * This is the number of queries the system will give in the input file. It is used
	 * in the for loop for taking queries.
	 */
	private static int queries;
		
	/**
	 * Main method of our PQoin market. Takes in the inputs then calls the relevant 
	 * actions for those inputs.
	 *  
	 * @param args args[0] is the input file and args[1] is the output file.
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		/**
		 * This ArrayList holds all traders in the market. <p>This ArrayList will be used when a method 
		 * needs to acces a specific trader object. This list will be filled in the trader 
		 * for loop. Each trader object is added to this ArrayList right after creation.
		 */
		ArrayList<Trader> traders = new ArrayList<Trader>();
		
		/**
		 * Scanner object for the input file. Will be used to get the input line by line.
		 */
		Scanner in = new Scanner(new File(args[0]));
		
		/**
		 * PrintStream object for the output file. Will be used when an output query is called.
		 */
		PrintStream out = new PrintStream(new File(args[1]));
		
		seed = in.nextInt();
		in.nextLine();
		
		/**
		 * Scanner object for each line in the input. The Scanner 'in' object will pass each
		 * line in the input file to this Scanner object.<p>This scanner will be used for 
		 * scaning a single line.
		 */
		Scanner line = new Scanner(in.nextLine());
				
		fee = line.nextInt();
		users = line.nextInt();
		queries = line.nextInt();
		
		market = new Market(fee);
		myRandom = new Random(seed);
		
		// for loop for traders, creates traders and adds them to the traders ArrayList.
		for (int i=0; i<users; i++) {
			line = new Scanner(in.nextLine());
			Trader trader = new Trader(line.nextDouble(), line.nextDouble());
			traders.add(trader);
			//System.out.println(traders);
			
		}
		
		
		// for loop for queries, does the necessary operations for each type of query.
		for (int i=0; i<queries; i++) {
			line = new Scanner(in.nextLine());
			int type = line.nextInt();
			
			if (type == 3) {
				Trader trader = traders.get(line.nextInt());
				double amount = line.nextDouble();
				trader.deposit(amount);
				
			} else if (type == 4) {
				Trader trader = traders.get(line.nextInt());
				double amount = line.nextDouble();
				trader.withdraw(amount);
				
			} else if (type == 5) {
				Trader trader = traders.get(line.nextInt());
				out.println(trader.toString());
				
			} else if (type == 500) {
				out.println("Current market size: "+ String.format("%.5f", Market.getBuyingMarketCap())+
						" "+ String.format("%.5f", Market.getSellingMarketCap()));
			
			} else if (type == 501) {
				out.println("Number of successful transactions: "
						+ Market.getSuccessfulTransactions());
			
			} else if (type == 502) {
				out.println("Number of invalid queries: "+Market.invalidQueries);
			
			} else if (type == 505) {
				out.println("Current prices: "+String.format("%.5f", market.getBuyingPrice())+" "
						+String.format("%.5f", market.getSellingPrice())+" "+String.format("%.5f", market.getMarketPrice()));
	
			} else if (type == 555) {
				for (Trader trader : traders) {
					out.println(trader.toString());
				}
						
			} else if (type == 777) {
				for (Trader trader: traders) {
					double amountPQ = myRandom.nextDouble() * 10;
					trader.addPQ(amountPQ);
				}
				
			}  else if (type == 666) {
				double price = line.nextDouble();
				market.makeOpenMarketOperation(price, traders);
			
			} else {
					
				if (type == 10) {
					Trader trader = traders.get(line.nextInt());
					double price = line.nextDouble();
					double amount = line.nextDouble();
					trader.buy(amount, price, market);
					
				} else if (type == 11) {
					Trader trader = traders.get(line.nextInt());
					double amount = line.nextDouble();
					double price = market.getSellingPrice();
					if (price != 0) {
						trader.buy(amount, price, market);
					} else {
						Market.invalidQueries += 1;
					}
					
				} else if (type == 20) {
					Trader trader = traders.get(line.nextInt());
					double price = line.nextDouble();
					double amount = line.nextDouble();
					trader.sell(amount, price, market);
					
				} else if (type == 21) {
					Trader trader = traders.get(line.nextInt());
					double amount = line.nextDouble();
					double price = market.getBuyingPrice();
					if (price != 0) {
						trader.sell(amount, price, market);
					} else {
						Market.invalidQueries += 1;
					}
				}
				
				//market.printQueues();
				market.checkTransactions(traders);
				// checkTransactions is only called when a query had the ability to...
				// ...change the market in a way that can generate transactions.
				
			}//end of if else
	
		} //query for loop
		in.close();
		out.close();
				
	}// main method
	
} // main class
