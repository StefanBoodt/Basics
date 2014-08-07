package animations;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

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
 * @since 7-8-2014
 * @version 7-8-2014
 * 
 * @see Image
 * @see List
 * 
 * @author stefanboodt
 *
 */
public class Animation {

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
	}
	
	/**
	 * Adds the Image to the animation for the given time period.
	 * @param image The image you want to add to the animation.
	 * @param duration The amount of time the image is visible.
	 */
	public synchronized void addFrame(Image image, long duration) {
		AnimeFrame frame = new AnimeFrame(image, duration);
		totalDuration += duration;
	}
}
