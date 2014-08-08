package animations;

import static org.junit.Assert.*;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Sprite class.
 * 
 * @since 8-8-2014
 * @version 8-8-2014
 * 
 * @see Sprite
 * 
 * @author stefanboodt
 *
 */
public class SpriteTest {

	private Sprite sprite;
	
	private final Image help = 
			new ImageIcon("default icons/help.png").getImage();
	
	private final Image printer = 
			new ImageIcon("default icons/printer.png").getImage();
	
	@Before
	public void setUp() throws Exception {
		Animation animation = new Animation();
		animation.addFrame(help, 100);
		sprite = new Sprite(animation);
	}

	/**
	 * @param sprite the sprite to set
	 */
	public synchronized final void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests the correctness of the #getX method.
	 */
	@Test
	public void testGetX() {
		assertTrue(0 == sprite.getX());
	}
	
	/**
	 * Tests the correctness of the #getDx method.
	 */
	@Test
	public void testGetDx() {
		assertTrue(0 == sprite.getDx());
	}

	/**
	 * Tests the correctness of the #getY method.
	 */
	@Test
	public void testGetY() {
		assertTrue(0 == sprite.getY());
	}
	
	/**
	 * Tests the correctness of the #getDy method.
	 */
	@Test
	public void testGetDy() {
		assertTrue(0 == sprite.getDy());
	}
	
	/**
	 * Tests the correctness of the #getX method after it is reset.
	 */
	@Test
	public void testGetXAfterSetting() {
		sprite.setX(10);
		assertTrue(10 == sprite.getX());
	}
	
	/**
	 * Tests the correctness of the #getDx method after setting it.
	 */
	@Test
	public void testGetDxAfterSetting() {
		sprite.setDx(10);
		assertTrue(10 == sprite.getDx());
	}

	/**
	 * Tests the correctness of the #getY method after it is set.
	 */
	@Test
	public void testGetYAfterSetting() {
		sprite.setY(10);
		assertTrue(10 == sprite.getY());
	}
	
	/**
	 * Tests the correctness of the #getDy method after it is set.
	 */
	@Test
	public void testGetDyAfterSetting() {
		sprite.setDy(10);
		assertTrue(10 == sprite.getDy());
	}
	
	/**
	 * Tests the update method.
	 */
	@Test
	public void testUpdate() {
		sprite.getAnimation().addFrame(printer, 20);
		sprite.update(110);
		assertEquals(printer, sprite.getImage());
	}
	
	/**
	 * Tests the update method.
	 */
	@Test
	public void testUpdate2() {
		sprite.getAnimation().reset();
		sprite.getAnimation().addFrame(printer, 20);
		sprite.getAnimation().addFrame(help, 20);
		sprite.update(110);
		assertEquals(help, sprite.getImage());
	}
	
	/**
	 * Tests equality with an Object.
	 */
	@Test
	public void testEqualsWithObject() {
		assertFalse(sprite.equals(new Object()));
	}
	
	/**
	 * Tests equality with an Object with the same adress.
	 */
	@Test
	public void testEqualsSameAdress() {
		assertEquals(sprite,sprite);
	}
	
	/**
	 * Tests equality with an Object with the same values.
	 */
	@Test
	public void testEqualsEmpty() {
		sprite.getAnimation().reset();
		assertEquals(sprite, new Sprite(new Animation()));
	}
	
	/**
	 * Tests equality with an Object with the same values.
	 */
	@Test
	public void testEqualsDifferent() {
		sprite.getAnimation().reset();
		sprite.getAnimation().addFrame(help, 10);
		assertFalse(sprite.equals(new Sprite(new Animation())));
	}
	
	/**
	 * Tests the getHeight method
	 */
	@Test
	public void testGetHeight() {
		final int height = help.getHeight(null);
		sprite.getAnimation().reset();
		sprite.getAnimation().addFrame(help, 10);
		assertEquals(height, sprite.getHeight());
	}
	
	/**
	 * Tests the getWidth method.
	 */
	@Test
	public void testGetWidth() {
		final int width = help.getWidth(null);
		sprite.getAnimation().reset();
		sprite.getAnimation().addFrame(help, 10);
		assertEquals(width, sprite.getWidth());
	}
	
	/**
	 * Tests the getImage method.
	 */
	@Test
	public void testGetImage() {
		sprite.getAnimation().reset();
		sprite.getAnimation().addFrame(help, 10);
		assertEquals(help, sprite.getImage());
	}
}
