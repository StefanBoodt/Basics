package animations;

import java.awt.Image;
import java.util.Observable;

/**
 * <p>
 * The AnimeFrame class binds together an image and it's duration.
 * It contains methods to alter the image and duration and to request
 * them. It also adopts the MVC pattern by extending Observable.
 * </p>
 * 
 * <p>
 * This class extends Observable but doesn't change any of it's
 * functionality. The only classes that have some advantage of keeping
 * up-to-date with this class are the classes that reference it in
 * their class definition. This includes the classes that use it to
 * store the frames. Any other class observing this is wasting time
 * because it is really uninteresting for them.
 * </p>
 * 
 * @since 7-8-2014
 * @version 8-8-2014
 * 
 * @see Observable
 * @see Image
 * 
 * @author stefanboodt
 *
 */
public class AnimeFrame extends Observable {

	/**
	 * The Image of the Frame.
	 */
	private Image image;
	
	/**
	 * The duration the frame is visible.
	 */
	private long duration;
	
	/**
	 * The String that tells duration.
	 * It is used by notifyObservers(DURATION) which could indicate
	 * to listening classes that the duration has changed.
	 */
	public final static String DURATION = "duration";
	
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
	 * Creates an AnimeFrame that is a copy of the given frame.
	 * @param frame The frame you want to copy.
	 */
	public AnimeFrame(AnimeFrame frame) {
		this(frame.getImage(), frame.getDuration());
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
	 * Notifies the observers that it's image has changed.
	 * It calls #notifyObservers(image).
	 * @param image The new image.
	 */
	protected synchronized final void setImage(Image image) {
		if (this.image != image) {
			this.image = image;
			setChanged();
		}
		notifyObservers(image);
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
	 * Notifies the observers that it's duration has changed.
	 * It calls {@link notifyObservers(Object)}. With the given duration.
	 * The {@link #DURATION} is the String declared in this class.
	 * @param duration The duration of the frame after this point.
	 */
	protected synchronized final void setDuration(long duration) {
		if (this.duration != duration) {
			this.duration = duration;
			setChanged();
		}
		notifyObservers(DURATION);
	}

	/**
	 * Checks if two Objects are equal.
	 * They are considered equal when the image and duration are
	 * equal.
	 */
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (other instanceof AnimeFrame) {
			AnimeFrame that = (AnimeFrame) other;
			return this.duration == that.duration &&
					this.image.equals(that.image);
		}
		return false;
	}
}
