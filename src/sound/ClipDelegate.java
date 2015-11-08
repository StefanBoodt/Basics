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
	 * @param The info with which to select the mixer.
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
	protected final static synchronized Clip getClip() throws LineUnavailableException {
		return AudioSystem.getClip();
	}
	
	/**
	 * Equivalent to AudioSystem.getClip(info).
	 * @param info The info an which to select the mixer.
	 * @throws LineUnavailableException If there are no lines available.
	 * @see AudioSystem#getClip(javax.sound.sampled.Mixer.Info)
	 * @return The requested Clip.
	 */
	protected final static synchronized Clip getClip(Mixer.Info info)
			throws LineUnavailableException {
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
	 * Equivalent to {@link Clip#drain()}.
	 */
	public void drain() {
		clip.drain();
	}
	
	/**
	 * Equivalent to Clip.equals(Object).
	 * Even removes the problem of two clipdelegates being equalized.
	 * @param other The object to compare to.
	 * @return true iff they are equal.
	 */
	public boolean equals(Object other) {
		if (other instanceof ClipDelegate) {
			return clip.equals(((ClipDelegate) other).clip);
		}
		return clip.equals(other);
	}
	
	/**
	 * Equivalent to {@link Clip#flush()}.
	 */
	public void flush() {
		clip.flush();
	}

	/**
	 * Equivalent to {@link Clip#start()}.
	 */
	public void start() {
		clip.start();
	}

	/**
	 * Equivalent to {@link Clip#stop()}.
	 */
	public void stop() {
		clip.stop();
	}

	/**
	 * Equivalent to {@link Clip#isRunning()}.
	 * @return true iff the clip runs.
	 */
	public boolean isRunning() {
		return clip.isRunning();
	}

	/**
	 * Equivalent to {@link Clip#isActive()}.
	 * @return true iff the clip is active.
	 */
	public boolean isActive() {
		return clip.isActive();
	}

	/**
	 * Equivalent to {@link Clip#getFormat()}.
	 * @return The AudioFormat used.
	 */
	public AudioFormat getFormat() {
		return clip.getFormat();
	}

	/**
	 * Equivalent to {@link Clip#getBufferSize()}.
	 * @return the buffer size.
	 */
	public int getBufferSize() {
		return clip.getBufferSize();
	}

	/**
	 * Equivalent to {@link Clip#available()}.
	 * @return the amount of bytes available.
	 */
	public int available() {
		return clip.available();
	}

	/**
	 * Equivalent to {@link Clip#getFramePosition()}.
	 */
	public int getFramePosition() {
		return clip.getFramePosition();
	}

	/**
	 * Equivalent to {@link Clip#getLongFramePosition()}.
	 */
	public long getLongFramePosition() {
		return clip.getLongFramePosition();
	}

	/**
	 * Equivalent to {@link Clip#getMicrosecondPosition()}.
	 */
	public long getMicrosecondPosition() {
		return clip.getMicrosecondPosition();
	}

	/**
	 * Equivalent to {@link Clip#getLevel()}.
	 * @return The amplitude.
	 */
	public float getLevel() {
		return clip.getLevel();
	}

	/**
	 * Equivalent to {@link Clip#getLineInfo()}.
	 * @return The discription of the line.
	 */
	public javax.sound.sampled.Line.Info getLineInfo() {
		return clip.getLineInfo();
	}

	/**
	 * Equivalent to {@link Clip#open()}.
	 * @throws LineUnavailableException If there is no line available.
	 */
	public void open() throws LineUnavailableException {
		clip.open();
	}

	/**
	 * Equivalent to {@link Clip#close()}.
	 */
	public void close() {
		clip.close();
	}

	/**
	 * Equivalent to {@link Clip#isOpen()}.
	 * @return {@code true} iff the line is open.
	 */
	public boolean isOpen() {
		return clip.isOpen();
	}

	/**
	 * Equivalent to {@link Clip#getControls()}.
	 * @return The controls.
	 */
	public Control[] getControls() {
		return clip.getControls();
	}

	/**
	 * Equivalent to {@link Clip#isControlSupported(Type)}.
	 * @param control The requested type.
	 * @return {@code true} iff at least one control of the
	 * requested type is supported
	 */
	public boolean isControlSupported(Type control) {
		return clip.isControlSupported(control);
	}

	/**
	 * Equivalent to {@link Clip#getControl(Type)}.
	 * @param control The type of control you are searching for.
	 * @return A control of the requested type.
	 */
	public Control getControl(Type control) {
		return clip.getControl(control);
	}

	/**
	 * Equivalent to {@link Clip#addLineListener(LineListener)}.
	 * @param listener The listener going to be added.
	 */
	public void addLineListener(LineListener listener) {
		clip.addLineListener(listener);
	}

	/**
	 * Equivalent to {@link Clip#removeLineListener(LineListener)}.
	 * @param listener The listener that is going to be removed.
	 */
	public void removeLineListener(LineListener listener) {
		clip.removeLineListener(listener);
	}

	/**
	 * Equivalent to {@link Clip#open(AudioFormat, byte[], int, int)}.
	 * @param format the format of the supplied audio data
	 * @oaram data a byte array containing audio data to
	 * load into the clip
	 * @param offset the point at which to start copying,
	 * expressed in bytes from the beginning of the array
	 * @param bufferSize the number of bytes of data to load into the
	 * clip from the array.
	 * @throws LineUnavailableException If there is no line available.
	 */
	public void open(AudioFormat format, byte[] data, int offset, int bufferSize)
			throws LineUnavailableException {
		clip.open(format, data, offset, bufferSize);
	}

	/**
	 * Equivalent to {@link Clip#open(AudioInputStream)}.
	 * @param stream The stream to open.
	 * @throws LineUnavailableException If there is no line available.
	 */
	public void open(AudioInputStream stream) throws LineUnavailableException,
			IOException {
		clip.open(stream);
	}

	/**
	 * Equivalent to {@link Clip#getFrameLength()}.
	 * @return the frame length.
	 */
	public int getFrameLength() {
		return clip.getFrameLength();
	}

	/**
	 * Equivalent to {@link Clip#getMicrosecondLength()}.
	 * @return The media duration in microseconds.
	 */
	public long getMicrosecondLength() {
		return clip.getMicrosecondLength();
	}

	/**
	 * Equivalent to {@link Clip#setFramePosition(int)}.
	 * @param frames The index of the frame to switch to.
	 */
	public void setFramePosition(int frames) {
		clip.setFramePosition(frames);
	}

	/**
	 * Equivalent to {@link Clip#setMicrosecondPosition(long)}.
	 * @param microseconds The position to continue from.
	 */
	public void setMicrosecondPosition(long microseconds) {
		clip.setMicrosecondPosition(microseconds);
	}

	/**
	 * Equivalent to {@link Clip#setLoopPoints(int, int)}.
	 * @param start The startpoint of the loop.
	 * @param end The endpoint of the loop.
	 */
	public void setLoopPoints(int start, int end) {
		clip.setLoopPoints(start, end);
	}

	/**
	 * Equivalent to {@link Clip#loop(int)}.
	 * @param count The amount of times to loop.
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
