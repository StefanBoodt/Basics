package datastructures;

import java.util.*;

/**
 * The circular Linked list is a list that is singly linked and
 * circular. It is known to be the preffered list when implementing a
 * Stack.
 * 
 * @since 16-8-2014
 * @version 16-8-2014
 * 
 * @see AbstractList<E>
 * @see List
 * @see Collection
 * @see Iterable
 * 
 * @author stefanboodt
 * 
 * @param<E> The type stored in this list.
 */
public class CircularLinkedList<E> extends AbstractList<E> {

	/**
	 * The Node containing the element at the tail.
	 */
	private Node<E> tail;
	
	/**
	 * The size in the list.
	 * There is chosen to do it this way rather than iterating the
	 * loop when the size method is requested.
	 */
	private int size;
	
	/**
	 * Creates a new CircularLinkedList.
	 */
	public CircularLinkedList() {
		super();
		tail = null;
		size = 0;
	}
	
	/**
	 * Creates a new CircularLinkedList with the given Collection as
	 * default values. It starts the List and adds all values in the
	 * Collection.
	 * @see Collection
	 * @param startUp The default values.
	 */
	public CircularLinkedList(Collection<E> startUp) {
		this();
		addAll(startUp);
	}
	
	/**
	 * Creates a new CircularLinkedList with the given Iterable as
	 * default values. It starts the List and adds all values in the
	 * Iterable.
	 * @see Iterable
	 * @param startUp The default values.
	 */
	public CircularLinkedList(Iterable<E> startUp) {
		this();
		for (E el: startUp) {
			add(el);
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new CircularLinkedListIterator<E>(tail);
	}

	@Override
	public boolean remove(Object o) {
		int size = size();
		int index = indexOf(o);
		if (index != -1) {
			this.remove(index);
		}
		return size != size();
	}

	@Override
	public void clear() {
		// Clearing all of the links between nodes is "unnecessary", but:
        // - helps a generational GC if the discarded nodes inhabit
        //   more than one generation
        // - is sure to free memory even if there is a reachable Iterator
		Node<E> finger = tail;
		while (size > 0) {
			Node<E> next = finger.getNext();
			finger.setElement(null);
			finger.setNext(null);
			finger = next;
			size--;
		}
		tail = null;
		if (size != 0) {
			throw new RuntimeException("The size is not correct");
		}
	}

	@Override
	public E get(int index) {
		return node(index).getElement();
	}

	@Override
	public E set(int index, E element) {
		Node<E> node = node(index);
		E e = node.getElement();
		node.setElement(element);
		return e;
	}

	@Override
	public void add(int index, E element) {
		
		size++;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<E> newList() {
		return new CircularLinkedList<E>();
	}
	
	@Override
	protected boolean isElementIndex(int index) {
		return tail != null && index >= 0;
	}
	
	/**
	 * Returns the node at the given index.
	 * @param index The index of the requested node.
	 * @return The node at the given index.
	 */
	protected Node<E> node(int index) {
		checkElementIndex(index);
		Node<E> finger = tail;
		for (int left = index; left > 0; left--) {
			finger = finger.getNext();
		}
		return finger;
	}
	
	/**
	 * The Iterator of the given class.
	 * It walks over all elements in the list.
	 * 
	 * @since 16-8-2014
	 * @version 16-8-2014
	 * 
	 * @see Iterator
	 * @see CircularLinkedList
	 * 
	 * @author stefanboodt
	 *
	 * @param <E> The type stored in the linked list.
	 */
	protected static class CircularLinkedListIterator<E> implements
			Iterator<E> {
		
		/**
		 * Remembers the tail.
		 */
		private Node<E> tail;
		
		/**
		 * To remember the finger.
		 */
		private Node<E> finger;
		
		/**
		 * Creates an Iterator for a circular list.
		 * @param start The starting point.
		 */
		public CircularLinkedListIterator(Node<E> start) {
			tail = start;
			finger = start;
		}

		@Override
		public boolean hasNext() {
			return finger != null && finger.next != tail;
		}

		@Override
		public E next() {
			E element = finger.getElement();
			finger = finger.next;
			return element;
		}
		
	}
	
	/**
	 * The Storing unit in a LinkedList.
	 * 
	 * @since 16-8-2014
	 * @version 16-8-2014
	 * 
	 * @see LinkedList
	 * @see CircularLinkedList
	 * 
	 * @author stefanboodt
	 *
	 * @param <E> The type stored in the node.
	 */
	protected static class Node<E> {
		
		/**
		 * The next node.
		 */
		private Node<E> next;
		
		/**
		 * The value in this Node.
		 */
		private E element;
		
		/**
		 * Creates a Node with the given element and next node.
		 * @param el The next Element.
		 * @param next The next Node.
		 */
		public Node(E el, Node<E> next) {
			setElement(el);
			setNext(next);
		}
		
		/**
		 * Sets the element to the given value.
		 * @param elem The new value of this node.
		 */
		public void setElement(E elem) {
			element = elem;
		}
		
		/**
		 * Gets the stored value.
		 * @return The value that is stored in this element.
		 */
		public E getElement() {
			return element;
		}
		
		/**
		 * Sets the next node of this node.
		 * @param next The new next node.
		 */
		public void setNext(Node<E> next) {
			this.next = next;
		}
		
		/**
		 * Gets the node next to this one.
		 * @return The next node.
		 */
		public Node<E> getNext() {
			return next;
		}
		
		@Override
		public String toString() {
			return "Node(element = " + element + ")"; 
		}
		
		/**
		 * Checks if two Objects are equal. They are considered equal
		 * iff they are both Nodes and their elements are equal.
		 * @param other The Object to compare with.
		 * @return true if the Objects are equal.
		 */
		@Override
		public boolean equals(Object other) {
			if (other instanceof Node) {
				Node<?> that = (Node<?>) other;
				return this.element.equals(that.element);
			}
			return false;
		}
		
		/**
		 * Creates a hash code by adding 4 to the hash code of it's
		 * element.
		 */
		@Override
		public int hashCode() {
			return 4 + element.hashCode();
		}
	}
}
