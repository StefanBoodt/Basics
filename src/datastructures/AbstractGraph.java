package datastructures;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import datastructures.Graph.Edge;
import datastructures.Graph.Vertex;

/**
 * An Abstract class implementation of the Graph interface. This class is
 * superclass to quite some Graph classes and implements some default
 * methods.
 * 
 * @see Graph
 * @see Graph.Vertex
 * @see Graph.Edge
 * 
 * @author stefanboodt
 *
 * @param <E> The type of Object to store in the Vertices of the Graph.
 * @param <O> The type of Object to store in the Edges of the Graph
 */
public abstract class AbstractGraph<E,O> implements Graph<E, O> {

	/**
	 * Serial number.
	 */
	private static final long serialVersionUID = -1669300315208782656L;

	/**
	 * Set of all vertices.
	 */
	private Set<Vertex<E>> vertices;
	
	/**
	 * The edges of the graph.
	 */
	private Set<Edge<O>> edges;
	
	/**
	 * Creates a new AbstractGraph.
	 */
	public AbstractGraph() {
		vertices = new HashSet<Vertex<E>>();
		edges = new HashSet<Edge<O>>();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return size() != 0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		final int oldsize = size();
		insertVertex(e);
		return size() != oldsize;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		vertices.clear();
		edges.clear();
	}

	@Override
	public Collection<datastructures.Graph.Vertex<E>> vertices() {
		return vertices;
	}

	@Override
	public Collection<datastructures.Graph.Edge<O>> edges() {
		return edges;
	}

	@Override
	public datastructures.Graph.Vertex<E> opposite(
			datastructures.Graph.Vertex<E> v, datastructures.Graph.Edge<O> e)
			throws datastructures.Graph.UnconnectedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean areAdjecent(datastructures.Graph.Vertex<E> v,
			datastructures.Graph.Vertex<E> w) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E replace(datastructures.Graph.Vertex<E> v, E el) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public O replace(datastructures.Graph.Edge<O> e, O x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public datastructures.Graph.Vertex<E> insertVertex(E el) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public datastructures.Graph.Vertex<E> insertVertex(
			datastructures.Graph.Vertex<E> v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public datastructures.Graph.Edge<O> insertEdge(
			datastructures.Graph.Vertex<E> source, O element,
			datastructures.Graph.Vertex<E> destination) {
		return insertEdge(new UndirectedEdge(source, destination, element));
	}

	@Override
	public datastructures.Graph.Edge<O> insertEdge(
			datastructures.Graph.Edge<O> edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeVertex(datastructures.Graph.Vertex<E> vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public O removeEdge(datastructures.Graph.Edge<O> edge) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class UndirectedEdge<O> implements Edge<O> {

		/**
		 * Serial number.
		 */
		private static final long serialVersionUID = 3358429037474718610L;

		/**
		 * The source of the graph.
		 */
		private Vertex<E> source;
		
		/**
		 * The destination of the edge.
		 */
		private Vertex<E> destination;
		
		/**
		 * Vertex element.
		 */
		private O element;
		
		/**
		 * Creates a new Undirected edge with the given source,
		 * destination, element.
		 * @param source The source of the edge.
		 * @param destination The destination of the edge.
		 * @param element The element in the edge.
		 */
		public UndirectedEdge(Vertex<E> source, Vertex<E> destination,
				O element) {
			super();
			setSource(source);
			setDestination(destination);
			setElement(element);
		}
		
		@SuppressWarnings({ "unchecked", "hiding" })
		@Override
		public <E> Vertex<E> getSource() {
			return (datastructures.Graph.Vertex<E>) source;
		}

		@Override
		public <E> datastructures.Graph.Vertex<E> getDestination() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <E> E setSource(datastructures.Graph.Vertex<E> src) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <E> E setDestination(datastructures.Graph.Vertex<E> dest) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public O getElement() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setElement(O newvalue) {
			// TODO Auto-generated method stub
			
		}
	}

}
