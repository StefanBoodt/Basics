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
	 * The filter used.
	 */
	private SoundFilter filter;
	
	/**
	 * @param in
	 */
	public FilteredSoundStream(InputStream in, SoundFilter filter) {
		super(in);
		this.filter = filter;
	}

}
