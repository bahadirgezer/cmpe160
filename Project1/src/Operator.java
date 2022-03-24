
package question;

/**
 * This class is used for calculations that is supposed to be made by the operator.
 */
public class Operator {
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	private int ID, discountRate, talkTime=0, messageAmount=0;
	private double talkingCharge, messageCost, networkCharge, networkUsage=0;
	
	/**
	 * This constructor takes ID, talkingCharge, messageCost, networkCharge, discountRate.
	 * 
	 * @param ID
	 * @param talkingCharge
	 * @param messageCost
	 * @param networkCharge
	 * @param discountRate
	 */
	
	public Operator(int ID, double talkingCharge, double messageCost, double networkCharge, int discountRate) {
		this.ID = ID;
		this.talkingCharge = talkingCharge;
		this.messageCost = messageCost;
		this.networkCharge = networkCharge;
		this.discountRate = discountRate;
	}
	
	double calculateTalkingCost(int minute, Customer customer) {
		return 5.6;
	}
	/**
	 * This method calculates and returns the total talking charge. It takes the relevant discounts into account.
	 * @param minute
	 * @param customer
	 * @return
	 */
	
	double calculateTalkingCostt(int minute, int age) {
		double totalCharge = (double)minute * talkingCharge;
		if(age >=18 && age <= 65) {
			return totalCharge;
		} else {
			return totalCharge * ((double)(100-discountRate)/100);
		}
	}
	
	/**
	 * Adds talk time to both operators.
	 * @param minute
	 * @param other
	 */
	
	void addOperatorTalkTime(int minute, Operator other){
		talkTime += minute;
		other.talkTime += minute;
	}
	
	
	double calculateMessageCost(int quantity, Customer customer, Customer other) {
		return 5.6;
	}
	/**
	 * This method calculates and returns the total message charge. It takes the relevant discounts into account.
	 * 
	 * @param quantity
	 * @param customer
	 * @param other
	 * @return
	 */
	
	double calculateMessageCostt(int quantity, Operator operator, Customer other) {
		double totalCharge = (double)quantity * messageCost;
		if(operator.getID() == other.getOperator().getID()) { 
			return totalCharge * ((double)(100-discountRate)/100);
		} else {
			return totalCharge;
		}
		
	}
	
	/**
	 * Adds the quantity of messages to the operator's messageAmount.
	 * @param quantity
	 */
	void addOperatorMessageAmount(int quantity){
		messageAmount += quantity;
	}
	
	
	/**
	 * This method calculates and returns the total network charge.
	 * @param amount
	 * @return
	 */
	
	double calculateNetworkCost(double amount) {
		return amount * networkCharge;
	}
	/**
	 * Adds network usage amount to networkUsage.
	 * 
	 * @param amount
	 */
	
	void addOperatorNetworkUsage(double amount) {
		networkUsage += amount;
	}
	
	
	//getter methods
	/**
	 * Getter for talkingCharge.
	 * @return
	 */
	double getTalkingCharge() {
		return talkingCharge;
	}
	/**
	 * Getter for messageCost.
	 * @return
	 */
	double getMessageCost() {
		return messageCost;
	}
	/**
	 * Getter for networkCharge.
	 * @return
	 */
	double getNetworkCharge() {
		return networkCharge;
	}
	
	int getID() {
		return ID;
	}
	double getNetworkUsage() {
		return networkUsage;
	}
	
	//setter methods
	/**
	 * Setter for the talkingCharge.
	 * @param talkingCharge
	 */
	void setTalkingCharge(double talkingCharge) {
		this.talkingCharge = talkingCharge;
	}
	/**
	 * Setter for the messageCost.
	 * @param messageCost
	 */
	void setMessageCost(double messageCost) {
		this.messageCost = messageCost;
	}
	/**
	 * Setter for the networkCharge.
	 * @param networkCharge
	 */
	void setNetworkCharge(double networkCharge) {
		this.networkCharge = networkCharge;
	}
	
	public String toString() {
		return "Operator " + ID + " : " + talkTime + " " + messageAmount + " " + String.format("%.2f", networkUsage);
	}
	
	
	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}
