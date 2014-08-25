package datastructures;

import java.io.*;
import java.util.*;

/**
 * A Graph is a collection of points and lines. Different names exist for
 * these points and lines but here Vertex and Edge are used. Graphs can
 * either be directed or indirected, or can be a combination of the two.
 * A Graph is actually a way to represent connections and relationships
 * between the Objects it stores. A well known usage of Graphs is for
 * navigation (, by using Dijkstra's alrgoritm).
 * 
 * @since 25-8-2014
 * @version 25-8-2014
 * 
 * @see Collection
 * @see Serializable
 * 
 * @author stefanboodt
 *
 * @param <E> The type of Object to store in the Vertices of the Graph.
 * @param <O> The type of Object to store in the Edges of the Graph
 */
public interface Graph<E, O> extends Collection<E>, Serializable {
	
	/**
	 * Returns a collection of all the vertices in this Graph.
	 * @return The vertices present in this Graph.
	 */
	public Collection<Vertex<E>> vertices();
	
	/**
	 * Returns a collection of all the edges in the Graph.
	 * @return The Edges in the Graph.
	 */
	public Collection<Edge<O>> edges();
	
	/**
	 * Return a collection of incident edges. An Edge is called incident
	 * to a Vertex iff the Vertex is one of it's ending points. That can
	 * be either incoming or outgoing, but it can also be neither, for
	 * instance in an undirectedGraph. 
	 * @param v The Vertex to get the incident edges of.
	 * @return A collection of the edges that have the given vertex as
	 * it's ending point.
	 */
	public default Collection<Edge<O>> incidentEdges(Vertex<E> v) {
		return v.getEdges();
	}
	
	/**
	 * Gets the end of the Edge that is not the given Vertex but is on
	 * the other side. 
	 * @param v The vertex to move from.
	 * @param e The Edge to move over.
	 * @return The vertex at the other side of the edge.
	 * @throws UnconnectedException When v and e are not incident.
	 */
	public Vertex<E> opposite(Vertex<E> v, Edge<O> e)
			throws UnconnectedException;
	
	/**
	 * Seeks the edges end points.
	 * @param e The edge.
	 * @see Edge#getEndPoints()
	 * @return an array containing the vertices at the end of the edge.
	 */
	public default Vertex<E>[] endVertices(Edge<O> e) {
		return e.getEndPoints();
	}
	
	/**
	 * Checks if the given vertices are adjecent. Two vertices are
	 * adjecent iff the vertices are conected by one single edge.
	 * That is there is an Edge e so that the endpoints are v and w.
	 * @param v The first vertex.
	 * @param w The second vertex.
	 * @return true if they are adjecent and false otherwise.
	 */
	public boolean areAdjecent(Vertex<E> v, Vertex<E> w);
	
	/**
	 * Replaces v by a new vertex with el as it's element. All the
	 * edges the vertex had should transfer to the new Vertex.
	 * @param v The vertex to be replaced.
	 * @param el The element to store in the new Vertex.
	 * @return The element at the old vertex, or null if no such vertex
	 * was found. 
	 */
	public E replace(Vertex<E> v, E el);
	
	/**
	 * Replaces the edge e by a new edge with the given Object o.
	 * @param e The edge to be replaced.
	 * @param x The value for the new edge.
	 * @return The Object of the old edge, or null if there was no such
	 * edge.
	 */
	public O replace(Edge<O> e, O x);
	
	/**
	 * Inserts a new vertex into the Graph. It is unconnected to any
	 * other vertex in the Graph. The vertex has el as it's element.
	 * @param el The element for the new Vertex.
	 * @return The inserted vertex.
	 */
	public Vertex<E> insertVertex(E el);
	
	/**
	 * Inserts the vertex, v into the Graph. It is unconnected to any
	 * other vertex in the Graph.
	 * @param v The vertex to insert.
	 * @return The vertex inserted.
	 */
	public Vertex<E> insertVertex(Vertex<E> v);
	
