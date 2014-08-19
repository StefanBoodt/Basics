package sound;

/**
 * The SoundFilter interface. The sound filter class applies
 * techniques to manipulate sounds to the sounds. Since buffering may
 * be applied by the filters, you should create a new Soundfilter for
 * every sound played. They can be reused however by calling the
 * reset method after they are finished playing.
 * 
 * @since 10-8-2014
 * @version 10-8-2014
 * 
 * @see Sound
 * @see SoundManager
 * 
 * @author stefanboodt
 *
 */
public interface SoundFilter {

	/**
	 * Filters the samples. The samples should be 16 bit, signed,
	 * little-endian format.
	 * @param samples The array to filter.
	 */
	public void filter(byte[] samples);
	
	/**
	 * Filters the samples. The samples should be 16 bit, signed,
	 * little-endian format. It starts on offset and then proceeds length.
	 * @param samples The array to filter.
	 * @param offset The starting point.
	 * @param length The length to continue after offset.
	 */
	public void filter(byte[] samples, int offset, int length);
	
	/**
	 * Gets the remaining size, in bytes, that this filter plays sounds
	 * after the sound is finished. An example of other implementation
	 * would be an echo that plays longer than the original.
	 * @return 0, by default.
	 */
	public default int getRemainingSize() {
		return 0;
	}
	
	/**
	 * Resets the filter so it can be used again.
	 */
	public void reset();
	
	/**
	 * Convenience method for getting a 16-bit sample from a byte array.
	 * The samples should be 16 bit, signed, little-endian format.
	 * @param buffer The byte array of the sound.
	 * @param position The position to start on.
	 * @return A short at the given location.
	 */
	public static short getSample(byte[] buffer, int position) {
		return (short) ((buffer[position + 1] & 0xff << 8) |
				(buffer[position] & 0xff));
	}
	
	/**
	 * Convenience method for setting a 16-bit sample from a byte array.
	 * The samples should be 16 bit, signed, little-endian format.
	 * @param buffer The byte array of the sound.
	 * @param position The position to start on.
	 * @param sample The new short at the given location.
	 */
	public static void setSample(byte[] buffer, int position, short sample) {
		buffer[position] = (byte) (sample & 0xff);
		buffer[position + 1] = (byte) ((sample >> 8) & 0xff);
	}
}
