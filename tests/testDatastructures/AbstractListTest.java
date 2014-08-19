package testDatastructures;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastructures.AbstractList;
import datastructures.CircularLinkedList;

/**
 * Tests the AbstractList class. An empty list should be passed
 * to make sure that the tests pass.
 * 
 * @since 16-8-2014
 * @version 19-8-2014
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
	 * Tests if the toArray method works fine.
	 */
	@Test
	public void testToArray() {
		String[] array = {"Poop",
				"Kick",
				"Lowland",
				"Master",
				"Pee"
		};
		list.clear();
		list.addAll(new CircularLinkedList<String>(array));
		Object[] array2 = list.toArray();
		/*
			System.out.println(list.size());
			System.out.println(asString(array));
			System.out.println(asString(array2));
		*/
		assertArrayEquals(array, array2);
	}
	
	/**
	 * Tests if the toArray method works fine.
	 */
	@Test
	public void testToArrayWithArgument() {
		String[] array = {"Poop",
				"Kick",
				"Lowland",
				"Master",
				"Pee"
		};
		String[] emptyarray = new String[5];
		list.clear();
		list.addAll(new CircularLinkedList<String>(array));
		String[] array2 = list.toArray(emptyarray);
		assertArrayEquals(array, array2);
	}
	
	/**
	 * Tests get on an List with 1 element.
	 */
	@Test
	public void testGet1Element() {
		list.add("Piper");
		assertEquals("Piper", list.get(0));
	}
	
	/**
	 * Tests get on an list.
	 */
	@Test
	public void testGet() {
		list.add("Piper");
		list.add("Poop");
		list.add("Diarrhea");
		assertEquals("Piper", list.get(0));
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
	 * Test if two Lists with the same adress have the same hashcode.
	 */
	@Test
	public void testHashCodeSameAdress() {
		list.add("Winer");
		assertEquals(list.hashCode(), list.hashCode());
	}
	
	/**
	 * Tests to see if the clone has a different adress.
	 * @throws CloneNotSupportedException Never.
	 */
	@Test
	public void testCloneAdress() throws CloneNotSupportedException {
		list.add("Winer");
		AbstractList<String> l2 = list.clone();
		assertFalse(l2 == list);
	}
	
	/**
	 * Tests to see if the clone is equal.
	 * @throws CloneNotSupportedException Never.
	 */
	@Test
	public void testCloneEquals() throws CloneNotSupportedException {
		AbstractList<String> l2 = list.clone();
		assertEquals(list, l2);
	}
	
	/**
	 * Test if two Lists with the same adress have the same hashcode.
	 * @throws CloneNotSupportedException If the CloneAble Interface is
	 * not implemented. Since AbstractList does this the Exception can
	 * only arise with really bad programming, such as overriding the
	 * clone method to always throw the exception.
	 */
	@Test
	public void testHashCode() throws CloneNotSupportedException {
		if (list.size() == 0) {
			list.add("Winer");
		}
		List<String> l2 = list.clone();
		assertEquals(list.hashCode(), l2.hashCode());
	}
	
	/**
	 * Tests the {@link AbstractList#add(int, Object)} method inserts
	 * it in the right place.
	 */
	@Test
	public void testGetAfterAddingWithIndex0() {
		list.add("Pokey");
		list.add("Wine");
		list.add("Param");
		list.add(0, "Must");
		assertEquals("Must", list.get(0));
	}
	
	/**
	 * Tests the {@link AbstractList#add(int, Object)} method inserts
	 * it in the right place.
	 */
	@Test
	public void testGetAfterAddingWithIndex() {
		list.add("Pokey");
		list.add("Wine");
		list.add("Param");
		list.add(2, "Must");
		assertEquals("Must", list.get(2));
	}
	
	/**
	 * Tests equality
	 */
	@Test
	public void testEqualsWithObject() {
		list.add("pij");
		assertNotEquals(list, new Object());
	}
	
	/**
	 * Tests equality
	 */
	@Test
	public void testEqualsWithException() {
		list.add("pij");
		assertFalse(list.equals(new Exception()));
	}
	
	/**
	 * A random string.
	 */
	private final String s1 = "Hi";
	
	/**
	 * Another string shortcut.
	 */
	private final String s2 = "Bye";
	
	/**
	 * Tests equality
	 */
	@Test
	public void testEqualsDifferentElements() {
		List<String> l2 = list.newList();
		list.add(s1);
		list.add("lol");
		list.add(s2);
		l2.add(s1);
		l2.add("lOl");
		l2.add(s2);
		assertFalse(list.equals(l2));
	}
	
	/**
	 * Tests equality
	 */
	@Test
	public void testEqualsDifferentOrder() {
		List<String> l2 = list.newList();
		list.add(s1);
		list.add(s2);
		list.add("lol");
		l2.add(s1);
		l2.add("lol");
		l2.add(s2);
		assertFalse(list.equals(l2));
	}
	
	/**
	 * Tests equality
	 */
	@Test
	public void testShallowEqualsDifferentElements() {
		List<String> l2 = list.newList();
		list.add(s1);
		list.add("lol");
		list.add(s2);
		l2.add(s1);
		l2.add("lOl");
		l2.add(s2);
		assertFalse(list.shallowEquals(l2));
	}
	
	/**
	 * Tests equality
	 */
	@Test
	public void testShallowEqualsDifferentOrder() {
		List<String> l2 = list.newList();
		list.add(s1);
		list.add(s2);
		list.add("lol");
		l2.add(s1);
		l2.add("lol");
		l2.add(s2);
		assertTrue(list.shallowEquals(l2));
	}
	
	/**
	 * Tests equality
	 * @throws CloneNotSupportedException If the list can't be cloned.
	 */
	@Test
	public void testShallowEqualsDifferentSize() throws CloneNotSupportedException {
		List<String> l2 = list.clone();
		l2.add("Winer");
		assertFalse(list.shallowEquals(l2));
	}
	
	/**
	 * Tests the equality with the same adress.
	 */
	@Test
	public void testShallowEqualsWithSameAdress() {
		assertTrue(list.shallowEquals(list));
	}
	
	/**
	 * Tests equality
	 * @throws CloneNotSupportedException If the list can't be cloned.
	 */
	@Test
	public void testEqualsDifferentSize() throws CloneNotSupportedException {
		List<String> l2 = list.clone();
		l2.add("Winer");
		assertFalse(list.equals(l2));
	}
	
	/**
	 * Tests the equality with the same adress.
	 */
	@Test
	public void testEqualsWithSameAdress() {
		assertEquals(list, list);
	}
	
	/**
	 * Tests the index of method.
	 */
	@Test
	public void testIndexOfNotFoundObject() {
		assertEquals(-1, list.indexOf(new Object()));
	}
	
	/**
	 * Tests the index of method.
	 */
	@Test
	public void testIndexOfNotFound() {
		assertEquals(-1, list.indexOf(s1));
	}
	
	/**
	 * Tests the index of method.
	 */
	@Test
	public void testIndexOf3() {
		list.add("Poop");
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		assertEquals(3, list.indexOf(s3));
	}
	
	/**
	 * Tests the index of method.
	 */
	@Test
	public void testIndexOf4() {
		list.add("Poop");
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		assertEquals(4, list.indexOf(s4));
	}
	
	/**
	 * Tests the index of method.
	 */
	@Test
	public void testIndexOf0() {
		list.add("Poop");
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		assertEquals(0, list.indexOf("Poop"));
	}
	
	/**
	 * Tests the index of method.
	 */
	@Test
	public void testLastIndexOfNotFoundObject() {
		assertEquals(-1, list.lastIndexOf(new Object()));
	}
	
	/**
	 * Tests the index of method.
	 */
	@Test
	public void testLastIndexOfNotFound() {
		assertEquals(-1, list.lastIndexOf(s1));
	}
	
	/**
	 * Tests the index of method.
	 */
	@Test
	public void testLastIndexOf3() {
		list.add("Poop");
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		assertEquals(3, list.lastIndexOf(s3));
	}
	
	/**
	 * Tests the index of method.
	 */
	@Test
	public void testLastIndexOf3Double() {
		list.add("Poop");
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s3);
		assertEquals(5, list.lastIndexOf(s3));
	}
	
	/**
	 * Tests the index of method.
	 */
	@Test
	public void testIndexOf3Double() {
		list.add("Poop");
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s3);
		assertEquals(3, list.indexOf(s3));
	}
	
	/**
	 * Tests the index of method.
	 */
	@Test
	public void testLastIndexOf4() {
		list.add("Poop");
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		assertEquals(4, list.lastIndexOf(s4));
	}
	
	/**
	 * Tests the index of method.
	 */
	@Test
	public void testLastIndexOf0() {
		list.add("Poop");
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		assertEquals(0, list.lastIndexOf("Poop"));
	}
	
	/**
	 * Tests the {@link AbstractList#subList(int, int)} method.
	 */
	@Test
	public void testSubListCompleteRange() {
		list.add(s3);
		list.add(s4);
		List<String> l = list.subList(0, list.size());
		assertEquals(list, l);
	}
	
	/**
	 * Tests the {@link AbstractList#subList(int, int)} method.
	 */
	@Test
	public void testSubList() {
		list.add(s3);
		list.add(s1);
		list.add(s4);
		list.add(s2);
		List<String> list2 = list.newList();
		list2.add(s1);
		list2.add(s4);
		List<String> correct = list.newList();
		correct.add(s1);
		assertEquals(correct, list.subList(1, 2));
	}
	
	/**
	 * Tests the clear and newList methods.
	 */
	@Test
	public void testClearAndNewList() {
		list.add(s2);
		list.clear();
		List<String> l1 = list.newList();
		assertEquals(list, l1);
	}
	
	/**
	 * Tests the {@link AbstractList#clear()} method.
	 */
	@Test
	public void testClear() {
		List<String> l2 = new CircularLinkedList<String>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.clear();
		assertEquals(l2, list);
	}
	
	/**
	 * Tests the {@link AbstractList#clear()} method.
	 */
	@Test
	public void testClearSize() {
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.clear();
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests unify with two lists.
	 */
	@Test
	public void testUnifyEmptyList() {
		AbstractList<String> l2 = list.newList();
		list.add(s1);
		Set<String> l1 = new HashSet<String>();
		l1.add(s1);
		assertEquals(l1, list.unify(l2));
	}
	
	/**
	 * Tests unify with two lists.
	 */
	@Test
	public void testUnify() {
		AbstractList<String> l2 = list.newList();
		list.add(s1);
		l2.add(s2);
		Set<String> l1 = new HashSet<String>();
		l1.add(s1);
		l1.add(s2);
		assertEquals(l1, list.unify(l2));
	}
	
	/**
	 * Tests the {@link AbstractList#difference(List)} method.
	 */
	@Test
	public void testDifferenceOneEmpty() {
		list.add(s1);
		Set<String> set = new HashSet<String>();
		set.add(s1);
		List<String> l1 = list.newList();
		assertEquals(set, list.difference(l1));
	}
	
	/**
	 * Tests the {@link AbstractList#difference(List)} method.
	 */
	@Test
	public void testDifferenceOtherEmpty() {
		Set<String> set = new HashSet<String>();
		List<String> l1 = list.newList();
		l1.add(s1);
		assertEquals(set, list.difference(l1));
	}
	
	/**
	 * Tests the {@link AbstractList#difference(List)} method.
	 */
	@Test
	public void testDifferenceBothEmpty() {
		Set<String> set = new HashSet<String>();
		List<String> l1 = list.newList();
		assertEquals(set, list.difference(l1));
	}
	
	/**
	 * Tests the {@link AbstractList#difference(List)} method.
	 */
	@Test
	public void testDifference() {
		list.add(s1);
		list.add(s2);
		list.add(s3);
		Set<String> set = new HashSet<String>();
		set.add(s1);
		List<String> l1 = list.newList();
		l1.add(s2);
		l1.add(s3);
		l1.add(s4);
		assertEquals(set, list.difference(l1));
	}
	
	/**
	 * Tests the {@link AbstractList#difference(List)} method.
	 */
	@Test
	public void testSymmetricDifference() {
		list.add(s1);
		list.add(s2);
		list.add(s3);
		Set<String> set = new HashSet<String>();
		set.add(s1);
		set.add(s4);
		AbstractList<String> l1 = list.newList();
		l1.add(s2);
		l1.add(s3);
		l1.add(s4);
		assertEquals(set, list.symmetricDifference(l1));
	}
	
	/**
	 * Tests the {@link AbstractList#difference(List)} method.
	 */
	@Test
	public void testDifferenceAndSymmetricDifference() {
		AbstractList<String> l1 = list.newList();
		assertEquals(list.symmetricDifference(l1),
				list.difference(l1));
	}
	
	/**
	 * Tests the {@link AbstractList#removeAll(Object)} method.
	 */
	@Test
	public void testRemoveAllObject() {
		list.add(s1);
		list.add(s1);
		list.add(s1);
		list.removeAll(s1);
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Tests the {@link AbstractList#removeAll(Object)} method.
	 */
	@Test
	public void testRemoveAllObjectWhenOtherObjectIsInListWithContains() {
		list.add(s1);
		list.add(s1);
		list.add(s1);
		list.add(s2);
		list.removeAll(s1);
		assertFalse(list.contains(s1));
	}
	
	/**
	 * Tests the {@link AbstractList#removeAll(Object)} method.
	 */
	@Test
	public void testRemoveAllObjectWhenOtherObjectIsInList() {
		list.add(s1);
		list.add(s1);
		list.add(s1);
		list.add(s2);
		//System.out.println(list);
		list.removeAll(s1);
		//System.out.println(list);
		assertEquals(1, list.size());
	}
	
	/**
	 * Tests the {@link AbstractList#removeAll(Object)} method.
	 */
	@Test
	public void testRemoveAllObjectWhenOtherObjectIsInList2() {
		list.add(s1);
		list.add(s1);
		list.add(s2);
		list.add(s1);
		list.removeAll(s1);
		assertEquals(1, list.size());
	}
	
	/**
	 * Tests if the {@link AbstractList#removeAll(Collection)} method
	 * works fine.
	 */
	@Test
	public void testRemoveAll() {
		Set<String> set = new HashSet<String>();
		set.add(s1);
		set.add(s2);
		set.add(s3);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.removeAll(set);
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Tests if the {@link AbstractList#removeAll(Collection)} method
	 * works fine.
	 */
	@Test
	public void testRemoveAllReturn() {
		Set<String> set = new HashSet<String>();
		set.add(s1);
		set.add(s2);
		set.add(s3);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		assertTrue(list.removeAll(set));
	}
	
	/**
	 * Tests if the {@link AbstractList#removeAll(Collection)} method
	 * works fine.
	 * @throws CloneNotSupportedException If cloning is not possible.
	 */
	@Test
	public void testRemoveAllNotFound() throws CloneNotSupportedException {
		Set<String> set = new HashSet<String>();
		set.add(s1);
		set.add(s2);
		set.add(s3);
		list.add(s4);
		List<String> clone = list.clone();
		clone.removeAll(set);
		assertEquals(list, clone);
	}
	
	/**
	 * Tests the {@link AbstractList#intersect(List)} method.
	 */
	@Test
	public void testIntersect() {
		AbstractList<String> l2 = list.newList();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		l2.add(s2);
		l2.add(s3);
		l2.add(s4);
		Set<String> set = new HashSet<String>();
		set.add(s2);
		set.add(s3);
		assertEquals(set, list.intersect(l2));
	}
	
	/**
	 * Tests the {@link AbstractList#intersect(List)} method.
	 */
	@Test
	public void testIntersectCorners() {
		AbstractList<String> l2 = list.newList();
		list.add(s1);
		list.add(s2);
		list.add(s4);
		l2.add(s1);
		l2.add(s3);
		l2.add(s4);
		Set<String> set = new HashSet<String>();
		set.add(s1);
		set.add(s4);
		assertEquals(set, list.intersect(l2));
	}
	
	/**
	 * Tests the {@link AbstractList#intersect(List)} method.
	 */
	@Test
	public void testIntersectAll() {
		AbstractList<String> l2 = list.newList();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		l2.add(s1);
		l2.add(s2);
		l2.add(s3);
		l2.add(s4);
		Set<String> set = new HashSet<String>();
		set.add(s2);
		set.add(s3);
		set.add(s4);
		set.add(s1);
		assertEquals(set, list.intersect(l2));
	}
	
	/**
	 * Tests the {@link AbstractList#intersect(List)} method.
	 */
	@Test
	public void testIntersectNoIntersection() {
		AbstractList<String> l2 = list.newList();
		list.add(s1);
		list.add(s2);
		l2.add(s3);
		l2.add(s4);
		Set<String> set = new HashSet<String>();
		assertEquals(set, list.intersect(l2));
	}
	
	/**
	 * Tests the listIterator method.
	 */
	@Test (expected = UnsupportedOperationException.class)
	public void testListIterator() {
		list.listIterator();
	}
	
	/**
	 * Tests the listIterator method that takes a parameter.
	 */
	@Test (expected = UnsupportedOperationException.class)
	public void testListIteratorWithParameter() {
		list.listIterator(0);
	}
	
	/**
	 * Tests the {@link AbstractList#RetainAll(List)} method.
	 */
	@Test (timeout = 2000)
	public void testRetainAll() {
		AbstractList<String> l2 = list.newList();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		l2.add(s2);
		l2.add(s3);
		l2.add(s4);
		AbstractList<String> set = list.newList();
		set.add(s2);
		set.add(s3);
		list.retainAll(l2);
		assertEquals(set, list);
	}
	
	/**
	 * Tests the {@link AbstractList#RetainAll(List)} method.
	 */
	@Test (timeout = 2000)
	public void testRetainAllCorners() {
		AbstractList<String> l2 = list.newList();
		list.add(s1);
		list.add(s2);
		list.add(s4);
		l2.add(s1);
		l2.add(s3);
		l2.add(s4);
		AbstractList<String> set = list.newList();
		set.add(s1);
		set.add(s4);
		list.retainAll(l2);
		assertEquals(set, list);
	}
	
	/**
	 * Tests the {@link AbstractList#RetainAll(List)} method.
	 */
	@Test (timeout = 2000)
	public void testRetainAllAll() {
		AbstractList<String> l2 = list.newList();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		l2.add(s1);
		l2.add(s2);
		l2.add(s3);
		l2.add(s4);
		AbstractList<String> set = list.newList();
		set.add(s1);
		set.add(s2);
		set.add(s3);
		set.add(s4);
		list.retainAll(l2);
		assertEquals(set, list);
	}
	
	/**
	 * Tests the {@link AbstractList#RetainAll(List)} method.
	 */
	@Test (timeout = 2000)
	public void testRetainAllNoIntersection() {
		AbstractList<String> l2 = list.newList();
		list.add(s1);
		list.add(s2);
		l2.add(s3);
		l2.add(s4);
		AbstractList<String> set = list.newList();
		list.retainAll(l2);
		assertEquals(set, list);
	}
	
	/**
	 * Tests the correctness of the {@link AbstractList#removeAll(Object)}
	 * method to make sure an Object coming only in the first position is
	 * removed.
	 */
	@Test
	public void removeAllFirst() {
		list.add(s1);
		list.add(s2);
		list.add(s4);
		list.removeAll(s1);
		assertFalse(list.contains(s1));
	}
	
	/**
	 * Tests the {@link AbstractList#addAll(int, Collection)} method.
	 */
	@Test
	public void testAddAllIndex() {
		List<String> correct = list.newList();
		list.add(s1);
		LinkedList<String> addable = new LinkedList<String>();
		addable.add(s2);
		addable.add(s3);
		addable.add(s4);
		list.add(s1);
		list.addAll(1, addable);
		correct.add(s1);
		correct.add(s2);
		correct.add(s3);
		correct.add(s4);
		correct.add(s1);
		assertEquals(correct, list);
	}
	
	/**
	 * Tests the set method.
	 */
	@Test
	public void testSet() {
		list.add(s1);
		list.add(s2);
		list.set(0, s3);
		assertEquals(s3, list.get(0));
	}

	/**
	 * Sets the list to the given value.
	 * @param list the list to set
	 */
	protected final void setList(AbstractList<String> list) {
		this.list = list;
	}
	
	/**
	 * Represents an array with a String.
	 * @param array The array to represent.
	 * @return a string representation of the array.
	 */
	protected String asString(Object[] array) {
		String str = "[";
		for (Object o: array) {
			if (o instanceof Object[]) {
				str += asString((Object[]) o) + ", ";
			}
			else {
				str += o.toString() + ", ";
			}
		}
		if (str.length() >= 2) {
			str = str.substring(0, str.length() - 2);
		}
		return str + "]";
	}
	
	/**
	 * A random string.
	 */
	private final String s3 = "klnvkdsnmf,/FOSdjfkskfsmklsmf,.m";
	
	/**
	 * A random string.
	 */
	private final String s4 = "kfjkvnjkdnfjknifjkljfinsjdnfjnsfmavnvDOIFKSFNjfjksb"
			+ "knlnfmsncsVKJkav  jfknksnnknfkdlsnklnfklv.s,vv,anfdkdslnklgnlkg94u298u"
			+ "iodsufhjkfvnoisdfhjlj";
}
