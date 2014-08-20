package numbers;

import interfaces.*;

/**
 * The PrimeNumberGenerator generates prime numbers and can be used in
 * a for loop. This Generator is defaulted to see 1 not as a prime number.
 * 
 * @since 19-8-2014
 * @version 20-8-2014
 * 
 * @see BufferedPrimeNumberGenerator
 * @see ResetableIterator
 * 
 * @author stefanboodt
 *
 */
public class PrimeNumberGenerator implements ResetableIterator<Long> {

	/**
	 * The number it currently contains.
	 */
	protected long number;
	
	/**
	 * The number of steps taken.
	 */
	protected long steps;
	
	/**
	 * The amount of steps you want to take.
	 */
	protected final long AMOUNT_OF_STEPS;
	
	/**
	 * The minimal prime number.
	 */
	protected static final int FIRST_PRIME = 2;
	
	/**
	 * Infinite amount of steps, so you see each prime number pass.
	 */
	protected static final long INFINITE = Long.MAX_VALUE;
	
	/**
	 * The starting point.
	 */
	protected final int START;
	
	/**
	 * Creates a new prime number generator.
	 */
	public PrimeNumberGenerator() {
		this(FIRST_PRIME);
	}
	
	/**
	 * Creates a PrimeNumberGenerator that starts at the given starting
	 * point.
	 * @param startingPoint The starting point.
	 */
	public PrimeNumberGenerator(int startingPoint) {
		this(startingPoint, INFINITE);
	}
	
	/**
	 * Creates a PrimeNumberGenerator that starts at the given starting
	 * point.
	 * @param startingPoint The starting point.
	 * @param steps The maximum amount of steps you should take.
	 */
	public PrimeNumberGenerator(int startingPoint, long steps) {
		super();
		if (startingPoint < FIRST_PRIME) {
			startingPoint = FIRST_PRIME;
		}
		START = startingPoint;
		AMOUNT_OF_STEPS = steps;
		setUp();
	}
	
	private final void setUp() {
		number = START;
		steps = 0;
	}

	@Override
	public void reset() {
		setUp();
	}

	@Override
	public boolean hasNext() {
		return number >= 0 && steps < AMOUNT_OF_STEPS;
	}

	@Override
	public Long next() {
		steps++;
		long n = number;
		number++;
		while (!isPrime(number)) {
			number++;
		}
		return n;
	}

	/**
	 * Checks if the given number is a prime number. The lower the
	 * number, the sooner it is checked.
	 * @param number The number you want to check.
	 * @return true iff the number is prime.
	 */
	public boolean isPrime(long number) {
		if (number < FIRST_PRIME) {
			return false;
		}
		double sqrt = Math.sqrt(number);
		for (long i = 2; i <= sqrt; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
