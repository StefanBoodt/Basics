package numbers;

import java.io.*;
import java.util.*;

import datastructures.DefaultComparator;

/**
 * The BufferedPrimeNumberGenerator is a PrimeNumberGenerator that uses
 * buffers to keep track of previous calculated prime numbers and uses
 * these results to fasten the calculation time of the prime numbers.
 * 
 * @since 19-8-2014
 * @version 20-8-2014
 * 
 * @see PrimeNumberGenerator
 * 
 * @author stefanboodt
 *
 */
public class BufferedPrimeNumberGenerator extends PrimeNumberGenerator {

	/**
	 * The buffering list.
	 */
	private static List<Long> primeNumbers;
	
	/**
	 * Keeps track of a change in the list.
	 */
	private boolean changed;
	
	/**
	 * Creates a new BufferedPrimeNumberGenerator.
	 */
	public BufferedPrimeNumberGenerator() {
		this(FIRST_PRIME);
	}

	/**
	 * Creates a PrimeNumberGenerator that starts at the given starting
	 * point.
	 * @param startingPoint The starting point.
	 */
	public BufferedPrimeNumberGenerator(int startingPoint) {
		this(startingPoint, INFINITE);
	}
	
	/**
	 * Creates a PrimeNumberGenerator that starts at the given starting
	 * point.
	 * @param startingPoint The starting point.
	 * @param steps The maximum amount of steps you should take.
	 */
	public BufferedPrimeNumberGenerator(int startingPoint, long steps) {
		super(startingPoint, steps);
		setUp();
		changed = false;
	}
	
	/**
	 * Sets up the buffer.
	 */
	private final synchronized void setUp() {
		if (primeNumbers == null) {
			primeNumbers = new ArrayList<Long>();
			try {
				Scanner sc = new Scanner(new File("files/primenumbers.txt"));
				while (sc.hasNext()) {
					primeNumbers.add(Long.parseLong(sc.next()));
				}
				sc.close();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		sortNumbers();
	}
	
	/**
	 * {@inheritDoc}
	 * This uses the stored list of primeNumbers first. This saves
	 * a lot of time when used frequently but when the list is
	 * corrupted or this version is seldom used it is quicker to use
	 * the superclass variant. The list is also considered corrupted
	 * when there is a prime number missing in the sequence.
	 * @see PrimeNumberGenerator#isPrime(long)
	 */
	@Override
	public boolean isPrime(long number) {
		if (number < FIRST_PRIME) {
			return false;
		}
		if (primeNumbers.contains(number)) {
			return true;
		}
		double sqrt = Math.sqrt(number);
		long num = FIRST_PRIME;
		long largestNum = num;
		for (int i = 0; i < primeNumbers.size(); i++) {
			num = primeNumbers.get(i);
			if (num <= sqrt) {
				if (number % num == 0) {
					return false;
				}
				if (num > largestNum) {
					largestNum = num;
				}
			}
		}
		if (sqrt > largestNum) {
			boolean answer = super.isPrime(number);
			if (answer) {
				enlist(number);
			}
			return answer;
		}
		enlist(number);
		return true;
	}
	
	/**
	 * Enlists the number permanently as a prime number.
	 * This method should be used only when the {@link #isPrime(long)}
	 * states the number to be a prime number.
	 * @param number The number to enlist.
	 */
	protected final void enlist(long number) {
		primeNumbers.add(number);
		changed = true;
	}
	
	/**
	 * Sorts the number using the sort method.
	 */
	protected void sortNumbers() {
		primeNumbers.sort(new DefaultComparator<Long>());
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * In addition to resetting the list of prime numbers it also saves
	 * the newly created list of primenumbers to the file. Notice that
	 * this method calls {@link #save()} in addition to the superclass
	 * reset.
	 * </p>
	 * @see #save()
	 */
	@Override
	public void reset() {
		if (!primeNumbers.isEmpty()) {
			primeNumbers = new ArrayList<Long>();
			try {
				save();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		super.reset();
	}
	
	/**
	 * Saves the list of prime numbers to the file, so you can start using
	 * that as given next time.
	 * @throws FileNotFoundException If the file doesn't exist and cannot
	 * be created.
	 */
	public synchronized void save() throws FileNotFoundException {
		if (changed) {
			PrintWriter out = new PrintWriter(new File("files/primenumbers.txt"));
			String line = "";
			sortNumbers();
			for (int i = 1; i <= primeNumbers.size(); i++) {
				line += primeNumbers.get(i - 1) + " ";
				if (i == primeNumbers.size()) {
					out.print(line);
					out.flush();
				}
				else if (i % 15 == 0) {
					out.println(line);
					out.flush();
					line = "";
				}
			}
			out.close();
			changed = false;
		}
	}
}
