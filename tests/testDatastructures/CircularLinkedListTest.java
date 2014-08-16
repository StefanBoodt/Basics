package testDatastructures;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastructures.CircularLinkedList;

public class CircularLinkedListTest extends AbstractListTest {

	/**
	 * The list under test.
	 */
	private CircularLinkedList<String> list;
	
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
	 * Sets the list under test.
	 * @param list the list to set
	 */
	protected final void setList(CircularLinkedList<String> list) {
		this.list = list;
		super.setList(list);
	}
}
