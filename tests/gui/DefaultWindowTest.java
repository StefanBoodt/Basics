package gui;

import javax.swing.JComponent;

import org.junit.After;
import org.junit.Before;

/**
 * Tests the default implementation of the Window class.
 * 
 * @since 04-11-2014
 * @version 04-11-2014
 * 
 * @see WindowTest
 * @see Window
 * @see DefaultWindowTest.DefaultWindow
 * 
 * @author stefanboodt
 *
 */
public final class DefaultWindowTest extends WindowTest {

	@Override
	@Before
	public void setUp() throws Exception {
		setWindow(new DefaultWindow());
		final boolean visible = true;
		getWindow().setVisible(visible);
	}

	@Override
	@After
	public void tearDown() throws Exception {
		final boolean visible = false;
		getWindow().setVisible(visible);
	}

	/**
	 * A window that makes it possible to test the implementation of the
	 * Window class.
	 * 
	 * @since 04-11-2014
	 * @version 04-11-2014
	 * 
	 * @see Window
	 * 
	 * @author stefanboodt
	 *
	 */
	private final static class DefaultWindow extends Window {
		
		/**
		 * Serial number.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Creates a default window.
		 */
		public DefaultWindow() {
			super();
		}
		
		@Override
		public void setDefaultMainMenu() {
			JComponent comp = new JComponent() {

				/**
				 * Serial number.
				 */
				private static final long serialVersionUID = 1L;
				
			};
			comp.setDoubleBuffered(true);
			setScreen(comp);
		}
	}
}
