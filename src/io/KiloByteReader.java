package io;

import java.io.*;

/**
 * A Reader that works a little different from the others.
 * 
 * @since 21-8-2014
 * @version 21-8-2014
 * 
 * @see Reader
 * 
 * @author stefanboodt
 *
 */
public class KiloByteReader extends Reader {

	/**
	 * The input stream to read.
	 */
	private InputStream input;
	
	/**
	 * Keeps track of the length read.
	 */
	private int length;
	
	/**
	 * Creates an KiloByteReader that reades an input stream.
	 * @param input The input stream to read.
	 */
	public KiloByteReader(InputStream input) {
		super();
		this.input = input;
		length = 0;
	}

	@Override
	public void close() throws IOException {
		input.close();
		input = null;
	}
	
	/**
	 * Reads one kilobyte of data.
	 * @return The bytes read.
	 * @throws IOException If an IOException occurs.
	 */
	public byte[] readBytes() throws IOException {
		byte[] buf = new byte[1024];
		length = input.read(buf);
		return buf;
	}
	
	/**
	 * {@inheritDoc}
	 * Delegates it to {@link InputStreamReader}.
	 * @see InputStreamReader#read(char[], int, int)
	 */
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		InputStreamReader reader = new InputStreamReader(input);
		int read = reader.read(cbuf, off, len);
		reader.close();
		return read;
	}
	
	/**
	 * Returns the amount of characters read 
	 * @return The read amount.
	 */
	public int getReadAmount() {
		return length;
	}
	
	/**
	 * Returns the InputStream used.
	 * @return The InputStream used by this Reader.
	 */
	public InputStream getInputStream() {
		return input;
	}
	
	/**
	 * Checks if the reader finished reading the stream.
	 * @return true if it did.
	 */
	public boolean isFinished() {
		return getReadAmount() == -1;
	}
}
