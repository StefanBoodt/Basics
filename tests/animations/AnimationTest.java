package animations;

import static org.junit.Assert.*;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the animation class.
 * 
 * Due to lack of checkable methods in that class, the tests cover a
 * percentage lower than normal.
 * 
 * @since 8-8-2014
 * @version 8-8-2014
 * 
 * @author stefanboodt
 *
 */
public class AnimationTest {
	
	/**
	 * The animation to test.
	 */
	private Animation animation;

	/**
	 * An image.
	 */
	private final Image image = new ImageIcon("default images/help").getImage();
	
	/**
	 * Does the set up.
	 * @throws Exception If there is a problem with initiallising the
	 * class.
	 */
	@Before
	public void setUp() throws Exception {
		setAnimation(new Animation());
	}

	/**
	 * Sets the animation to the given value.
	 * @param animation The new Animation.
	 */
	protected synchronized final void setAnimation(Animation animation) {
		this.animation = animation;
	}

	/**
	 * Does the neccessary clean up.
	 * @throws Exception If the clean up fails.
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests the getting of the frame while being out of bounds.
	 */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetFrameOutOfBounds() {
		animation.getFrame(4);
	}

	/**
	 * Tests the getting of the frame while being out of bounds.
	 */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetFrameNegative() {
		animation.getFrame(-4);
	}
	
	/**
	 * Adds a frame and sees if the animation knows it.
	 * It also tests {@link #animation.endTime(int)}.
	 */
	@Test
	public void testAddFrame() {
		long duration =  100;
		animation.addFrame(image, duration);
		assertEquals(duration, animation.endTime(0));
	}
	
	/**
	 * Tests if the frame is displayed correctly.
	 */
	@Test
	public void testGetFrameAt() {
		AnimeFrame frame = new AnimeFrame(image, 100);
		animation.addFrame(frame.getImage(), frame.getDuration());
		assertEquals(frame, animation.getFrame(0));
	}
	
	/**
	 * Tests if the frame is updated the update method is called.
	 * This isn't actually tested but there is seen that the
	 * animation is registered. And the code is run.
	 */
	@Test
	public void testFrameUpdate() {
		AnimeFrame frame = new AnimeFrame(image, 100);
		animation.addFrame(frame.getImage(), frame.getDuration());
		animation.getFrame(0).setDuration(101);
		assertEquals(1, animation.getFrame(0).countObservers());
	}
	
	/**
	 * Tests the update method. The getImage method is tested at the
	 * same time. The two can't be split.
	 */
	@Test
	public void testUpdateMethod() {
		Image i2 = new ImageIcon("default icons/printer").getImage();
		animation.addFrame(image, 100);
		animation.addFrame(i2, 50);
		animation.update(125);
		assertEquals(i2, animation.getImage());
	}
	
	/**
	 * Tests the update method. The getImage method is tested at the
	 * same time. The two can't be split.
	 */
	@Test
	public void testUpdateMethod2() {
		Image i2 = new ImageIcon("default icons/printer").getImage();
		animation.addFrame(image, 100);
		animation.addFrame(i2, 50);
		animation.update(99);
		assertEquals(image, animation.getImage());
	}
	
	/**
	 * Tests the reset method.
	 */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testReset() {
		animation.addFrame(image, 100);
		animation.addFrame(image, 100);
		animation.reset();
		animation.getFrame(0);
	}
	
	/**
	 * Tests for equality of two AnimeFrames with the same adress.
	 */
	@Test
	public void testEqualsSameAdress() {
		assertEquals(animation, animation);
	}
	
	/**
	 * Tests for equality of two AnimeFrames with the same frames.
	 */
	@Test
	public void testEqualsSameValues() {
		Animation anime = new Animation();
		animation.reset();
		animation.addFrame(image, 100);
		anime.addFrame(image, 100);
		animation.addFrame(image, 1902);
		anime.addFrame(image, 1902);
		assertEquals(animation, anime);
	}
	
	/**
	 * Tests for equality of two AnimeFrames with other durations.
	 */
	@Test
	public void testEqualsDifferentDuration() {
		Animation anime = new Animation();
		animation.reset();
		animation.addFrame(image, 100);
		anime.addFrame(image, 100);
		Image i2 = new ImageIcon("default icons/open.png").getImage();
		animation.addFrame(i2, 1902);
		anime.addFrame(i2, 1901);
		assertFalse(animation.equals(anime));
	}
	
	/**
	 * Tests for equality of two AnimeFrames with the same adress.
	 */
	@Test
	public void testEqualsWithObject() {
		assertFalse(animation.equals(new Object()));
	}
}
