package main;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegularExpressions {
	

	static String strPattern1a = "^1 (\\d+) (\\d+)$";
	static String strPattern1b = "^1 (\\d+) (\\d+) (R|L)$";
	static String strPattern2  = "^2 (\\d+) (\\d+) (\\d+) (\\d+) (\\d+) (d+\\.d+)$";
	static String strPattern3  = "^3 (d+\\.d+) (d+\\.d+)$";
	static String strPattern4  = "^4 (\\d+) (\\d+)$";
	static String strPattern5  = "^5 (\\d+) (\\d+)$";
	static String strPattern6  = "^6 (\\d+) (\\d+)$";
	static String strPattern7  = "^6 (\\d+) (d+\\.d+)$";

	
	static Pattern pattern1a = Pattern.compile(strPattern1a);
	static Pattern pattern1b = Pattern.compile(strPattern1b);
	static Pattern pattern2  = Pattern.compile(strPattern2);
	static Pattern pattern3  = Pattern.compile(strPattern3);
	static 	Pattern pattern4  = Pattern.compile(strPattern4);
	static 	Pattern pattern5  = Pattern.compile(strPattern5);
	static 	Pattern pattern6  = Pattern.compile(strPattern6);
	static 	Pattern pattern7  = Pattern.compile(strPattern7);

	
	public static void inputParser(String input) {
		Matcher matcher1a = pattern1a.matcher(input);
		if (matcher1a.find()) {
			int ID = Integer.parseInt(matcher1a.group(0));
			int weight = Integer.parseInt(matcher1a.group(1));
			Operations.createContainer(ID, weight, 'N');
			}
		
		Matcher matcher1b = pattern1b.matcher(input);
		if (matcher1b.find()) {
			int ID = Integer.parseInt(matcher1b.group(0));
			int weight = Integer.parseInt(matcher1b.group(1));
			Operations.createContainer(ID, weight, 'N');
			}
		
		Matcher matcher2 = pattern2.matcher(input);
		if (matcher2.find()) {
			int ID = Integer.parseInt(matcher2.group(0));
			int maxWeight = Integer.parseInt(matcher2.group(1));
			int maxAll = Integer.parseInt(matcher2.group(2));
			int maxHeavy = Integer.parseInt(matcher2.group(3));
			int maxRefrige = Integer.parseInt(matcher2.group(4));
			int maxLiquid = Integer.parseInt(matcher2.group(5));
			double fuelCon = Double.parseDouble(matcher2.group(6));
			Operations.createShip(ID, maxWeight, 
					maxAll, maxHeavy, maxRefrige, maxLiquid, fuelCon);
		}
		
		Matcher matcher3 = pattern3.matcher(input);
		if (matcher3.find()) {
			double X = Double.parseDouble(matcher3.group(0));
			double Y = Double.parseDouble(matcher3.group(1));
			Operations.createPort(X, Y);
		}
		
		Matcher matcher4 = pattern4.matcher(input);
		if (matcher4.find()) {
			int shipID = Integer.parseInt(matcher4.group(0));
			int containerID = Integer.parseInt(matcher4.group(1));
			Operations.loading(shipID, containerID);
		}

		Matcher matcher5 = pattern5.matcher(input);
		if (matcher5.find()) {
			int shipID = Integer.parseInt(matcher5.group(0));
			int containerID = Integer.parseInt(matcher5.group(1));
			Operations.unloading(shipID, containerID);
		}
		
		Matcher matcher6 = pattern6.matcher(input);
		if (matcher6.find()) {
			int shipID = Integer.parseInt(matcher6.group(0));
			int portID = Integer.parseInt(matcher6.group(1));
			Operations.sailing(shipID, portID);
		}
		
		Matcher matcher7 = pattern7.matcher(input);
		if (matcher7.find()) {
			int shipID = Integer.parseInt(matcher7.group(0));
			double fuel = Double.parseDouble(matcher7.group(1));
			Operations.addFuel(shipID, fuel);
		}

	}
			
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
