package gui;

import static org.junit.Assert.*;

import javax.swing.JComponent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Window class.
 * 
 * @since 04-11-2014
 * @version 04-11-2014
 * 
 * @see Window
 * 
 * @author stefanboodt
 *
 */
public abstract class WindowTest {

	/**
	 * The window under test.
	 */
	private Window window;
	
	/**
	 * Does some set up.
	 * @throws Exception If the set up fails.
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Does some clean up.
	 * @throws Exception If the clean up fails.
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests the {@link Window#isDoubleBuffered()} method.
	 */
	@Test
	public void testDoubleBuffered() {
		JComponent comp = new JComponent(){
			/**
			 * serial number.
			 */
			private static final long serialVersionUID = 2947096538953799200L;
		};
		final boolean doublebuffered = true;
		comp.setDoubleBuffered(doublebuffered);
		window.setScreen(comp);
		assertTrue(getWindow().isDoubleBuffered());
	}
	
	/**
	 * Tests the {@link Window#isDoubleBuffered()} method.
	 */
	@Test
	public void testDoubleBufferedFalse() {
		JComponent comp = new JComponent(){
			/**
			 * serial number.
			 */
			private static final long serialVersionUID = 2947096538953799200L;
		};
		final boolean doublebuffered = false;
		comp.setDoubleBuffered(doublebuffered);
		window.setScreen(comp);
		assertFalse(getWindow().isDoubleBuffered());
	}

	/**
	 * Tests the {@link Window#isDoubleBuffered()} method.
	 */
	@Test
	public void testIsDoubleBuffered() {
		JComponent comp = new JComponent(){
			/**
			 * serial number.
			 */
			private static final long serialVersionUID = 2947096538953799200L;
		};
		final boolean doublebuffered = true;
		comp.setDoubleBuffered(doublebuffered);
		window.setScreen(comp);
		assertTrue(getWindow().isDoubleBuffered());
	}
	
	/**
	 * Tests the {@link Window#isDoubleBuffered()} method.
	 */
	@Test
	public void testDoubleBufferedAgainstComponent() {
		JComponent comp = new JComponent(){
			/**
			 * serial number.
			 */
			private static final long serialVersionUID = 2947096538953799200L;
		};
		final boolean doublebuffered = false;
		comp.setDoubleBuffered(doublebuffered);
		window.setScreen(comp);
		final boolean expected = comp.isDoubleBuffered();
		assertEquals(expected, getWindow().isDoubleBuffered());
	}
	
	/**
	 * Tests if the amount of components is one after the
	 * {@link Window#setDefaultMainMenu()} method call.
	 */
	@Test
	public void testSetMenu() {
		final int expected = 1;
		assertEquals(expected, window.getComponentCount());
	}
	
	/**
	 * Tests the {@link Window#setScreen(JComponent)} method.
	 */
	@Test
	public void testComponentsAmount() {
		setRandomJComponent();
		setRandomJComponent();
		setRandomJComponent();
		getWindow().setScreen(new JComponent(){
			private static final long serialVersionUID = 2L;
		});
		
		final int expected = 1;
		assertEquals(expected, getWindow().getComponentCount());
	}
	
	private final void setRandomJComponent() {
		getWindow().setScreen(new JComponent(){
			private static final long serialVersionUID = 1L;
		});
	}
	
	/**
	 * Set the window under test.
	 * @param screen The window under test.
	 */
	public void setWindow(Window screen) {
		this.window = screen;
	}
	
	/**
	 * Returns the window under test.
	 * @return The window under test.
	 */
	public Window getWindow() {
		return window;
	}
}
