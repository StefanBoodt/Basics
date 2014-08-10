package sound;

import java.io.*;
import java.util.*;

import javax.sound.sampled.*;

import concurrency.ThreadPool;

/**
 * The sound manager class manages sounds. It's methods make it easy to
 * manipulate sounds. It extends a ThreadPool so you can play many
 * sounds at the same time. It also limits the number of sounds being
 * played.
 * 
 * <p>
 * This class makes little use of the Sound API. Instead it tries to work
 * around it to overcome the problem of the API. The Sound API uses a lot
 * of Objects related to the line Interface. Multiple lines are very hard
 * to control and there is a limitation on how many lines can be opened.
 * Use this class only if you want to open a lot of lines, because this
 * class should work around the use of lines. If you simply want to play
 * one music piece in your application at the same time then use of the
 * Sound API is preferred.
 * </p>
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
 * @version 10-8-2014
 * 
 * @see AudioFormat
 * @see ThreadPool
 * 
 * @author stefanboodt
 *
 */
public class SoundManager extends ThreadPool implements Observer {

	/**
	 * The audioformat used by this object.
	 */
	private AudioFormat playbackFormat;
	
	/**
	 * The lock for pauses.
	 */
	private final static Object pausedLock = new Object();
	
	/**
	 * Keeps track of the paused state of the soundmanager.
	 */
	private boolean paused;
	
	/**
	 * The SoundPlayerThread list that all sound players need to
	 * register to, so they get the updates.
	 */
	private List<SoundPlayerThread> players;
	
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
		players = new LinkedList<SoundPlayerThread>();
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
		
