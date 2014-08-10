package sound;

import java.io.IOException;

import javax.sound.sampled.*;
import javax.sound.sampled.Control.Type;

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
 * @see SoundManager
 * 
 * @author stefanboodt
 *
 */
public class ClipDelegate {

	/**
	 * The clip you delegate to.
	 */
	private Clip clip;
	
	/**
	 * Creates a new ClipDelegate. After calling this one of the methods
	 * {@link #open(AudioInputStream)}, {@link #open(AudioFormat, byte[], int, int)}
	 * or {@link #open()} should be called.
	 * @throws LineUnavailableException If there are no lines available.
	 * @see #getClip()
	 */
	public ClipDelegate() throws LineUnavailableException {
		setClip(getClip());
	}
	
	/**
	 * Creates a new ClipDelegate with a mixer conform the given info.
	 * After calling this one of the methods
	 * {@link #open(AudioInputStream)}, {@link #open(AudioFormat, byte[], int, int)}
	 * or {@link #open()} should be called.
	 * @throws LineUnavailableException If there are no lines available.
	 * @see AudioSystem#getClip()
	 */
	public ClipDelegate(Mixer.Info info) throws LineUnavailableException {
		setClip(getClip(info));
	}
	
	/**
	 * Equivalent to AudioSystem.getClip().
	 * @throws LineUnavailableException If there are no lines available.
	 * @see AudioSystem#getClip(javax.sound.sampled.Mixer.Info)
	 * @return The requested Clip.
	 */
	protected static synchronized final Clip getClip() throws LineUnavailableException {
		return AudioSystem.getClip();
	}
	
	/**
	 * Equivalent to AudioSystem.getClip(info).
	 * @param info The info an which to select the mixer.
	 * @throws LineUnavailableException If there are no lines available.
	 * @see AudioSystem#getClip(javax.sound.sampled.Mixer.Info)
	 * @return The requested Clip.
	 */
	protected static synchronized final Clip getClip(Mixer.Info info) throws LineUnavailableException {
		return AudioSystem.getClip(info);
	}
	
	/**
	 * Sets the new Clip.
	 * @param clip The new Clip.
	 */
	public synchronized void setClip(Clip clip) {
		this.clip = clip;
	}

	/**
	 * Equivalent to {@link Clip#drain()}
	 */
	public void drain() {
		clip.drain();
	}
	
	/**
	 * Equivalent to {@link Clip#equals(Object)}
	 * Even removes the problem of two clipdelegates being equalized.
	 */
	public boolean equals(Object other) {
		if (other instanceof ClipDelegate) {
			return clip.equals(((ClipDelegate) other).clip);
		}
		return clip.equals(other);
	}
	
	/**
	 * Equivalent to {@link Clip#flush()}
	 */
	public void flush() {
		 clip.flush();
	}

	/**
	 * Equivalent to {@link Clip#start()}
	 */
	public void start() {
		clip.start();
	}

	/**
	 * Equivalent to {@link Clip#stop()}
	 */
	public void stop() {
		clip.stop();
	}

	/**
	 * Equivalent to {@link Clip#isRunning()}
	 */
	public boolean isRunning() {
		return clip.isRunning();
	}

	/**
	 * Equivalent to {@link Clip#isActive()}
	 */
	public boolean isActive() {
		return clip.isActive();
	}

	/**
	 * Equivalent to {@link Clip#getFormat()}
	 */
	public AudioFormat getFormat() {
		return clip.getFormat();
	}

	/**
	 * Equivalent to {@link Clip#getBufferSize()}
	 */
	public int getBufferSize() {
		return clip.getBufferSize();
	}

	/**
	 * Equivalent to {@link Clip#available()}
	 */
	public int available() {
		return clip.available();
	}

	/**
	 * Equivalent to {@link Clip#getFramePosition()}
	 */
	public int getFramePosition() {
		return clip.getFramePosition();
	}

	/**
	 * Equivalent to {@link Clip#getLongFramePosition()}
	 */
	public long getLongFramePosition() {
		return clip.getLongFramePosition();
	}

	/**
	 * Equivalent to {@link Clip#getMicrosecondPosition()}
	 */
	public long getMicrosecondPosition() {
		return clip.getMicrosecondPosition();
	}

	/**
	 * Equivalent to {@link Clip#getLevel()}
	 */
	public float getLevel() {
		return clip.getLevel();
	}

	/**
	 * Equivalent to {@link Clip#getLineInfo()}
	 */
	public javax.sound.sampled.Line.Info getLineInfo() {
		return clip.getLineInfo();
	}

	/**
	 * Equivalent to {@link Clip#open()}
	 */
	public void open() throws LineUnavailableException {
		clip.open();
	}

	/**
	 * Equivalent to {@link Clip#close()}
	 */
	public void close() {
		clip.close();
	}

	/**
	 * Equivalent to {@link Clip#isOpen()}
	 */
	public boolean isOpen() {
		return clip.isOpen();
	}

	/**
	 * Equivalent to {@link Clip#getControls()}
	 */
	public Control[] getControls() {
		return clip.getControls();
	}

	/**
	 * Equivalent to {@link Clip#isControlSupported(Type)}
	 */
	public boolean isControlSupported(Type control) {
		return clip.isControlSupported(control);
	}

	/**
	 * Equivalent to {@link Clip#getControl(Type)}
	 */
	public Control getControl(Type control) {
		return clip.getControl(control);
	}

	/**
	 * Equivalent to {@link Clip#addLineListener(LineListener)}
	 */
	public void addLineListener(LineListener listener) {
		clip.addLineListener(listener);
	}

	/**
	 * Equivalent to {@link Clip#removeLineListener(LineListener)}
	 */
	public void removeLineListener(LineListener listener) {
		clip.removeLineListener(listener);
	}

	/**
	 * Equivalent to {@link Clip#open(AudioFormat, byte[], int, int)}
	 */
	public void open(AudioFormat format, byte[] data, int offset, int bufferSize)
			throws LineUnavailableException {
		clip.open(format, data, offset, bufferSize);
	}

	/**
	 * Equivalent to {@link Clip# open(AudioInputStream)}
	 */
	public void open(AudioInputStream stream) throws LineUnavailableException,
			IOException {
		clip.open(stream);
	}

	/**
	 * Equivalent to {@link Clip#getFrameLength()}
	 */
	public int getFrameLength() {
		return clip.getFrameLength();
	}

	/**
	 * Equivalent to {@link Clip#getMicrosecondLength()}
	 */
	public long getMicrosecondLength() {
		return clip.getMicrosecondLength();
	}

	/**
	 * Equivalent to {@link Clip#setFramePosition(int)}
	 */
	public void setFramePosition(int frames) {
		clip.setFramePosition(frames);
	}

	/**
	 * Equivalent to {@link Clip#setMicrosecondPosition(long)}
	 */
	public void setMicrosecondPosition(long microseconds) {
		clip.setMicrosecondPosition(microseconds);
	}

	/**
	 * Equivalent to {@link Clip#setLoopPoints(int, int)}
	 */
	public void setLoopPoints(int start, int end) {
		clip.setLoopPoints(start, end);
	}

	/**
	 * Equivalent to {@link Clip#loop(int)}
	 */
	public void loop(int count) {
		clip.loop(count);
	}
	
	/**
	 * Makes it loop continiously.
	 */
	public void alwaysLoop() {
		loop(Clip.LOOP_CONTINUOUSLY);
	}
}
