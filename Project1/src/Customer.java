
package question;

public class Customer {
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	private int ID, age, talkTime=0, messageAmount=0;
	private String name;
	private Operator operator;
	private double limitingAmount, networkUsage=0;
	private Bill bill;

	
	
	public Customer(int ID, String name, int age, Operator operator, double limitingAmount){
		this.ID = ID;
		this.age = age;
		this.name = name;
		this.operator = operator;
		this.limitingAmount = limitingAmount;
		bill = new Bill(limitingAmount);
	}
	
	
	/**
	 * Adds talking minutes to both customers.
	 * @param minute
	 * @param other
	 */
	
	void addCustomerTalkTime(int minute, Customer other) {
		talkTime += minute;
		other.talkTime += minute;
	}

	
	/**
	 * Adds talking time to both operators. 
	 * Calculates the talking cost.
	 * Adds the cost to the caller operator.
	 * But initially checks if cost + currentDebt is greater then limitingAmount; if not, it does nothing.
	 * 
	 * @param minute
	 * @param other
	 */
	
	void talk(int minute, Customer other) {
		double cost = operator.calculateTalkingCostt(minute, age);
		if (bill.check(cost)==true) {
			Operator otherOperator = other.getOperator();
			operator.addOperatorTalkTime(minute, otherOperator);	
			bill.add(cost);
			addCustomerTalkTime(minute, other);
		}
	}
	
	/**
	 * Adds message quantity to the sending operator.
	 * Calculates message cost.
	 * Adds the cost to the customer bill.
	 * But initially checks if cost + currentDebt is greater then limitingAmount; if not, it does nothing.
	 * 
	 * @param quantity
	 * @param other
	 */
	void message(int quantity, Customer other) {
		double cost = operator.calculateMessageCostt(quantity, operator, other);
		if (bill.check(cost)) {
			messageAmount += quantity;
			bill.add(cost);
			operator.addOperatorMessageAmount(quantity);
		}
	}
	
	
	void connection(double amount) { //amount is the number of data as MB
		double cost = operator.calculateNetworkCost(amount);
		if (bill.check(cost)) {
			networkUsage += amount;
			bill.add(cost);
			operator.addOperatorNetworkUsage(amount);
		}
	}
	
	/**
	 * 
	 * Pays for the current debt with a specified amount.
	 * The specified amount gets subtracted from the currentDebt. 
	 * 
	 * @param amount
	 */
	void customerPay(double amount) {
		bill.pay(amount);
	}
	
	/**
	 * Changes the limitingAmount to the amount specified. Checks if the new limiting amount is higher than the current debt. If so, does nothing.
	 * @param amount
	 */
	void customerChangeTheLimit(double amount) {
		bill.changeTheLimit(amount);
	}
	
	
	//getter methods
	int getAge() {
		return age;
	}
	Operator getOperator() {
		return operator;
	}
	Bill getBill() {
		return bill;
	}
	int getTalkTime() {
		return talkTime;
	}
	int getMessageAmount() {
		return messageAmount;
	}
	double getNetworkUsage() {
		return networkUsage;
	}
	String getName() {
		return name;
	}
	double getLimitingAmount() {
		return limitingAmount;
	}
	//setter methods
	void setAge(int age) {
		this.age = age;
	}
	void setOperator(Operator operator) {
		this.operator = operator;
	}
	void setBill(Bill bill) {
		this.bill = bill;
	}
	
	public String toString() {
		return "Customer " + ID + " : " + String.format("%.2f", bill.getTotalPayment()) + " " + String.format("%.2f", bill.getCurrentDebt());
	}
	

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

