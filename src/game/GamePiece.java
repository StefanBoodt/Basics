package game;

import interfaces.Drawable;

/**
 * This class builds some basic game methods to use in the game.
 * Such as move, isVisible and isConcrete.
 * 
 * @since 24-8-2014
 * @version 24-8-2014
 * 
 * @see Drawable
 * @see Game
 * 
 * @author stefanboodt
 *
 */
public interface GamePiece extends Drawable {

	/**
	 * Moves the GameObject by dx and dy.
	 * @param dx The horizontal distance.
	 * @param dy The vertical distance.
	 */
	public void move(float dx, float dy);
	
	/**
	 * Checks if this object is visible.
	 * It is invisible if the object is invisible or when it is out
	 * of the screen. 
	 * @return true if the object is visible and inside the screen.
	 */
	public boolean isVisible();
	
	/**
	 * Checks if this is concrete. When a piece is concrete it means
	 * that it is touchable. If it is not concrete other gamepieces might
	 * move through it. This default implementation returns true.
	 * @return true by default.
	 */
	public default boolean isConcrete() {
		return true;
	}
}
