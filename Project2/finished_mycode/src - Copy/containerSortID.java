package containers;

import java.util.Comparator;

public class containerSortID implements Comparator<Container>{
	
	/**
	 * compare() method for containers. This is used to sort containers only by their
	 * ID. The compareTo() method in the Comparable interace sorts containers first by
	 * their type, then by their ID. Since the printing out operation requires us to 
	 * print the containers out first by their types then by their IDs, the compareTo() 
	 * method is used in those situations.
	 */
	@Override
	public int compare(Container c1, Container c2) {
		return c1.getID() - c2.getID();
	}
}
