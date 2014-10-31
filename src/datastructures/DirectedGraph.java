package datastructures;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This class represents a directed Graph. That is it is a graph in which
 * every vertex has a direction and can only be walked in this direction.
 * 
 * @since 30-10-2014
 * @version 30-10-2014
 * 
 * @author stefanboodt
 * 
 * @param <E> The type of Object to store in the Vertices of the Graph.
 * @param <O> The type of Object to store in the Edges of the Graph
 *
 */
public class DirectedGraph<E, O> extends AbstractGraph<E, O> {

	/**
	 * Serial number.
	 */
	private static final long serialVersionUID = 7674348446416400079L;
	
	/**
	 * Set of all vertices.
	 */
	private Set<Vertex<E>> vertices;
	
	/**
	 * The edges of the graph.
	 */
	private Set<Edge<O>> edges;

	/**
	 * Creates a new directed graph with no vertices or edges.
	 */
	public DirectedGraph() {
		super();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
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
		return oldsize != size();
	}

	@Override
	public boolean remove(Object o) {
		final int oldsize = size();
		if (o instanceof Vertex<?>) {
			Vertex<?> question = (Vertex<?>) o;
			//Vertex<E> vertex = new Vertex<E>(null);
			//question.getClass().isInstance(vertex);
		}
		return oldsize != size();
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
		E element = v.getElement();
		v.setElement(el);
		return element;
	}

	@Override
	public O replace(datastructures.Graph.Edge<O> e, O x) {
		O element = e.getElement();
		e.setElement(x);
		return element;
	}

	@Override
	public datastructures.Graph.Vertex<E> insertVertex(E el) {
		Vertex<E> vertex = new DefaultVertex<E>(el);
		vertices.add(vertex);
		return vertex;
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
		// TODO Auto-generated method stub
		return null;
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

	/**
	 * This class gives an implementation to the vertex class.
	 * 
	 * @see Vertex
	 * 
	 * @author stefanboodt
	 *
	 * @param <E> The type to store in the vertex.
	 */
	public class DefaultVertex<E> implements Vertex<E> {

		/**
		 * Serial number
		 */
		private static final long serialVersionUID = 777612620623440339L;

		/**
		 * The Element stored in the Vertex.
		 */
		private E element;
		
		/**
		 * The Edges linked to this vertex.
		 */
		private List<Edge<O>> edges;
		
		/**
		 * Creates a default vertex to use in a graph.
		 * @param element The element to use this vertex
		 */
		public DefaultVertex(E element) {
			setElement(element);
		}
		
		@Override
		public void setElement(E element) {
			this.element = element;
		}

		@Override
		public E getElement() {
			return element;
		}

		@Override
		public <O> datastructures.Graph.Edge<O> addEdge(
				datastructures.Graph.Edge<O> edge) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <O> Collection<datastructures.Graph.Edge<O>> getEdges() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isConnected(datastructures.Graph.Vertex<E> v) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
}
