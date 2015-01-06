package gui;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * A common class that represents the Window the application is running on.
 * 
 * @since 03-11-2014
 * @version 03-11-2014
 * 
 * @see JFrame
 * 
 * @author stefanboodt
 *
 */
public abstract class Window extends JFrame {

	/**
	 * Serial number.
	 */
	private static final long serialVersionUID = 7446192599263749847L;
	
	/**
	 * The current component.
	 */
	private JComponent current;

	/**
	 * Creates a window in which the application takes place.
	 */
	public Window() {
		super();
		setDefaultMainMenu();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * Sets the default main menu.
	 */
	public abstract void setDefaultMainMenu();
	
	/**
	 * Returns whether or not the component is double buffered. It is
	 * double buffered if all the components it uses are double buffered.
	 * @return true if it is double buffered.
	 */
	public boolean isDoubleBuffered() {
		return current != null && current.isDoubleBuffered();
	}
	
	/**
	 * Sets the screen to the given JComponent. It also removes all other
	 * components for you, so the given one is the only component.
	 * @param component The component.
	 */
	public void setScreen(JComponent component) {
		if  (current != null) {
			this.remove(current);
		}
		current = component;
		this.add(component);
		this.validate();
	}
}
