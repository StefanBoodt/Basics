package sound;

/**
 * Gives a default implementation of the SoundFilter interface.
 * This class should be subclassed instead of implementing the interface,
 * but the interface can be safely implemented if neccessary.
 * 
 * @see Sound
 * @see SoundFilter
 * @see SoundManager
 * 
 * @author stefanboodt
 *
 */
public abstract class AbstractSoundFilter implements SoundFilter {

	/**
	 * Creates a new SoundFilter
	 */
	public AbstractSoundFilter() {
		super();
	}
	
	@Override
	public void filter(byte[] samples) {
		filter(samples, 0, samples.length);
	}
}
