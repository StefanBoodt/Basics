package testDatastructures;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastructures.CircularLinkedList;
import datastructures.EmptyDataStructureException;

/**
 * Tests the circular linked list class
 * 
 * @since 18-8-2014
 * @version 18-8-2014
 * 
 * @see CircularLinkedList
 * 
 * @author stefanboodt
 *
 */
public class CircularLinkedListTest extends AbstractListTest {

	/**
	 * The list under test.
	 */
	private CircularLinkedList<String> list;
	
	/**
	 * Some random string.
	 */
	private final String s1 = "kvlksndflknfhlkjsjfj487y2u3j";
	
	/**
	 * Some random string.
	 */
	private final String s2 = "h82y3uhjkhabfjhonlkcjisfjdnslkf";
	
	/**
	 * Some random string.
	 */
	private final String s3 = "Ofjisjfnkbdmvl?????jdsfnjksdnfopi9";
	
	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		setList(new CircularLinkedList<String>());
	}

	@Override
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Tests the {@link CircularLinkedList#addFirst(Object)}
	 */
	@Test
	public void testAddFirst() {
		list.add(s1);
		list.add(s2);
		list.addFirst(s3);
		assertEquals(s3, list.get(0));
	}
	
	/**
	 * Tests the {@link CircularLinkedList#addLast(Object)}
	 */
	@Test
	public void testAddLast() {
		list.add(s1);
		list.add(s2);
		list.addLast(s3);
		assertEquals(s3, list.get(2));
	}
	
	/**
	 * Tests the {@link CircularLinkedList#removeFirst()}
	 */
	@Test
	public void testRemoveFirst() {
		list.add(s1);
		list.add(s2);
		list.addFirst(s3);
		list.removeFirst();
		assertFalse(list.contains(s3));
	}
	
	/**
	 * Tests the {@link CircularLinkedList#removeLast()}
	 */
	@Test
	public void testRemoveLast() {
		list.add(s1);
		list.add(s2);
		list.addLast(s3);
		list.removeLast();
		assertFalse(list.contains(s3));
	}
	
	/**
	 * Tests the {@link CircularLinkedList#removeFirst()}
	 */
	@Test (expected = EmptyDataStructureException.class)
	public void testRemoveFirstEmpty() {
		list.removeFirst();
	}
	
	/**
	 * Tests the {@link CircularLinkedList#removeLast()}
	 */
	@Test (expected = EmptyDataStructureException.class)
	public void testRemoveLastEmpty() {
		list.removeLast();
	}
	
	/**
	 * Tests the {@link CircularLinkedList#removeFirst()}
	 */
	@Test
	public void testRemoveFirst1() {
		list.addLast(s1);
		list.removeFirst();
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Tests the {@link CircularLinkedList#removeLast()}
	 */
	@Test
	public void testRemoveLast1() {
		list.addFirst(s1);
		list.removeLast();
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Tests the {@link CircularLinkedList#removeFirst()}
	 */
	@Test
	public void testRemoveFirst1A() {
		list.addFirst(s1);
		list.removeFirst();
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Tests the {@link CircularLinkedList#removeLast()}
	 */
	@Test
	public void testRemoveLast1A() {
		list.addLast(s1);
		list.removeLast();
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Tests the {@link CircularLinkedList#remove(int)}
	 */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testRemoveIndexEmpty2() {
		list.add(s1);
		list.remove(-1);
	}
	
	/**
	 * Tests the {@link CircularLinkedList#remove(int)}
	 */
	@Test (expected = EmptyDataStructureException.class)
	public void testRemoveIndexEmpty() {
		list.remove(0);
	}
	
	/**
	 * Tests the {@link CircularLinkedList#removeFirst()}
	 */
	@Test
	public void testRemoveFirstSize2() {
		list.add(s1);
		list.add(s2);
		list.addFirst(s3);
		list.removeFirst();
		assertEquals(2, list.size());
	}
	
	/**
	 * Tests the {@link CircularLinkedList#removeLast()}
	 */
	@Test
	public void testRemoveLastSize2() {
		list.add(s1);
		list.add(s2);
		list.addLast(s3);
		list.removeLast();
		assertEquals(2, list.size());
	}
	
	/**
	 * Sets the list under test.
	 * @param list the list to set
	 */
	protected final void setList(CircularLinkedList<String> list) {
		this.list = list;
		super.setList(list);
	}
}
