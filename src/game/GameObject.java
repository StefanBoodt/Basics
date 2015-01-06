package game;

import java.awt.geom.Line2D;

import interfaces.Drawable;

/**
 * This Interface represents the GameObject object.
 * It combines the lot of Drawable and methods commonly used by
 * games. It also makes the Objects moveable and damagable.
 * 
 * @since 16-8-2014
 * @version 24-8-2014
 * 
 * @see Drawable
 * @see GamePiece
 * 
 * @author stefanboodt
 *
 */
public interface GameObject extends GamePiece {
	
	/**
	 * Makes the GameObject hit.
	 * All subclasses should implement what happens if the object is
	 * hit by the given hitter.
	 * @param hitter The Object that hit this one.
	 */
	public void hit(GameObject hitter);
	
	/**
	 * Method that should be called when the GameObject died.
	 */
	public void die();
	
	/**
	 * Gets the hits that must be done to kill the GameObject.
	 * @return The number of hits remaining.
	 */
	public int getRemainingHits();
	
	/**
	 * Checks if the line given by the line2D object is intersected by this
	 * GameObject.
	 * @param line The line to check against.
	 * @return true iff they intersect.
	 */
	public boolean intersects(Line2D line);
}
