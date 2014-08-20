package interfaces;

/**
 * Makes the things resetable. That is being able to be reset so they
 * can be used again from the start. Classes that implement Iterator
 * should consider being resetable. Classes implementing this class as
 * well as Closeable should consider whether they want to close and reopen
 * or they want to stay open. Sometimes this is not a choice however.
 * 
 * @since 19-8-2014
 * @version 20-8-2014
 * 
 * @see java.util.Iterator
 * @see java.io.Closeable
 * 
 * @author stefanboodt
 *
 */
public interface Resetable {
	
	/**
	 * Resets the resetable so it can be used again from scratch.
	 * That is the class is reset so that it is entirely recreated
	 * with the same variables as the first time, while maintaining the
	 * old references. It is practical for when the original parameters
	 * are no longer in existance, but you like to use the old resetable.
	 */
	public void reset();
}
