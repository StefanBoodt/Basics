package datastructures;

import java.util.*;

/**
 * The circular Linked list is a list that is singly linked and
 * circular. It is known to be the preffered list when implementing a
 * Stack.
 * 
 * @since 16-8-2014
 * @version 19-8-2014
 * 
 * @see AbstractList
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
	 * Creates a new CircularLinkedList with the given Collection as
	 * default values. It starts the List and adds all values in the
	 * Collection.
	 * @see Collection
	 * @param startup The default values.
	 * @since 18-8-2014
	 */
	@SafeVarargs
	public CircularLinkedList(E ... startup) {
		this();
		for (E el: startup) {
			add(el);
		}
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
		checkIndex(index);
		if (index == 0) {
			addFirst(element);
		}
		else {
			Node<E> prev = node(index - 1);
			prev.setNext(new Node<E>(element, prev.getNext()));
			size++;
		}
	}
	
	/**
	 * Adds the element to the beginning of the list.
	 * @param element The element to be added.
	 */
	public void addLast(E element) {
		if (isEmpty()) {
			tail = new Node<E>(element, null);
			tail.setNext(tail);
		}
		else {
			Node<E> node = new Node<E>(element, tail.getNext());
			tail.setNext(node);
			tail = node;
		}
		size++;
	}
	
	@Override
	public boolean add(E element) {
		int oldSize = size();
		addLast(element);
		return oldSize != size;
	}
	
	/**
	 * Adds the element to the end of the list.
	 * @param element The element to be added.
	 * @see #add(Object)
	 */
	public void addFirst(E element) {
		if (isEmpty()) {
			addLast(element);
		}
		else {
			Node<E> next = tail.getNext();
			tail.setNext(new Node<E>(element, next));
			size++;
		}
	}
	
	/**
	 * Removes the first element of the list.
	 * @return the element at the start of the list.
	 */
	public E removeFirst() {
		if (isEmpty()) {
			throw new EmptyDataStructureException();
		}
		final Node<E> first = tail.getNext();
		final E el = first.getElement();
		if (size == 1) {
			tail = null;
		}
		else {
			tail.setNext(first.getNext());
		}
		size--;
		return el;
	}
	
	/**
	 * Removes the last element of the list.
	 * @return the element at the end of the list.
	 */
	public E removeLast() {
		if (isEmpty()) {
			throw new EmptyDataStructureException();
		}
		if (size() == 1) {
			return removeFirst();
		}
		node(size).setNext(tail.getNext());
		E el = tail.getElement();
		tail = tail.getNext();
		size--;
		return el;
	}

	@Override
	public E remove(int index) {
		if (isEmpty()) {
			throw new EmptyDataStructureException();
		}
		checkIndex(index);
		if (index == 0) {
			return removeFirst();
		}
		else if (index > 0) {
			Node<E> prev = node(index - 1);
			Node<E> next = node(index + 1);
			E element = node(index).getElement();
			prev.setNext(next);
			size--;
			return element;
		}
		return null;
	}
	
	@Override
	public AbstractList<E> newList() {
		return new CircularLinkedList<E>();
	}
	
	@Override
	protected boolean isIndex(int index) {
		return tail != null && index >= 0;
	}
	
	/**
	 * Returns the node at the given index.
	 * @param index The index of the requested node.
	 * @return The node at the given index.
	 */
	protected Node<E> node(int index) {
		checkIndex(index);
		Node<E> finger = tail;
		for (int left = index; left >= 0; left--) {
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
		private Node<E> first;
		
		/**
		 * To remember the finger.
		 */
		private Node<E> finger;
		
		/**
		 * Remembers whether or not this is the first round.
		 */
		private boolean starting;
		
		/**
		 * Creates an Iterator for a circular list.
		 * @param tail The last one of the nodes in the circular list.
		 */
		public CircularLinkedListIterator(Node<E> tail) {
			if (tail != null) {
				first = tail.getNext();
			}
			reset();
		}
		
		/**
		 * Resets the Iterator so it can start again.
		 */
		public void reset() {
			finger = first;
			starting = true;
		}

		@Override
		public boolean hasNext() {
			return finger != null && (finger != first || starting);
		}

		@Override
		public E next() {
			starting = false;
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