	/**
	 * Inserts an Edge with the given source and given destination.
	 * @param source The source of the Edge.
	 * @param element The element in the Edge.
	 * @param destination The destination of the Edge.
	 * @return The edge inserted.
	 */
	public Edge<O> insertEdge(Vertex<E> source, O element,
			Vertex<E> destination);
	
	/**
	 * Inserts an Edge with the given source and given destination.
	 * @param edge The edge to insert.
	 * @return The edge inserted.
	 */
	public Edge<O> insertEdge(Edge<O> edge);
	
	/**
	 * Removes the vertex vertex from the graph.
	 * @param vertex The vertex to remove.
	 * @return the element of the removed vertex. Null is returned if
	 * no such vertex exists.
	 */
	public E removeVertex(Vertex<E> vertex);
	
	/**
	 * Removes the edge from the vertex.
	 * @param edge The edge to remove.
	 * @return The element of the edge, or null if no edge is found.
	 */
	public O removeEdge(Edge<O> edge);
	
	/**
	 * The Interface for the vertices. It's methods make it usable for a
	 * Graph. It's Methods are made to access the elements and traverse
	 * the Graph. Classes implementing this Interface can then be used
	 * vertices in the Graph allowing customization of the Vertices.
	 * Some Documentation names Vertices as Nodes.
	 * 
	 * @since 25-8-2014
	 * @version 25-8-2014
	 * 
	 * @see Graph
	 * @see Graph#vertices()
	 * 
	 * @author stefanboodt
	 *
	 * @param <E> The type used to store in the vertex.
	 */
	public static interface Vertex<E> extends Serializable {
		
		/**
		 * Sets the element of this vertex to the given value.
		 * @param element The new value of the vertex.
		 */
		public void setElement(E element);
		
		/**
		 * Gets the Element stored in this vertex.
		 * @return The element that was stored in this edge.
		 */
		public E getElement();
		
		/**
		 * Adds the given edge to the Vertex.
		 * @param edge The edge to add.
		 * @return The Edge you just added, or null on failure.
		 */
		public <O> Edge<O> addEdge(Edge<O> edge);
		
		/**
		 * Returns the incident edges of this vertex.
		 * @return The edges that are incident to this vertex.
		 */
		public <O> Collection<Edge<O>> getEdges();
		
		/**
		 * Counts the amount of incident edges this vertex has.
		 * Please note that this default behavior simply returns
		 * the size of the collection returned by {@link Vertex#getEdges()},
		 * and as such you can not return a collection for which you
		 * need to specify the size by using this method.
		 * @return The amount of incident edges this vertex has.
		 */
		public default int getAmountOfEdges() {
			return getEdges().size();
		}
		
		/**
		 * {@inheritDoc}
		 * <p>
		 * Returns the hashCode of this Vertex. The hash code is
		 * calculated by adding the amount of incidentEdges to
		 * {@link #getEdges()}{@link Edge#hashCode()}. All subclasses could also use
		 * {@link Vertex#hashCode(Vertex)} to calculate the hash code
		 * as this uses the wanted technique.
		 * </p>
		 * @return The hash code.
		 */
		@Override
		public int hashCode();
		
		/**
		 * Calculates the hashCode for the given vertex.
		 * @param vertex The vertex to calculate the hash code from.
		 * @return The hash code of the given vertex.
		 */
		public static <E> int hashCode(Vertex<E> vertex) {
			return vertex.getElement().hashCode()
					+ vertex.getAmountOfEdges();
		}
		
		/**
		 * Checks if the vertex is connected in any way, either directly
		 * or indirectly, to the given vertex.
		 * @param v The vertex to check the connection to.
		 * @return true iff there is a path from this vertex to the
		 * given vertex.
		 */
		public boolean isConnected(Vertex<E> v);
		
