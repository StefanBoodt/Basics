package basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class provides some basic test.
 * 
 * @since 08-11-2015
 * @version 08-11-2015
 * 
 * @author stefanboodt
 *
 */
public abstract class BasicTest {
	
	/**
	 * The Object under test.
	 */
	private Object tested;

	/**
	 * Does some set up.
	 * @throws Exception If the set up fails.
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Does some clean up.
	 * @throws Exception If the clean up fails.
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Gets the Object under test.
	 * @return The object under the tests.
	 */
	protected Object getObjectUnderTest() {
		return tested;
	}
	
	/**
	 * Sets the Object under test.
	 * @param tested The new tested object.
	 */
	protected void setObjectUnderTest(Object tested) {
		this.tested = tested;
	}
	
	/**
	 * This test checks the hashCode method.
	 */
	@Test
	public void testHash() {
		assertEquals(tested.hashCode(), tested.hashCode());
	}

	/**
	 * Tests if the object is equal to itself.
	 */
	@Test
	public void testEquals() {
		assertEquals(tested, tested);
	}
	
	/**
	 * Tests if the object is equal to another Object.
	 */
	@Test
	public void testEqualsObject() {
		assertNotEquals(tested, new Object());
	}

	/**
	 * Tests if the object is equal to an exception.
	 */
	@Test
	public void testEqualsException() {
		assertNotEquals(tested, new Exception());
	}

	/**
	 * Tests if the object is equal to {@code null}.
	 */
	@Test
	public void testEqualsNull() {
		assertNotEquals(tested, null);
	}

}
