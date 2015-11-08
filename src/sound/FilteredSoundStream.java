package sound;

import java.io.*;

/**
 * This FilterInputStream applies a filter to a Sound. It can then play
 * let the sound be played with the filter applied.
 * 
 * @since 10-8-2014
 * @version 10-8-2014
 * 
 * @see SoundFilter
 * @see FilterInputStream
 * 
 * @author stefanboodt
 *
 */
public class FilteredSoundStream extends FilterInputStream {

	/**
	 * The number used to notify that the size is unknown.
	 */
	private static final int REMAINING_SIZE_UNKNOWN = -100;
	
	/**
	 * Keeps track of the remaining size of the stream.
	 */
	private int remainingSize;
	
	/**
	 * The filter used.
	 */
	private SoundFilter filter;
	
	/**
	 * Creates an InputStream with the given Filter and InputStream.
	 * @param in The InputStream used behind the scenes.
	 * @param filter The filter to be used.
	 */
	public FilteredSoundStream(InputStream in, SoundFilter filter) {
		super(in);
		this.filter = filter;
		remainingSize = REMAINING_SIZE_UNKNOWN;
	}
	
	/**
	 * Applies the filter and calls super.
	 * Reads up to len bytes of data from this input stream into an array of bytes.
	 * If len is not zero, the method blocks until some input is available;
	 * otherwise, no bytes are read and 0 is returned.
	 * @see FilterInputStream#read(byte[], int, int)
	 */
	@Override
	public int read(byte[] samples, int offset, int length)
			throws IOException {
		int bytesRead = super.read(samples, offset, length);
		if (bytesRead > 0) {
			filter.filter(samples, offset, length);
			return bytesRead;
		}
		
		if (remainingSize == REMAINING_SIZE_UNKNOWN) {
			remainingSize = filter.getRemainingSize();
			// Round to the nearest multiple of 4.
			remainingSize = remainingSize / 4 * 4;
		}
		if (remainingSize > 0) {
			length = Math.min(length, remainingSize);
			
			//Clear the buffer
			for (int i = offset; i < offset + length; i++) {
				samples[i] = 0;
			}
			
			// filter the rest
			filter.filter(samples, offset, length);
			remainingSize -= length;
			return length;
		}
		// end of the Stream.
		return -1;
	}

}
