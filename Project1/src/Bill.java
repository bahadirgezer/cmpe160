
package question;

public class Bill {

	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	private double limitingAmount, currentDebt, totalPayment=0;
	
	
	/**
	 * Constructor for the Bill class.
	 * @param limitingAmount
	 */
	public Bill(double limitingAmount) {
		this.limitingAmount = limitingAmount;
		this.currentDebt = 0;
	}
	
	
	/**
	 * Returns true if the total amount is smaller than or equal to limitingAmount.
	 * 
	 * @param amount
	 * @return
	 */
	boolean check(double amount) {
		if (amount+currentDebt>limitingAmount) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * Adds the spending amount to the current debt.
	 * If the currentDebt exceeds the limiting amount after the transaction, it does nothing.
	 * 
	 * @param amount
	 */
	void add(double amount) {
		if(check(amount)) {
			currentDebt += amount;
		}
	}
	
	
	/**
	 * Pays for the current debt with a specified amount.
	 * The specified amount gets subtracted from the currentDebt. 
	 * 
	 * @param amount
	 */
	void pay(double amount) {
		if(currentDebt-amount<0) {
			totalPayment += currentDebt;
			currentDebt = 0;
		} else {
			currentDebt -= amount;
			totalPayment += amount;
		}
	}
	
	/**
	 * Changes the limitingAmount to the amount specified. Checks if the new limiting amount is higher than the current debt. If so, does nothing.
	 * @param amount
	 */
	void changeTheLimit(double amount) {
		if(amount>=currentDebt) {
			limitingAmount = amount;
		}
	}
	
	//getter methods
	
	double getLimitingAmount() {
		return limitingAmount;
	}
	double getCurrentDebt() {
		return currentDebt;
	}
	double getTotalPayment() {
		return totalPayment;
	}
	
	
	
	


	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