		for (SoundPlayerThread player: players) {
			player.cleanUp();
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
	public void setPaused(boolean paused) {
		if (this.paused != paused) {
			synchronized (pausedLock) {
				this.paused = paused;
				for (SoundPlayerThread player: players) {
					player.setPaused(paused);
				}
				if (!paused) {
					pausedLock.notifyAll();
				}
			}
		}
	}
	
	/**
	 * Gets a sound from a file with the given name.
	 * This is equivalent to #getSound(getAudioInputStream(filename))
	 * @param filename The name of the file the sound is in.
	 * @return The sound out of the file named filename.
	 * @see #getSound(AudioInputStream)
	 * @see #getAudioInputStream(String)
	 */
	public Sound getSound(String filename) {
		return getSound(getAudioInputStream(filename));
	}
	
	/**
	 * Gets the sound given in the Input stream.
	 * @param stream The input stream the sound is in.
	 * @return The sound in the stream.
	 */
	public Sound getSound(AudioInputStream stream) {
		if (stream == null) {
			return null;
		}
		// Calculates the expected length.
		int expectedlength = (int) stream.getFrameLength() * 
				stream.getFormat().getFrameSize();
		
		byte[] samples = new byte[expectedlength];
		DataInputStream is = new DataInputStream(stream);
		try {
			is.readFully(samples);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Sound(samples);
	}
	
	/**
	 * Returns an AudioInputStream that contains the contents of the
	 * file.
	 * @param filename The file to be read.
	 * @return An AudioInputStream containing the sound that
	 * was stored in the file.
	 */
	public AudioInputStream getAudioInputStream(String filename) {
		try {
			AudioInputStream source = 
					AudioSystem.getAudioInputStream(new File(filename));
			return AudioSystem.getAudioInputStream(playbackFormat, source);
		} 
		catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Plays the given sound.
	 * @param sound The sound to play.
	 * @return an InputStream.
	 */
	public InputStream play(Sound sound) {
		return play(sound, null, false);
	}
	
	/**
	 * Plays the given sound, using the given filter.
	 * @param sound The sound to play.
	 * @param filter The used filter.
	 * @param loop true if you want to loop, false otherwise.
	 * @return an InputStream.
	 */
	public InputStream play(Sound sound, AbstractSoundFilter filter, boolean loop) {
		InputStream stream;
		if (sound != null) {
			if (loop) {
				stream = new LoopingByteInputStream(sound);
			}
			else {
				stream = new ByteArrayInputStream(sound.getSamples());
			}
			return play(stream, filter);
		}
		return null;
	}
	
	/**
	 * Equivalent to {@link #play(InputStream, SoundFilter)} with the
	 * SoundFilter set to null.
	 * @param is the inputstream.
	 */
	public InputStream play(InputStream is) {
		return play(is, null);
	}
	
	/**
	 * Plays a sound from an InputStream, and returns directly.
	 * @param stream The inputStream.
	 * @param filter An optional filter.
	 * @return the inputStream.
	 */
	public InputStream play(InputStream stream, SoundFilter filter) {
		if (stream != null) {
			if (filter != null) {
				stream = new FilteredSoundStream(stream, filter);
			}
			SoundPlayerThread spt = new SoundPlayerThread(stream, playbackFormat);
			submit(spt);
			spt.addObserver(this);
			this.players.add(spt);
		}
		return stream;
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
	
	@Override
	public void update(Observable observable, Object arg) {
		if (observable instanceof SoundPlayerThread) {
			SoundPlayerThread player = (SoundPlayerThread) observable;
			if (arg.equals(SoundPlayerThread.DESTROYED)) {
				int times = 0;
				while (players.contains(player)) {
					players.remove(player);
					times++;
				}
				if (times == 0) {
					System.out.println("SoundPlayerThread " + player + 
							" was not contained in the list.");
				}
			}
		}
	}
	
	/**
	 * This is the Thread that lets the music play.
	 * The only thing that can be Observed from this class is the
	 * destruction of this class.
	 * 
	 * @since 10-8-2014
	 * @version 10-8-2014
	 * 
	 * @see Thread
	 * @see Sound
	 * @see FilteredSoundStream
	 * 
	 * @author stefanboodt
	 *
	 */
	public static class SoundPlayerThread extends Observable
		implements Runnable {
		
		/**
		 * The source of the player.
		 */
		private InputStream source;
		
		/**
		 * The AudioFormat used.
		 */
		private AudioFormat format;
		
		/**
		 * The SourceDataLine
		 */
		private SourceDataLine line;
		
		/**
		 * The buffer used.
		 */
		private byte[] buffer;
		
		/**
		 * Whether or not this player is paused.
		 */
		private boolean paused;
		
		/**
		 * Indicates a change in the paused value.
		 */
		public static final String PAUSED_CHANGE = "paused was changed";
		
		/**
		 * Indicates the destruction of the player.
		 */
		public static final String DESTROYED = "destroyed";
		
		/**
		 * Creates a new SoundPlayerThread with the given source, that
		 * is not paused.
		 * @param source The source of the SoundPlayerThread.
		 * @param format The AudioFormat object.
		 */
		public SoundPlayerThread(InputStream source, AudioFormat format) {
			this(source, format, false);
		}
		
		/**
		 * Creates a new SoundPlayerThread with the given source.
		 * @param source The source of the SoundPlayerThread.
		 * @param format The AudioFormat object.
		 */
		public SoundPlayerThread(InputStream source, AudioFormat format, boolean paused) {
			this.source = source;
			this.format = format;
			this.paused = paused;
			startUpThread();
		}
		
		public void setPaused(boolean newPaused) {
			paused = newPaused;
			notifyObservers(PAUSED_CHANGE);
		}
		
		/**
		 * Makes it possible to querie the state.
		 * @return whether or not this player should be in paused mode.
		 */
		public boolean isPaused(){
			return paused;
		}
		
		/**
		 * Makes the music play.
		 */
		@Override
		public void run() {
			if (line == null || buffer == null) {
				cleanUp();
				return;
			}
			try {
				int numBytesRead = 0;
				while (numBytesRead != -1) {
					synchronized (pausedLock) {
						if (paused) {
							try {
								pausedLock.wait();
							}
							catch (InterruptedException e) {
								/* Prints StackTrace to notify users of
								 the error. */
								e.printStackTrace();
								return;
							}
						}
						numBytesRead = source.read(buffer, 0, buffer.length);
						if (numBytesRead != -1) {
							line.write(buffer, 0, numBytesRead);
						}
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			cleanUp();
		}
		
		/**
		 * Tests if the two Objects are equal.
		 * they are equal iff
		 * both are SoundPlayerThreads and both have
		 * the same source, format and line.
		 */
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (other instanceof SoundPlayerThread) {
				SoundPlayerThread that = (SoundPlayerThread) other;
				return (this.source.equals(that.source) && 
						this.line.equals(that.line) &&
						this.format.equals(that.format));
			}
			return false;
		}
		
		/**
		 * Returns a slightly readable representation of the
		 * Object.
		 */
		@Override
		public String toString() {
			return "SoundPlayerThread(InputStream = " + source.toString() + 
				", SourceDataLine = " + line.toString() + ", format = " + 
					format.toString() + ")";
		}
		
		/**
		 * Does any start up for the Thread.
		 */
		private void startUpThread() {
			int bufferSize = format.getFrameSize() *
					Math.round(format.getSampleRate() / 10);
			try {
				line = AudioSystem.getSourceDataLine(format);
				line.open(format, bufferSize);
			}
			catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			line.start();
			buffer = new byte[bufferSize];
		}
		
		/**
		 * Does the clean up. It drains and closes the line.
		 * It then sends a message to it's Observers.
		 */
		private void cleanUp() {
			if (line != null) {
				line.drain();
				line.close();
			}
			this.notifyObservers(DESTROYED);
		}
	}
	
	/**
	 * A ByteArrayInputStream that loops over an byte array until close
	 * is called.
	 * 
	 * @since 10-8-2014
	 * @version 10-8-2014
	 * 
	 * @see ByteArrayInputStream
	 * @see Sound
	 * 
	 * @author stefanboodt
	 *
	 */
	public static class LoopingByteInputStream 
		extends ByteArrayInputStream {
		
		/**
		 * Keeps track of whether or not this stream is closed.
		 */
		private boolean closed;
		
		/**
		 * Creates a Looping Byte Input Stream with the given buffer.
		 * @param samples The buffer this class controls and plays.
		 */
		public LoopingByteInputStream(byte[] samples) {
			super(samples);
			closed = false;
		}
		
		/**
		 * Creates a looping byte input stream that plays the sound.
		 * Equivalent to {@link #SoundManager(byte[])} with the given
		 * byte array equivalent to sound.getSamples.
		 * @param sound The sound in the input stream.
		 * @see #getSamples()
		 */
		public LoopingByteInputStream(Sound sound) {
			this(sound.getSamples());
		}
		
		/**
		 * Closes the LoopingByteInputStream. This makes the looping
		 * and playing stop.
		 */
		@Override
		public void close() throws IOException {
			super.close();
			closed = true;
		}
		
		/**
		 * Reads up to len bytes of data into an array of bytes from this input stream.
		 * If pos equals count, then the end of the file is reached and we start over.
		 * Otherwise, the number k of bytes read is equal to the smaller of len
		 * and count-pos. If k is positive, then bytes buf[pos] through buf[pos+k-1]
		 * are copied into b[off] through b[off+k-1] in the manner performed by
		 * System.arraycopy. The value k is added into pos and k is returned.
		 */
		@Override
		public int read(byte[] buffer, int offset, int length) {
			if (closed) {
				return -1;
			}
			int totalBytesRead = 0;
			// Now the reading happens.
			while (totalBytesRead < length) {
				int numBytesRead = super.read(buffer, 
						offset + totalBytesRead,
						length - totalBytesRead);
				
				if (numBytesRead > 0) {
					totalBytesRead += numBytesRead;
				}
				else {
					reset();
				}
			}
			return totalBytesRead;
		}
	}
	
	/**
	 * This class is simply a wrapper around a byte array.
	 * It makes clearer though what you are talking about.
	 * 
	 * @since 9-8-2914
	 * @version 10-8-2014
	 * 
	 * @see SoundManager
	 * 
	 * @author stefanboodt
	 *
	 */
	public static class Sound {
		
		/**
		 * The stored samples.
		 */
		private byte[] samples;
		
		/**
		 * Creates a sound with the given byteArray.
		 * @param samples The array of bytes read.
		 */
		public Sound(byte[] samples) {
			this.samples = samples;
		}
		
		/**
		 * Creates a sound from the given collection.
		 * @param samples The collection of bytes.
		 */
		public Sound(Collection<Byte> samples) {
			this.samples = new byte[samples.size()];
			Byte[] array = (Byte[]) samples.toArray();
			for (int i = 0; i < array.length; i++) {
				this.samples[i] = array[i];
			}
		}
		
		/**
		 * Get the sample stored in this sound.
		 * @return The samples the sound was created with.
		 */
		public byte[] getSamples() {
			return samples;
		}
	}
}
