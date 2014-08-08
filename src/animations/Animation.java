package animations;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * <p>
 * The class that can animate Objects. The animation of these Objects
 * is done through simply changing the image that is displayed. There
 * is meant that the picture is changed from pic1 to pic2 without
 * changing either of them. By using this class all animations can behave
 * the same. Specific behaviour can be achieved by subclassing this
 * class. Although starting from scratch may be advised there.
 * </p>
 * 
 * <p>
 * This class relies on AnimeFrame to store it's frames. When planning
 * on subclassing you should also see that class. Supporting this class
 * with subclasses from AnimeFrame is fragile, yet possible. You should
 * consider that changing the methods of AnimeFrame could destroy this
 * class' functionality.
 * </p>
 * 
 * @since 7-8-2014
 * @version 8-8-2014
 * 
 * @see Image
 * @see List
 * @see AnimeFrame
 * 
 * @author stefanboodt
 *
 */
public class Animation implements Observer {

	/**
	 * The list of Images to play through.
	 */
	private List<AnimeFrame> frames;
	
	/**
	 * The current index of the frame.
	 */
	private int currentFrameIndex;
	
	/**
	 * The time the animation is in. Used to keep track of whether or
	 * not we should use the next frame or stay with this one.
	 */
	private long animTime;
	
	/**
	 * The duration of the whole animation.
	 */
	private long totalDuration;
	
	/**
	 * Keeps track of the end times of all frames.
	 */
	private List<Long> endTimes;
	
	/**
	 * Creates a animation.
	 */
	public Animation() {
		setUp();
	}

	/**
	 * Does some set up for the class.
	 */
	private void setUp() {
		totalDuration = 0;
		frames = new LinkedList<AnimeFrame>();
		endTimes = new LinkedList<Long>();
		start();
	}
	
	/**
	 * Adds the Image to the animation for the given time period.
	 * @param image The image you want to add to the animation.
	 * @param duration The amount of time the image is visible.
	 */
	public synchronized void addFrame(Image image, long duration) {
		AnimeFrame frame = new AnimeFrame(image, duration);
		totalDuration += duration;
		frame.addObserver(this);
		frames.add(frame);
		endTimes.add(totalDuration);
	}
	
	/**
	 * (Re-)Starts the animation.
	 */
	public synchronized void start() {
		animTime = 0;
		currentFrameIndex = 0;
	}
	
	/**
	 * Update the animation to go forward by the given amount of
	 * time. If the new time is in the time of a different frame it
	 * updates the picture seen to the correct one.
	 * @param time The amount of time passed since the last call to
	 * this method. In order to have smooth animations it is
	 * recommended that the time variable is not relatively high. The
	 * time should be given in milliseconds.
	 */
	public synchronized void update(long time) {
		if (frames.size() > 1) {
			animTime += time;
			if (animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrameIndex = 0;
			}
			while (animTime > endTime(currentFrameIndex)) {
				currentFrameIndex++;
			}
		}
	}
	
	/**
	 * Returns the time this frame ends at.
	 * @param FrameIndex The index of the frame you want to calculate
	 * the endtime of.
	 * @return The time the frame at the given index ends.
	 */
	public synchronized final long endTime(int FrameIndex) {
		if (endTimes.size() != frames.size()) {
			updateEndTimes();
		}
		return endTimes.get(FrameIndex);
	}
	
	/**
	 * Updates all end times known to this Animation. Since this method
	 * loops over all AnimeFrames it should be used sparingly. This
	 * method is automatically called by {@link #endTime(int)} to
	 * update the end times if the amount of stored times doesn't
	 * match with the amount of AnimeFrames it must iterate over.
	 */
	protected synchronized final void updateEndTimes() {
		endTimes = new LinkedList<Long>();
		totalDuration = 0;
		for (int i = 0; i < frames.size(); i++) {
			totalDuration += getFrame(i).getDuration();
			endTimes.add(totalDuration);
		}
	}
	
	/**
	 * Returns the frame at the given index.
	 * @param frameIndex The index you want to have.
	 * @return The frame at the given Index.
	 */
	protected synchronized final AnimeFrame getFrame(int frameIndex) {
		return frames.get(frameIndex);
	}

	/**
	 * Updates the end times if the Observable passed is an
	 * AnimeFrame.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof AnimeFrame) {
			updateEndTimes();
		}
	}
}
