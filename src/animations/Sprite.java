package animations;

import java.awt.Image;

/**
 * The Sprite class. This class implements the code for sprites.
 * Sprites are representations of images in games and some other
 * programs. It represents moving images.
 * 
 * @since 8-8-2014
 * @version 8-8-2014
 * 
 * @see Image
 * @see Animation
 * 
 * @author stefanboodt
 *
 */
public class Sprite {

	/**
	 * The animation of the sprite.
	 */
	private Animation animation;
	
	/**
	 * The x position of the sprite.
	 */
	private float x;
	
	/**
	 * The y position of the sprite
	 */
	private float y;
	
	/**
	 * The acceleration in the x direction.
	 */
	private float dx;
	
	/**
	 * The acceleration in the y direction.
	 */
	private float dy;
	
	/**
	 * Creates a new Sprite with the given animation.
	 */
	public Sprite(Animation anim) {
		super();
		animation = anim;
	}
	
	/**
	 * Updates the sprite and forwards it the given amount of time.
	 * It updates the hidden animation as well as the position of the
	 * sprite. 
	 * @param time The amount of time in milliseconds that passed.
	 */
	public void update(long time) {
		x += dx;
		y += dy;
		animation.update(time);
	}

	/**
	 * Gets the x value of the sprite.
	 * @return the x
	 */
	public final float getX() {
		return x;
	}

	/**
	 * Sets the x.
	 * @param x the x to set
	 */
	public final void setX(float x) {
		this.x = x;
	}

	/**
	 * Gets the y value of the sprite.
	 * @return the y
	 */
	public final float getY() {
		return y;
	}

	/**
	 * Sets the y value of the sprite.
	 * @param y the y to set
	 */
	public final void setY(float y) {
		this.y = y;
	}

	/**
	 * Gets the acceleration of the x.
	 * @return the dx
	 */
	public final float getDx() {
		return dx;
	}

	/**
	 * Sets the acceleration of the x.
	 * @param dx the dx to set
	 */
	public final void setDx(float dx) {
		this.dx = dx;
	}

	/**
	 * Gets the acceleration of the y.
	 * @return the dy
	 */
	public final float getDy() {
		return dy;
	}

	/**
	 * Sets the acceleration of the y.
	 * @param dy the dy to set
	 */
	public final void setDy(float dy) {
		this.dy = dy;
	}
	
	/**
	 * Gets the width of the sprite, which is the width of 
	 * the current image.
	 * @return The width of the current image.
	 */
	public int getWidth() {
		return getImage().getWidth(null);
	}
	
	/**
	 * Gets the height of the sprite, which is the heigth of 
	 * the current image.
	 * @return The height of the current image.
	 */
	public int getHeight() {
		return getImage().getHeight(null);
	}
	
	/**
	 * Gets the image currently displayed by the Sprite.
	 * @return The image displayed.
	 */
	public Image getImage() {
		return animation.getImage();
	}
}
