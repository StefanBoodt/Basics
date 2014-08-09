package sound;

import java.io.IOException;

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
	 * The audioformat used by this object.
	 */
	private AudioFormat playbackFormat;
	
	/**
	 * The local line parameter.
	 */
	private ThreadLocal<?> localLine;
	
	/**
	 * The local buffer parameter.
	 */
	private ThreadLocal<?> localBuffer;
	
	/**
	 * The lock for pauses.
	 */
	private Object pausedLock;
	
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
		localLine = new ThreadLocal<Object>();
		localBuffer = new ThreadLocal<Object>();
		
		synchronized(this) {
			notifyAll();
		}
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
	
	/**
	 * Does the pre close clean up.
	 */
	protected void cleanUp() {
		setPaused(true);
		
		Mixer mixer = AudioSystem.getMixer(null);
		if (mixer.isOpen()) {
			mixer.close();
		}
	}
	
	/**
	 * Closes the SoundManager and cleans all executing sounds.
	 */
	@Override
	public void close() throws IOException {
		cleanUp();
		super.close();
	}
	
	/**
	 * Sets the paused to the given value.
	 * @param paused the new paused value. true if you want to pause,
	 * false if you want to unpause.
	 */
	protected synchronized void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	/**
	 * 
	 * @param filename
	 * @return
	 */
	public Sound getSound(String filename) {
		return null;
	}
	
	/**
	 * Does not make them accept any new sounds.
	 */
	@Override
	public void join() {
		cleanUp();
		super.join();
	}

	/**
	 * Checks if the sound manager is paused.
	 * @return the paused state, true for paused.
	 */
	public synchronized final boolean isPaused() {
		return paused;
	}
	
	public static class Sound extends javax.media.j3d.Sound {
		
		private byte[] samples;
		
		public Sound(byte[] samples) {
			this.samples = samples;
		}
	}
}
