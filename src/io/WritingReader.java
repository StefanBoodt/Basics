package io;

import java.io.*;

/**
 * A Reader that writes the read stuff to an OutPutStream.
 * 
 * @since 21-8-2014
 * @version 21-8-2014
 * 
 * @see KiloByteReader
 * @see InputStream
 * @see OutputStream
 * @see Writer
 * 
 * @author stefanboodt
 *
 */
public class WritingReader extends KiloByteReader 
		implements Flushable {
	
	/**
	 * The OutputStream to write to.
	 */
	private OutputStream output;
	
	/**
	 * Creates a WritingReader that reads the given InputStream and
	 * Writes to the given OutputStream.
	 * @param input The InputStream read.
	 * @param output The OutputStream used for writing.
	 */
	public WritingReader(InputStream input, OutputStream output) {
		super(input);
		this.output = output;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * The read Character is then written to the given OutputStream.
	 * </p>
	 */
	@Override
	public int read() throws IOException {
		int read = super.read();
		output.write(read);
		return read;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * The read Characters are then written to the given OutputStream.
	 * </p>
	 */
	@Override
	public int read(char[] cbuf) throws IOException {
		int len = super.read(cbuf);
		OutputStreamWriter writer = new OutputStreamWriter(output);
		writer.write(cbuf, 0, len);
		writer.flush();
		writer.close();
		return len;
	}
	
	/**
	 * {@inheritDoc}
	 * Then writes it to the given output stream.
	 */
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int length = super.read(cbuf, off, len);
		OutputStreamWriter writer = new OutputStreamWriter(output);
		writer.write(cbuf, 0, length);
		writer.flush();
		writer.close();
		return len;
	}
	
	/**
	 * {@inheritDoc}
	 * The read bytes are then written to the OutputStream and then
	 * returned. This method also flushes.
	 */
	@Override
	public byte[] readBytes() throws IOException {
		byte[] buf = super.readBytes();
		int length = this.getReadAmount();
		if (length != -1) {
			output.write(buf, 0, length);
			flush();
		}
		return buf;
	}
	
	/**
	 * Reads the Stream given in the constructor and writes it to the
	 * OutputStream given in the same constructor. This is the most
	 * useful method out of the entire class, for it reads the entire
	 * Stream and writes the entire Stream. It uses the {@link #readBytes()}
	 * method to read and write the bytes.
	 * 
	 * <p>
	 * Then it's superclass method {@link #isFinished()} is used to
	 * check if it is finished. After each {@link #readBytes()} call
	 * the {@link #flush()} method is called.
	 * </p>
	 * 
	 * @see #readBytes()
	 * @see #isFinished()
	 * @see #flush()
	 * @throws IOException If an IOException occurs.
	 */
	public void readStream() throws IOException {
		while (!isFinished()) {
			readBytes();
		}
	}

	@Override
	public void close() throws IOException {
		output.close();
		output = null;
		super.close();
	}

	@Override
	public void flush() throws IOException {
		output.flush();
	}
}
