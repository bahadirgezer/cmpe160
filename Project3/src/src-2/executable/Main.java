package executable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import elements.Market;
import elements.Trader;


public class Main {
	
	private static Market market;
	public static Random myRandom;
	private static int seed, fee, users, queries;
	private static ArrayList<Trader> traders = new ArrayList<Trader>();

	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		seed = in.nextInt();
		fee = in.nextInt();
		users = in.nextInt();
		queries = in.nextInt();
		market = new Market(fee);
		myRandom = new Random(seed);
		
		in.nextLine();
		
		for (int i=0; i<users; i++) {
			Scanner line = new Scanner(in.nextLine());
			Trader trader = new Trader(line.nextDouble(), line.nextDouble());
		}
		
		for (int i=0; i<queries; i++) {
			Scanner line = new Scanner(in.nextLine());
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
				out.println("Current market size: "+ Market.getBuyingMarketCap()+
						" "+ Market.getSellingMarketCap());
			
			} else if (type == 501) {
				out.println("Number of successful transactions: "
						+ Market.getSuccessfulTransactions());
			
			} else if (type == 502) {
				out.println(Market.invalidQueries);
			
			} else if (type == 505) {
				out.println("Current prices: "+market.getSellingPrice()+" "
						+market.getBuyingPrice()+" "+market.getMarketPrice());
	
			} else if (type == 555) {
				for (Trader trader : traders) {
					out.println(trader.walletString());
				}
						
			} else if (type == 777) {
				for (Trader trader: traders) {
					double amountPQ = myRandom.nextDouble() * 10;
					trader.addPQ(amountPQ);
				}
				
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
				} else if (type == 666) {
					double price = line.nextDouble();
					market.makeOpenMarketOperation(price, traders);
				
				}
				
				market.checkTransactions(traders);
				
			}
			
			
			
	
		} // query for loop
		
		
				
	} // main method

} // main class
