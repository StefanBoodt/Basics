package animations;

import java.awt.Image;

/**
 * The AnimeFrame class binds together an image and it's duration.
 * It contains methods to alter the image and duration and to request
 * them. It also adopts the MVC pattern by extending Observable.
 * 
 * @since 7-8-2014
 * @version 7-8-2014
 * 
 * @see Observable
 * @see Image
 * 
 * @author stefanboodt
 *
 */
public class AnimeFrame {

	/**
	 * The Image of the Frame.
	 */
	private Image image;
	
	/**
	 * The duration the frame is visible.
	 */
	private long duration;
	
	/**
	 * Creates an AnimeFrame with the given image and duration.
	 * @param image The image of this frame.
	 * @param duration The amount of time the frame is visible.
	 */
	public AnimeFrame(Image image, long duration) {
		setImage(image);
		setDuration(duration);
	}

	/**
	 * Gets the image.
	 * @return The image of the frame.
	 */
	protected synchronized final Image getImage() {
		return image;
	}
	
	/**
	 * Sets the image to the given value.
	 * @param image The new image.
	 */
	protected synchronized final void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Queries the duration of the visibility of the frame.
	 * @return The duration.
	 */
	protected synchronized final long getDuration() {
		return duration;
	}

	/**
	 * Sets the duration the frame is visible.
	 * @param duration The duration of the frame after this point.
	 */
	protected synchronized final void setDuration(long duration) {
		this.duration = duration;
	}

}
