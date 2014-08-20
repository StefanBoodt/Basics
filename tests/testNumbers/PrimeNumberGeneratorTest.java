package testNumbers;

import static org.junit.Assert.*;
import numbers.PrimeNumberGenerator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the PrimeNumberGenerator class.
 * 
 * @since 20-8-2014
 * @version 20-8-2014
 * 
 * @see PrimeNumberGenerator
 * 
 * @author stefanboodt
 *
 */
public class PrimeNumberGeneratorTest {

	/**
	 * The PrimeNumberGenerator under test.
	 */
	private PrimeNumberGenerator generator;
	
	/**
	 * Does any necessary set up. Subclasses should first call
	 * super.setUp before setting their own values.
	 * @throws Exception If the clean up doesn't happen.
	 */
	@Before
	public void setUp() throws Exception {
		setGenerator(new PrimeNumberGenerator());
	}

	/**
	 * Does any necessary clean up.
	 * @throws Exception If an exception is thrown in the clean up code.
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Tests the {@link PrimeNumberGenerator#isPrime(long)} method.
	 */
	@Test
	public void testIsPrimeFarNegative() {
		assertFalse(generator.isPrime(-27));
	}
	
	/**
	 * Tests the {@link PrimeNumberGenerator#isPrime(long)} method.
	 */
	@Test
	public void testIsPrimeMinus1() {
		assertFalse(generator.isPrime(-1));
	}
	
	/**
	 * Tests the {@link PrimeNumberGenerator#isPrime(long)} method.
	 */
	@Test
	public void testIsPrime0() {
		assertFalse(generator.isPrime(0));
	}
	
	/**
	 * Tests the {@link PrimeNumberGenerator#isPrime(long)} method.
	 */
	@Test
	public void testIsPrime1() {
		assertFalse(generator.isPrime(1));
	}

	/**
	 * Tests the {@link PrimeNumberGenerator#isPrime(long)} method.
	 */
	@Test
	public void testIsPrime2() {
		assertTrue(generator.isPrime(2));
	}
	
	/**
	 * Tests the {@link PrimeNumberGenerator#isPrime(long)} method.
	 */
	@Test
	public void testIsPrime3() {
		assertTrue(generator.isPrime(3));
	}
	
	/**
	 * Tests the {@link PrimeNumberGenerator#isPrime(long)} method.
	 */
	@Test
	public void testIsPrime4() {
		assertFalse(generator.isPrime(4));
	}
	
	/**
	 * Tests the {@link PrimeNumberGenerator#isPrime(long)} method.
	 */
	@Test
	public void testIsPrime5() {
		assertTrue(generator.isPrime(5));
	}
	
	/**
	 * Tests the {@link PrimeNumberGenerator#isPrime(long)} method.
	 */
	@Test
	public void testIsPrime6() {
		assertFalse(generator.isPrime(6));
	}
	
	/**
	 * Tests the {@link PrimeNumberGenerator#isPrime(long)} method.
	 */
	@Test
	public void testIsPrime7() {
		assertTrue(generator.isPrime(7));
	}
	
	/**
	 * Tests the {@link PrimeNumberGenerator#isPrime(long)} method.
	 */
	@Test
	public void testIsPrime11() {
		assertTrue(generator.isPrime(11));
	}
	
	/**
	 * Sets the generator to the given value.
	 * @param gen The new generator.
	 */
	public final void setGenerator(PrimeNumberGenerator gen) {
		generator = gen;
	}
}
