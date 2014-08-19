package datastructures;

import java.util.Comparator;

/**
 * The default comparator compares two comparable Objects and compares
 * them using their own compare method.
 * 
 * @since 19-8-2014
 * @version 19-8-2014
 * 
 * @see Comparable
 * @see Comparator
 * 
 * @author stefanboodt
 *
 */
public class DefaultComparator<T extends Comparable<T>> implements
		Comparator<T> {

	/**
	 * Creates a default DefaultComparator.
	 */
	public DefaultComparator() {
		super();
	}

	@Override
	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}
}
