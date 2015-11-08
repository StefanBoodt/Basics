package game;

/**
 * This interface gives the Hero certain methods to give it certain
 * functionality.
 * 
 * @since 16-8-2014
 * @version 16-8-2014
 * 
 * @see interfaces.Drawable
 * @see GameObject
 * @see Enemy
 * 
 * @author stefanboodt
 *
 */
public interface Hero extends GameObject {

	/**
	 * Sets the vertical distance between two move calls.
	 * @param dx The amount moved every move.
	 */
	public void setDX(float dx);
	
	/**
	 * Gets the vertical distance between two move calls.
	 * @return The amount moved every move.
	 */
	public float getDX();
	
	/**
	 * Sets the horizontal distance between two move calls.
	 * @param dy The amount moved every move.
	 */
	public void setDY(float dy);
	
	/**
	 * Gets the horizontal distance between two move calls.
	 * @return The amount moved every move.
	 */
	public float getDY();
	
	/**
	 * Moves the hero.
	 */
	public void move();
	
	/**
	 * Updates the sprite of the hero and it's movements by elapsedTime.
	 * @param ElapsedTime The time that has elapsed since the last update.
	 */
	public void update(long ElapsedTime);
}
