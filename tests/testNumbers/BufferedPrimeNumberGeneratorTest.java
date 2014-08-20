package testNumbers;

import static org.junit.Assert.*;
import org.junit.*;

import numbers.BufferedPrimeNumberGenerator;
import numbers.PrimeNumberGenerator;

/**
 * This class tests the BufferedPrimeNumberGenerator class.
 * 
 * <p>
 * Please note that the coverage and passages are changed each time the
 * class is executed. This has to do with the buffering. To restore the
 * passage and coverage to the original use the 
 * {@link BufferedPrimeNumberGenerator#reset()} method to reset it. The
 * buffer is cleared this way and so the passages and coverages are taken
 * from when the buffer was still empty. This hugely increases the
 * coverage and test meaning because all prime numbers have to be
 * recalculated. However the tests take a longer running time and so
 * does the calculation of the prime numbers elsewhere.
 * </p>
 * 
 * @since 20-8-2014
 * @version 20-8-2014
 * 
 * @see BufferedPrimeNumberGenerator
 * @see BufferedPrimeNumberGenerator#reset()
 * @see PrimeNumberGeneratorTest
 * 
 * @author stefanboodt
 *
 */
public class BufferedPrimeNumberGeneratorTest extends PrimeNumberGeneratorTest {

	/**
	 * The generator used.
	 */
	private BufferedPrimeNumberGenerator generator;
	
	/**
	 * Readies the class to be tested. This is the place where reset is
	 * called.
	 * @throws Exception If an exception is thrown by the method.
	 */
	/*@BeforeClass
	public void before() throws Exception {
		new BufferedPrimeNumberGenerator().reset();
	}/* Clears the buffer. Please use with caution.*/
	
	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		setGenerator(new BufferedPrimeNumberGenerator());
	}

	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		generator.save();
	}

	/**
	 * Tests the {@link BufferedPrimeNumberGenerator#isPrime(long)}.
	 */
	@Test
	public void testIsPrime13() {
		assertTrue(generator.isPrime(13));
	}
	
	/**
	 * Tests the {@link BufferedPrimeNumberGenerator#isPrime(long)}.
	 * It also verifies the time it took.
	 */
	@Test (timeout = 5)
	public void testIsPrime5Again() {
		assertTrue(generator.isPrime(5));
	}

	public void setGenerator(BufferedPrimeNumberGenerator gen) {
		super.setGenerator(gen);
		generator = gen;
	}
}
