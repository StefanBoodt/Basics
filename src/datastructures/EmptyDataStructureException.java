package datastructures;

/**
 * Exception thrown when a data structure is empty.
 * 
 * @since 16-8-2014
 * @version 16-8-2014
 * 
 * @see RuntimeException
 * 
 * @author stefanboodt
 *
 */
public class EmptyDataStructureException extends RuntimeException {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 172195693195663135L;
	
	/**
	 * Creates a new Exception
	 */
	public EmptyDataStructureException() {
		super();
	}

	/**
	 * Creates a new Exception with the given message.
	 * @param message The message given.
	 */
	public EmptyDataStructureException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmptyDataStructureException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public EmptyDataStructureException(String message, Throwable cause) {
		super(message, cause);
	}

	
	public EmptyDataStructureException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
