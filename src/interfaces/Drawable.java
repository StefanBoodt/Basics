package interfaces;

import static java.awt.RenderingHints.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * This interface makes classes that implement it drawable. That is it's
 * draw method can be called to draw it on the screen.
 * 
 * @since 9-8-2014
 * @version 9-8-2014
 * 
 * @see Graphics
 * @see Graphics2D
 * 
 * @author stefanboodt
 *
 */
public interface Drawable {

	/**
	 * Fills the shape done with the 
	 * @param g The graphics Object.
	 */
	public void draw(Graphics g);
	
	/**
	 * If g is a #Graphics2D object it turns on anti-aliasing. It then
	 * returns g with the anti-aliasing on. This can be ignored as the
	 * anti-aliasing is turned on for the g that is input not on the
	 * (identical) output g.
	 * @param g The Graphics you want to use anti-aliasing.
	 * @return g.
	 */
	public static Graphics turnOnAntiAliasing(Graphics g) {
		if (g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(KEY_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);
		}
		return g;
	}
}
