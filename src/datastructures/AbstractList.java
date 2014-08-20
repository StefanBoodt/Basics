package datastructures;

import interfaces.ResetableIterator;

import java.util.*;

/**
 * This class has a default implementation of some methods in the List
 * interface.
 * 
 * @since 10-8-2014
 * @version 20-8-2014
 * 
 * @see List
 * @see Cloneable
 * @see Iterable
 * @see ResetableIterator
 * 
 * @author stefanboodt
 *
 * @param <E> The type stored in the list.
 */
public abstract class AbstractList<E> implements List<E>, Iterable<E>,
	Cloneable {

	/**
	 * Constructor to call in subClasses.
	 * Current version does nothing.
	 */
	public AbstractList() {
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size()];
		for (int i = 0; i < size(); i++) {
			array[i] = get(i);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		for (int i = 0; i < size(); i++) {
			a[i] = (T) get(i);
		}
		return a;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object elem: c) {
			if (!contains(elem)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds all the Objects in c to the end of the list.
	 * @param c The collection of Objects to be added.
	 * @return true if there was something added.
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		int oldSize = size();
		for (E el: c) {
			add(el);
		}
		return oldSize != size();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		int oldSize = size();
		for (E el: c) {
			add(index, el);
			index++;
		}
		return oldSize != size();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		int oldSize = size();
		for (Object el: c) {
			removeAll(el);
		}
		return oldSize != size();
	}
	
	/**
	 * Removes all instances of Object o from the list.
	 * @param o The Object to be removed.
	 */
	public void removeAll(Object o) {
		while (this.contains(o)) {
			this.remove(o);
		}
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i< size(); i++) {
			if (get(i).equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		for (int i = size() - 1; i >= 0; i--) {
			if (get(i).equals(o)) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		int oldSize = size();
		List<E> removed = new ArrayList<E>();
		for (int i = 0; i < size(); i++) {
			E elem = get(i);
			if (!c.contains(elem)) {
				removed.add(elem);
			}
		}
		for (E elem: removed) {
			this.removeAll(elem);
		}
		return oldSize != size();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (other instanceof List) {
			List<?> that = (List<?>) other;
			if (this.size() != that.size()) {
				return false;
			}
			for (int i = 0; i < size(); i++) {
				if (!(this.get(i).equals(that.get(i)))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 1;
	    for (E e : this) {
	    	hashCode = 31 * hashCode + e.hashCode(); 
	    }
	    return hashCode;
	}
	
	/**
	 * Checks if the two Lists are shallow equal.
	 * They are considered equal iff they have the same size and
	 * have the same elements in both lists.
	 * @param other The list to compare with.
	 * @return true iff they are found equal.
	 */
	public boolean shallowEquals(List<E> other) {
		if (other == this) {
			return true;
		}
		List<?> that = (List<?>) other;
		if (this.size() != that.size()) {
			return false;
		}
		return this.containsAll(that) && that.containsAll(this);
	}
	
	@Override
	public ListIterator<E> listIterator() {
		 throw new UnsupportedOperationException();
	}
	
	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(E e) {
		int size = size();
		add(size, e);
		return this.size() != size;
	}

	@Override
	public AbstractList<E> subList(int fromIndex, int toIndex) {
		AbstractList<E> sub = this.newList();
		int index = fromIndex;
		while (index < toIndex) {
			sub.add(this.get(index));
			index++;
		}
		return sub;
	}
	
	/**
	 * Unifies the two lists in the returned Set.
	 * @param other The list to unify with
	 * @return The set containing only elements that are in either list.
	 */
	public Set<E> unify(List<E> other) {
		Set<E> uni = new HashSet<E>();
		uni.addAll(this);
		uni.addAll(other);
		return uni;
	}
	
	/**
	 * Intersects this list with the given value.
	 * @param other The list to compare with.
	 * @return The intersection of the two lists.
	 */
	public Set<E> intersect(List<E> other) {
		Set<E> intersect = new HashSet<E>();
		intersect.addAll(this);
		intersect.retainAll(other);
		return intersect;
	}
	
	/**
	 * Returns a set of E that appear in the first list but not in the
	 * second.
	 * @param other The List to compare with.
	 * @return A Set of elements that appear in only one list.
	 */
	public Set<E> difference(List<E> other) {
		Set<E> diff = new HashSet<E>();
		for (E el: this) {
			if (!other.contains(el)) {
				diff.add(el);
			}
		}
		return diff;
	}
	
	/**
	 * Returns a Set containing the Symmetric difference between the
	 * two lists.
	 * @param other The List to compare with.
	 * @return A Set of elements that appear in only one list.
	 */
	public Set<E> symmetricDifference(AbstractList<E> other) {
		Set<E> diff = this.difference(other);
		diff.addAll(other.difference(this));
		return diff;
	}
	
	/**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    protected String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size();
    }
    
    /**
     * Tells if the index is valid.
     */
    protected boolean isIndex(int index) {
        return index >= 0 && index < size();
    }

    /**
     * Checks if the index is correct.
     * @param index The index to be checked.
     */
    protected void checkIndex(int index) {
        if (!isIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public AbstractList<E> clone() throws CloneNotSupportedException {
		return (AbstractList<E>) super.clone();
	}
	
	/**
	 * Creates a String representation of the AbstractList.
	 */
	public String toString() {
		String res = "";
		for (E el: this) {
			res += ", " + el.toString();
		}
		if (res.length() >= 2) {
			res = res.substring(2);
		}
		return "[" + res + "]";
	}
	
	/**
	 * Creates a new version of the list and returns it.
	 * The difference with clear is that the new list is returned here
	 * and the list it is called upon is still intact.
	 * @see #clear()
	 * @return an empty instance of the List.
	 */
	public abstract AbstractList<E> newList();
	
	/**
	 * {@inheritDoc}
	 * Is now also Resetable.
	 * @since 20-8-2014
	 */
	@Override
	public abstract ResetableIterator<E> iterator();
}
