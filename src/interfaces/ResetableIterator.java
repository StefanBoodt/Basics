package interfaces;

import java.util.Iterator;

/**
 * Combines the usage of Iterators and Resetable. Classes implementing
 * this interface are known as ResetableIterators. They are known to
 * be used by iterators that can be reset, and therefor restarted.
 * 
 * @since 20-8-2014
 * @version 20-8-2014
 *  
 * @author stefanboodt
 *
 * @param <E> The type to iterate over.
 */
public interface ResetableIterator<E> extends Iterator<E>, Resetable {

}
