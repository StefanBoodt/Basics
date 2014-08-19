package datastructures;

import java.util.Comparator;

/**
 * The random orderer orders Objects in a random order.
 * It uses {@link Math#random()} to calculate a random number and
 * returns 1 if it is >= 1 and -1 if it is not. Two Objects that are
 * equal will remain at 0.
 * 
 * @since 19-8-2014
 * @version 19-8-2014
 * 
 * @see Math
 * @see Comparator
 * 
 * @author stefanboodt
 *
 * @param <T> The type of objects that are compared.
 */
public class RandomOrderer<T> implements Comparator<T> {
	
	/**
	 * Creates a new RandomOrderer.
	 */
	public RandomOrderer() {
		super();
	}

	@Override
	public int compare(T o1, T o2) {
		if (o1.equals(o2)) {
			return 0;
		}
		double random = Math.random();
		if (random >= 0.5) {
			return 1;
		}
		return -1;
	}

}
