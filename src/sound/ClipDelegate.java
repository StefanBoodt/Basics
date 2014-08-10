package sound;

import javax.sound.sampled.*;

/**
 * A class with methods used to get a Clip Object to work even more
 * easily than it already does. It requests a Clip using the most
 * common method, the way through AudioSystem, and then forwards the
 * default methods to the given Clip.
 * 
 * <p>
 * For use of large sounds or when you want to add filters and effects,
 * use the SoundManager class. That class works with the SourceDataLine
 * and can therefore handle that.
 * </p>
 * 
 * @since 10-8-2014
 * @version 10-8-2014
 * 
 * @see Clip
 * @see AudioSystem
 * @see SoundManager.
 * 
 * @author stefanboodt
 *
 */
public class ClipDelegate {

	private Clip clip;
	
	public ClipDelegate() {
		// TODO Auto-generated constructor stub
	}

}