		/**
		 * Checks if the Vertex is connected to any edge.
		 * @return true if this vertex is connected to an edge.
		 */
		public default boolean isConnected() {
			return getAmountOfEdges() != 0;
		}
	}
	
	/**
	 * The edge interface represents the connection in a Graph. An edge
	 * is also called arcs by some documents. Implementing classes could
	 * customize some behavior but the core methods should be the same or
	 * at least behave similarly, so the Graph classes can use them.
	 * 
	 * @since 25-8-2014
	 * @version 25-8-2014
	 * 
	 * @see Graph
	 * @see Graph#edges()
	 * 
	 * @author stefanboodt
	 *
	 * @param <O> The type stored in the Edge.
	 */
	public static interface Edge<O> extends Serializable {
		
		/**
		 * Returns the starting point of the edge.
		 * @return The source of the edge.
		 */
		public <E> Vertex<E> getSource();
		
		/**
		 * Gets the destination of this vertex.
		 * @return The end point of this edge.
		 */
		public <E> Vertex<E> getDestination();
		
		/**
		 * Returns the endpoints of this edge in an array.
		 * The first index is equal to {@link #getSource()} and the
		 * second index is equal to {@link #getDestination()}.
		 * @return An array of the endpoints in an array.
		 */
		public default <E> Vertex<E>[] getEndPoints() {
			@SuppressWarnings("unchecked")
			Vertex<E>[] endpoints = (Vertex<E>[]) new Vertex[2];
			endpoints[0] = getSource();
			endpoints[1] = getDestination();
			return endpoints;
		}
		
		/**
		 * Calculates the hashCode by the way proposed by {@link #hashCode()}.
		 * @param edge The edge to calculate the hash code of.
		 * @return The hash code.
		 */
		public static <O> int hashCode(Edge<O> edge) {
			int hash = 0;
			final int divider;
			if (edge.getElement() != null) {
				hash += 2 * edge.getElement().hashCode();
				divider = 4;
			}
			else {
				divider = 2;
			}
			hash += edge.getSource().hashCode();
			hash += edge.getDestination().hashCode();
			hash = hash / divider;
			return hash;
		}
		
		/**
		 * Calculates the hash code of the given edge.
		 * The hash code is calculated by 
		 * <p>
		 * if (edge.getElement() != null) { <br>
				hash += 2 * edge.getElement().hashCode();<br>
				divider = 4;<br>
			}<br>
			else {<br>
				divider = 2;<br>
			}<br>
			hash += edge.getSource().hashCode();<br>
			hash += edge.getDestination().hashCode();<br>
			hash = hash / divider;
			</p>
		 * @return The hash code.
		 */
		public int hashCode();
		
		/**
		 * Sets the starting point of the edge to the given value.
		 * @param src The new source.
		 * @return The new source.
		 */
		public <E> E setSource(Vertex<E> src);
		
		/**
		 * Sets the endpoint of the edge to the given value.
		 * @param dest The destination of the edge.
		 * @return The destination of the edge.
		 */
		public <E> E setDestination(Vertex<E> dest);
		
		/**
		 * Gets the element of this edge.
		 * @return the element or null if there is no element.
		 */
		public O getElement();
		
		/**
		 * Sets the element to the new value.
		 * @param newvalue The new value, or null for no value.
		 */
		public void setElement(O newvalue);
	}
	
	/**
	 * An exception thrown when an Edge and a Vertex are not connected
	 * but the program says they should be so.
	 * 
	 * @since 25-8-2014
	 * @version 25-8-2014
	 * 
	 * @author stefanboodt
	 *
	 */
	public static class UnconnectedException extends Exception {

		/**
		 * Serial number.
		 */
		private static final long serialVersionUID = 9101774236818999250L;
		
		/**
		 * Creates a new UnconnectedException.
		 */
		public UnconnectedException() {
			super();
		}
		
		/**
		 * Creates a new UnconnectedException.
		 * @param message The message to use as the 
		 */
		public UnconnectedException(String message) {
			super(message);
		}
	}
}
