package main;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * This is class interacts with the Operations.java class. This class 
 * prepares the regex patterns and uses them to extract the necessary
 * components of an input. It will then pass those components to the 
 * relevant functions in Operations.java.
 * 
 * @author Bahadir Gezer
 *
 */
public class RegularExpressions {
	
	/**
	 * Pattern object for input type 1 which does not have 'R' or 'L' at 
	 * the end for the refrigerated or liquid containers. This pattern is 
	 * used when creating a matcher object for the regular expression operations.
	 * 
	 */
	static Pattern  pattern1a = Pattern.compile("^1 (\\d+) (\\d+)$");
	
	/**
	 * Pattern object for input type 1 which has the 'R' or 'L' at 
	 * the end for the refrigerated or liquid containers. This pattern is 
	 * used when creating a matcher object for the regular expression operations.
	 * 
	 */
	static Pattern  pattern1b = Pattern.compile("^1 (\\d+) (\\d+) (R|L)$");
	
	/**
	 * Pattern object for input type 2. This pattern is used when creating a matcher object 
	 * for the regular expression operations.
	 */
	static Pattern  pattern2  = Pattern.compile("^2 (\\d+) (\\d+) (\\d+) (\\d+) (\\d+) (\\d+) (\\d+\\.\\d+)$");
	
	/**
	 * Pattern object for input type 3. This pattern is used when creating a matcher object 
	 * for the regular expression operations.
	 */
	static Pattern  pattern3  = Pattern.compile("^3 (-?\\d+\\.\\d+) (-?\\d+\\.\\d+)$");
	
	/**
	 * Pattern object for input type 4. This pattern is used when creating a matcher object 
	 * for the regular expression operations.
	 */
	static 	Pattern pattern4  = Pattern.compile("^4 (\\d+) (\\d+)$");
	
	/**
	 * Pattern object for input type 5. This pattern is used when creating a matcher object 
	 * for the regular expression operations.
	 */
	static 	Pattern pattern5  = Pattern.compile("^5 (\\d+) (\\d+)$");
	
	/**
	 * Pattern object for input type 6. This pattern is used when creating a matcher object 
	 * for the regular expression operations.
	 */
	static 	Pattern pattern6  = Pattern.compile("^6 (\\d+) (\\d+)$");
	
	/**
	 * Pattern object for input type 7. This pattern is used when creating a matcher object 
	 * for the regular expression operations.
	 */
	static 	Pattern pattern7  = Pattern.compile("^7 (\\d+) (\\d+\\.\\d+)$");
	
	/**
	 * Matches the input with regular expressions and for each type of input 
	 * passes the necessary values to the corresponding functions in the Operations.java class.
	 * @param input
	 */
	public static void inputParser(String input) {
		input = input.trim();
		
		/**
		 * Matcher object for the pattern object pattern1a. This matcher is 
		 * used for matching and grouping inputs.
		 */
		Matcher matcher1a = pattern1a.matcher(input);
		
		/**
		 * Matcher object for the pattern object pattern1b. This matcher is 
		 * used for matching and grouping inputs.
		 */
		Matcher matcher1b = pattern1b.matcher(input);
		
		/**
		 * Matcher object for the pattern object pattern2. This matcher is 
		 * used for matching and grouping inputs.
		 */
		Matcher matcher2 = pattern2.matcher(input);
		
		/**
		 * Matcher object for the pattern object pattern3. This matcher is 
		 * used for matching and grouping inputs.
		 */
		Matcher matcher3 = pattern3.matcher(input);
		
		/**
		 * Matcher object for the pattern object pattern4. This matcher is 
		 * used for matching and grouping inputs.
		 */
		Matcher matcher4 = pattern4.matcher(input);
		
		/**
		 * Matcher object for the pattern object pattern5. This matcher is 
		 * used for matching and grouping inputs.
		 */
		Matcher matcher5 = pattern5.matcher(input);
		
		/**
		 * Matcher object for the pattern object pattern6. This matcher is 
		 * used for matching and grouping inputs.
		 */
		Matcher matcher6 = pattern6.matcher(input);
		
		/**
		 * Matcher object for the pattern object pattern7. This matcher is 
		 * used for matching and grouping inputs.
		 */
		Matcher matcher7 = pattern7.matcher(input);

		if (matcher1a.matches()) {
			int ID = Integer.parseInt(matcher1a.group(1));
			int weight = Integer.parseInt(matcher1a.group(2));
			Operations.createContainer(ID, weight, 'N');
	
		} else if (matcher1b.matches()) {
			int ID = Integer.parseInt(matcher1b.group(1));
			int weight = Integer.parseInt(matcher1b.group(2));
			char type = matcher1b.group(3).charAt(0);
			Operations.createContainer(ID, weight, type);

		} else if (matcher2.matches()) {
			int ID = Integer.parseInt(matcher2.group(1));
			int maxWeight = Integer.parseInt(matcher2.group(2));
			int maxAll = Integer.parseInt(matcher2.group(3));
			int maxHeavy = Integer.parseInt(matcher2.group(4));
			int maxRefrige = Integer.parseInt(matcher2.group(5));
			int maxLiquid = Integer.parseInt(matcher2.group(6));
			double fuelCon = Double.parseDouble(matcher2.group(7));
			Operations.createShip(ID, maxWeight, 
					maxAll, maxHeavy, maxRefrige, maxLiquid, fuelCon);

		} else	if (matcher3.matches()) {
			double X = Double.parseDouble(matcher3.group(1));
			double Y = Double.parseDouble(matcher3.group(2));
			Operations.createPort(X, Y);

		} else if (matcher4.matches()) {
			int shipID = Integer.parseInt(matcher4.group(1));
			int containerID = Integer.parseInt(matcher4.group(2));
			Operations.loading(shipID, containerID);

		} else if (matcher5.matches()) {
			int shipID = Integer.parseInt(matcher5.group(1));
			int containerID = Integer.parseInt(matcher5.group(2));
			Operations.unloading(shipID, containerID);

		} else if (matcher6.matches()) {
			int shipID = Integer.parseInt(matcher6.group(1));
			int portID = Integer.parseInt(matcher6.group(2));
			Operations.sailing(shipID, portID);

		} else if (matcher7.matches()) {
			int shipID = Integer.parseInt(matcher7.group(1));
			double fuel = Double.parseDouble(matcher7.group(2));
			Operations.addFuel(shipID, fuel);
		}
	}
}
