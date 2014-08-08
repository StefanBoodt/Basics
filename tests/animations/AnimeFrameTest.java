package animations;

import static org.junit.Assert.*;

import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the anime frame class.
 * 
 * @since 8-8-2014
 * @version 8-8-2014
 * 
 * @see AnimeFrame
 * @see Image
 * @see Observer
 * 
 * @author stefanboodt
 *
 */
public class AnimeFrameTest {

	/**
	 * The frame under test.
	 */
	private AnimeFrame frame;
	
	/**
	 * The image in the animeFrame.
	 */
	private Image image;
	
	/**
	 * The printer Image. This is used as other image so we don't have
	 * to load a lot of images.
	 */
	private final Image printer = 
			new ImageIcon("default icons/printer.png").getImage();
	
	/**
	 * The duration of the image and the image frame.
	 */
	private long duration;
	
	/**
	 * Does any set up for this class.
	 * Subclasses should first call super and then set their own values
	 * or change the values.
	 * @throws Exception If the set up fails.
	 */
	@Before
	public void setUp() throws Exception {
		image = new ImageIcon("default icons/help.png").getImage();
		duration = 10;
		frame = new AnimeFrame(image, duration);
	}

	/**
	 * Does any clean up for the class.
	 * @throws Exception If the clean up fails.
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests the retrieval of the duration of the frame.
	 */
	@Test
	public void testGetDuration() {
		assertEquals(duration, frame.getDuration());
	}
	
	/**
	 * Tests the retrieval of the image contained in the frame.
	 */
	@Test
	public void testGetImage() {
		assertEquals(image, frame.getImage());
	}
	
	/**
	 * Tests if the AnimeFrame is reset. That is the anime frame did
	 * not call #setChanged of if it has updated. If #setChanged is
	 * called then it must notify it's Observers or this method fails.
	 */
	@Test
	public void testUpdatesReset() {
		Observer observer = new Observer() {
			@Override
			public void update(Observable o, Object arg) { }
		};
		frame.addObserver(observer);
		frame.setDuration(duration + 1);
		assertFalse(frame.hasChanged());
	}
	
	/**
	 * Tests if the AnimeFrame knows it has been updated and the
	 * update is send to the Observers, for the image.
	 */
	@Test
	public void testUpdatesDone() {
		Observer observer = new Observer() {
			private String arg;
			
			/**
			 * Returns whether or not this observer executed update.
			 * @return true if the update method is called.
			 */
			public String toString() {
				return arg;
			}
			
			@Override
			public void update(Observable o, Object arg) {
				if (arg instanceof String) {
					this.arg = (String) arg;
				}
				else {
					fail("Arg should be a String");
				}
			}
		};
		frame.addObserver(observer);
		frame.setDuration(duration + 1);
		assertEquals(AnimeFrame.DURATION, observer.toString());
	}
	
	/**
	 * Tests if the AnimeFrame knows it has been updated and the
	 * update is send to the Observers, for the image. If subclasses
	 * test on a frame with the image located in 
	 * "default icons/printer.png" this method should be overwritten.
	 */
	@Test
	public void testUpdatesDone2() {
		Observer observer = new Observer() {
			private boolean called;
			
			/**
			 * Returns whether or not this observer executed update.
			 * @param o Ignored
			 * @return true if the update method is called.
			 */
			public boolean equals(Object o) {
				return called;
			}
			
			@Override
			public void update(Observable o, Object arg) {
				called = true;
			}
		};
		frame.addObserver(observer);
		image = printer;
		frame.setImage(image);
		assertTrue(observer.equals(null));
	}
	
	/**
	 * Tests if the setting of the image is done correctly.
	 */
	@Test
	public void testGetImageAfterSetting() {
		image = printer;
		frame.setImage(image);
		assertEquals(image, frame.getImage());
	}
	
	/**
	 * Tests if the setting of the duration is done correctly.
	 */
	@Test
	public void testGetDurationAfterSetting() {
		duration++;
		frame.setDuration(duration);
		assertEquals(duration, frame.getDuration());
	}
	
	/**
	 * Tests equality between the frame and an Object.
	 */
	@Test
	public void testEqualsWithObject() {
		assertFalse(frame.equals(new Object()));
	}
	
	/**
	 * Tests equality between the frame and an Exception.
	 */
	@Test
	public void testEqualsWithException() {
		assertFalse(frame.equals(new Exception()));
	}
	
	/**
	 * Tests equality with two different durations.
	 */
	@Test
	public void testEqualsDifferentDuration() {
		AnimeFrame f2 = new AnimeFrame(image, frame.getDuration() + 10);
		assertFalse(frame.equals(f2));
	}
	
	/**
	 * Tests equality with two different durations and images.
	 * Subclasses that set images to printer.png should overwrite this
	 * method.
	 */
	@Test
	public void testEqualsDifferentDurationAndImage() {
		AnimeFrame f2 = new AnimeFrame(printer, frame.getDuration() + 10);
		assertFalse(frame.equals(f2));
	}
	
	/**
	 * Tests equality with two different Images.
	 * Subclasses that set images to printer.png should overwrite this
	 * method.
	 */
	@Test
	public void testEqualsDifferentImages() {
		AnimeFrame f2 = new AnimeFrame(printer, frame.getDuration());
		assertFalse(frame.equals(f2));
	}
	
	/**
	 * Tests for equality of two AnimeFrames with the same adress.
	 */
	@Test
	public void testEqualsSameAdress() {
		assertEquals(frame, frame);
	}
	
	/**
	 * Tests for equality of two AnimeFrames with the same arguments.
	 */
	@Test
	public void testEqualsTrueThroughCopyingConstructor() {
		AnimeFrame f2 = new AnimeFrame(frame); 
		assertEquals(frame, f2);
	}
	
	/**
	 * Tests for equality of two AnimeFrames with the same arguments.
	 */
	@Test
	public void testEqualsTrue() {
		AnimeFrame f2 = new AnimeFrame(frame.getImage(),
				frame.getDuration()); 
		assertEquals(frame, f2);
	}
	
	/**
	 * Sets the AnimeFrame under test to the given value.
	 * The image and duration used by AnimeFrameTest
	 * are updated as well.
	 * @param frame The new frame.
	 */
	protected synchronized final void setFrame(AnimeFrame frame) {
		this.frame = frame;
		duration = frame.getDuration();
		image = frame.getImage();
	}

	/**
	 * Gets the frame used in this test.
	 * @return The frame under test.
	 */
	protected synchronized final AnimeFrame getFrame() {
		return frame;
	}
}
