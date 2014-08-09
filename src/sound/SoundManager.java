package sound;

import javax.sound.sampled.*;

import concurrency.ThreadPool;

/**
 * The sound manager class manages sounds. It's methods make it easy to
 * manipulate sounds. It extends a ThreadPool so you can play many
 * sounds at the same time. It also limits the number of sounds being
 * played.
 * 
 * <p>
 * Possible ideas to extend this class:
 * 	<ul>
 * 		<li> add a setMasterVolume() method which uses Controls to
 * set the volume for each line. </li>
 * 		<li> don't play a sound if more than, say, 700ms have passed
 * since the request to play.</li>
 * 	</ul>
 * </p>
 * 
 * @since 9-8-2014
 * @version 9-8-2014
 * 
 * @see AudioFormat
 * @see ThreadPool
 * 
 * @author stefanboodt
 *
 */
public class SoundManager extends ThreadPool {

	/**
	 * 
	 */
	private AudioFormat playbackFormat;
	
	private ThreadLocal localLine;
	
	private ThreadLocal localBuffer;
	
	/**
	 * Keeps track of the paused state of the soundmanager.
	 */
	private boolean paused;
	
	/**
	 * Creates a soundmanager with the given format.
	 * This is equivalent to {@link #SoundManager(AudioFormat, int)} with
	 * the int as {@link #getMaximumSimultaniousSounds(AudioFormat)}
	 * @param format The format to use.
	 * @see #getMaximumSimultaniousSounds(AudioFormat)
	 */
	public SoundManager(AudioFormat format) {
		this(format, getMaximumSimultaniousSounds(format));
	}
	
	/**
	 * Creates a new SoundManager with the given format and maximum
	 * number of sounds.
	 * @param format The format for the soundmanager.
	 * @param maxSounds The amount of sounds that is allowed
	 * to play simultanious.
	 */
	public SoundManager(AudioFormat format, int maxSounds) {
		super(maxSounds);
		playbackFormat = format;
		localLine = new ThreadLocal();
		localBuffer = new ThreadLocal();
		
	}
	
	/**
	 * Gets the maximum amount of sounds of the given format the player
	 * can play, with the default mixer.
	 * @param format The format to check.
	 * @return The maximum amount of sounds playable.
	 */
	public static int getMaximumSimultaniousSounds(AudioFormat format) {
		DataLine.Info lineinfo = new DataLine.Info(SourceDataLine.class,
				format);
		Mixer mixer = AudioSystem.getMixer(null);
		return mixer.getMaxLines(lineinfo);
	}
}
