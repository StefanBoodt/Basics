package gui;

import javax.swing.JComponent;

/**
 * This class contains some default code for the main menu.
 * 
 * @since 04-11-2014
 * @version 04-11-2014
 * 
 * @see JComponent
 * @see Window
 * 
 * @author stefanboodt
 *
 */
public abstract class MainMenu extends JComponent {

	/**
	 * Serial number.
	 */
	private static final long serialVersionUID = -4905765460781590696L;

	/**
	 * The default double buffered value.
	 */
	public static final boolean DEFAULT_DOUBLE_BUFFERED = false;
	
	/**
	 * Creates a new MainMenu that is double buffered if the default
	 * {@link #DEFAULT_DOUBLE_BUFFERED} is true and otherwise is not.
	 */
	public MainMenu() {
		this(DEFAULT_DOUBLE_BUFFERED);
	}
	
	/**
	 * Creates a main menu with the given double buffered state.
	 * @param doublebuffered true if the menu should be double buffered.
	 */
	public MainMenu(final boolean doublebuffered) {
		super();
		this.setDoubleBuffered(doublebuffered);
		setUp();
	}
	
	/**
	 * Creates a menu with the given height width and double buffering.
	 * @param width The width of the menu.
	 * @param height The height of the menu.
	 * @param doublebuffered The double buffered state of the menu.
	 */
	public MainMenu(final int width, final int height,
			final boolean doublebuffered) {
		this(doublebuffered);
		this.setSize(width, height);
		this.setVisible(true);
	}
	
	/**
	 * Does the set up for the menu.
	 */
	protected void setUp() {
		addStartButton();
	}
	
	/**
	 * Adds the start button to the menu.
	 */
	protected abstract void addStartButton();
}
