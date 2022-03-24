
package question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {


	public static void main(String args[]) {

		Customer[] customers;
		Operator[] operators;

		int C, O, N;

		File inFile = new File("input_1.txt");  // args[0] is the input file
		File outFile = new File("output_1.txt");  // args[1] is the output file
		try {
			PrintStream outstream = new PrintStream(outFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		C = reader.nextInt();
		O = reader.nextInt();
		N = reader.nextInt();

		customers = new Customer[C];
		operators = new Operator[O];

		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
		PrintStream outstream1;
		try {
		        outstream1 = new PrintStream(outFile);
		}catch(FileNotFoundException e2) {
		        e2.printStackTrace();
		        reader.close();
		        return;
		}	
		

		int customerID = 0;
		int operatorID = 0;
		int inputInt;
		
		String name;
		int age, discountRate, currID, currrID, amount;
		double limitingAmount, talkingCharge, messageCost, networkCharge, amountt;
		
		
		for(int i=0; i<N; i++) {
			inputInt = reader.nextInt();
			if(inputInt == 1) {
				name = reader.next();
				age = reader.nextInt();
				currID = reader.nextInt();
				limitingAmount = reader.nextDouble();
//				System.out.println(customerID+" "+ name+" "+ age+" "+ operators[currID]+" "+ limitingAmount);
				customers[customerID] = new Customer(customerID, name, age, operators[currID],limitingAmount);
				customerID+=1;
			} else if(inputInt == 2){
				talkingCharge = reader.nextDouble();
				messageCost = reader.nextDouble();
				networkCharge = reader.nextDouble();
				discountRate = reader.nextInt();
//				System.out.println(operatorID+" "+ talkingCharge+ " "+ messageCost+ " "+ networkCharge+ " "+discountRate);
				operators[operatorID] = new Operator(operatorID, talkingCharge, messageCost, networkCharge, discountRate);
				operatorID+=1;
			} else if(inputInt == 3) {
				currID = reader.nextInt();
				currrID = reader.nextInt();
				amount = reader.nextInt();
				customers[currID].talk(amount, customers[currrID]);			
			} else if(inputInt == 4) {
				currID = reader.nextInt();
				currrID = reader.nextInt();
				amount = reader.nextInt();
				customers[currID].message(amount, customers[currrID]);
			} else if(inputInt == 5) {
				currID = reader.nextInt();
				amountt = reader.nextDouble();
				customers[currID].connection(amountt);
			} else if(inputInt == 6) {
				currID = reader.nextInt();
				amountt = reader.nextDouble();
				customers[currID].customerPay(amountt);
			} else if(inputInt == 7) {
				currID = reader.nextInt();
				currrID = reader.nextInt();
				customers[currID].setOperator(operators[currrID]);
			} else if(inputInt == 8) {
				currID = reader.nextInt();
				amountt = reader.nextDouble();
				customers[currID].customerChangeTheLimit(amountt);
			}
				
		}
					
		for(int ij=0; ij<O;ij++) {
			outstream1.println(operators[ij].toString());
		}
		
		for(int ijk=0; ijk<C;ijk++) {
			outstream1.println(customers[ijk].toString());
		}
		
		int maxTalk=0, maxMessage=0;
		Double maxConnection = 0.0;
		String maxTalkName="", maxMessageName="", maxConnectionName="";
		int loopControl=0;
		
		for(Customer customer: customers) {
			if(customer.getTalkTime()>maxTalk || loopControl == 0) {
				maxTalk = customer.getTalkTime();
				maxTalkName = customer.getName();
			}
			if(customer.getMessageAmount()>maxMessage || loopControl == 0) {
				maxMessage = customer.getMessageAmount();
				maxMessageName = customer.getName();
			}
			if(customer.getNetworkUsage()>maxConnection || loopControl == 0) {
				maxConnection = customer.getNetworkUsage();
				maxConnectionName = customer.getName();
			}
			loopControl+=1;
		}
		outstream1.println(maxTalkName + " : " + maxTalk);
		outstream1.println(maxMessageName + " : " + maxMessage);
		outstream1.println(maxConnectionName + " : " + String.format("%.2f", maxConnection));
		
		reader.close();
		outstream1.close();
		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
	} 
	
}

