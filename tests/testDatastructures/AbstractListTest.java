package testDatastructures;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastructures.AbstractList;

/**
 * Tests the AbstractList class. An empty list should be passed
 * to make sure that the tests pass.
 * 
 * @since 16-8-2014
 * @version 16-8-2014
 * 
 * @see AbstractList
 * 
 * @author stefanboodt
 *
 */
public abstract class AbstractListTest {

	/**
	 * The list under test.
	 */
	private AbstractList<String> list;
	
	/**
	 * Does any set up for the class.
	 * @throws Exception If the set up fails.
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Does any clean up for the class.
	 * @throws Exception If the clean up fails.
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests if the list is Empty.
	 */
	@Test
	public void testEmpty() {
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Tests if the list is Empty.
	 */
	@Test
	public void testEmptyFalse() {
		list.add("Poop");
		assertFalse(list.isEmpty());
	}
	
	/**
	 * Tests if the list is Empty.
	 */
	@Test
	public void testSize0() {
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests if the list has the right size.
	 */
	@Test
	public void testSize1() {
		list.add("Poop");
		assertEquals(1, list.size());
	}
	
	/**
	 * Tests if an empty list contains something.
	 */
	@Test
	public void testContainsAllEmpty() {
		List<String> list2 = new ArrayList<String>();
		list2.add("kznnskfnmnafklnkjfAKFJSHFKnsfksljkf9");
		assertFalse(list.containsAll(list2));
	}
	
	/**
	 * Tests if a list contains a value.
	 */
	@Test
	public void testContainsAllTrue() {
		list.add("Poop");
		List<String> list2 = new ArrayList<String>();
		list2.add("Poop");
		assertTrue(list.containsAll(list2));
	}
	
	/**
	 * Tests if a list contains a value.
	 */
	@Test
	public void testContainsAllMultiple() {
		list.add("P");
		list.add("jdfhksfnm");
		list.add("kword");
		list.add("Kword");
		list.add("PipER");
		List<String> list2 = new ArrayList<String>();
		list2.add("PipER");
		list2.add("Kword");
		assertTrue(list.containsAll(list2));
	}
	
	/**
	 * Tests if a list contains a value.
	 */
	@Test
	public void testContainsLast() {
		list.add("P");
		list.add("jdfhksfnm");
		list.add("kword");
		list.add("Kword");
		list.add("PipER");
		assertTrue(list.contains("PipER"));
	}
	
	/**
	 * Tests if a list contains a value.
	 */
	@Test
	public void testContainsFalse() {
		list.add("P");
		list.add("jdfhksfnm");
		list.add("kword");
		list.add("PipER");
		assertFalse(list.contains("Kword"));
	}
	
	/**
	 * Tests if an empty list contains something.
	 */
	@Test
	public void testContainsEmpty() {
		assertFalse(list.contains("Pfjkskhjlfnskjnv"));
	}
	
	/**
	 * Tests if a list contains an exception.
	 */
	@Test
	public void testContainsException() {
		assertFalse(list.contains(new Exception()));
	}
	
	/**
	 * Tests if a list contains an Object.
	 */
	@Test
	public void testContainsObject() {
		assertFalse(list.contains(new Object()));
	}
	
	/**
	 * Tests if a list contains a value.
	 */
	@Test
	public void testContainsTrue() {
		list.add("Poop");
		assertTrue(list.contains("Poop"));
	}
	
	/**
	 * Tests if a list contains a value.
	 */
	@Test
	public void testContainsMultiple() {
		list.add("P");
		list.add("jdfhksfnm");
		list.add("kword");
		list.add("Kword");
		list.add("PipER");
		assertTrue(list.contains("Kword"));
	}
	
	/**
	 * Tests if a list contains all values.
	 */
	@Test
	public void testContainsAllLast() {
		list.add("P");
		list.add("jdfhksfnm");
		list.add("kword");
		list.add("Kword");
		list.add("PipER");
		List<String> list2 = new ArrayList<String>();
		list2.add("PipER");
		assertTrue(list.containsAll(list2));
	}
	
	/**
	 * Tests if a list contains all values.
	 */
	@Test
	public void testContainsAll2() {
		list.add("P");
		list.add("jdfhksfnm");
		list.add("kword");
		list.add("Kword");
		list.add("PipER");
		List<String> list2 = new ArrayList<String>();
		list2.add("PipER");
		list2.add("P");
		assertTrue(list.containsAll(list2));
	}
	
	/**
	 * Tests if add succeeds.
	 */
	@Test
	public void testAdd() {
		assertTrue(list.add("Poep"));
	}
	
	/**
	 * Tests if a list contains all values.
	 */
	@Test
	public void testContainsAllSelf() {
		list.add("P");
		list.add("jdfhksfnm");
		list.add("kword");
		list.add("Kword");
		list.add("PipER");
		assertTrue(list.containsAll(list));
	}
	
	/**
	 * Tests if a list contains a value.
	 */
	@Test
	public void testContainsAllFalse() {
		list.add("P");
		list.add("jdfhksfnm");
		list.add("kword");
		list.add("PipER");
		List<String> p = new ArrayList<String>();
		p.add("Kword");
		assertFalse(list.containsAll(p));
	}
	
	/**
	 * Tests if the list has the right size.
	 */
	@Test
	public void testSize7() {
		list.add("Poop");
		list.add("fjhksnfjknc");
		list.add("d");
		list.add("Poop");
		list.add("fjhksnfjknc");
		list.add("d");
		list.add("36748294");
		assertEquals(7, list.size());
	}

	/**
	 * Sets the list to the given value.
	 * @param list the list to set
	 */
	protected final void setList(AbstractList<String> list) {
		this.list = list;
	}
}
