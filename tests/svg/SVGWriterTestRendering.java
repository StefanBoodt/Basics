package svg;

//import static svg.SVGWriterTest.*;
//
//import java.awt.Graphics;
//import java.awt.Image;
//
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;

import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests if we can draw a SVG image from a file.
 * 
 * @since 07-01-2014
 * @version 07-01-2014
 * 
 * @see SVGWriter
 * 
 * @author stefanboodt
 *
 */
public class SVGWriterTestRendering {

	/**
	 * The Graphics to test.
	 */
	private static SVGGraphics2D g;
	
	/**
	 * Does some set up.
	 * @throws Exception If the set up fails.
	 */
	@Before
	public void setUp() throws Exception {
		final int width = 100;
		final int height = 302;
		g = new SVGGraphics2D(width, height);
	}

	/**
	 * Does some clean up.
	 * @throws Exception If the clean up fails.
	 */
	@After
	public void tearDown() throws Exception {
		g.dispose();
	}

	/**
	 * Draws complex in a Window.
	 * @throws InterruptedException 
	 */
	@Test
	public void testDrawImagesFromFile() throws InterruptedException {
		//TestFrame frame = new TestFrame();
		//frame.paint(g);
	}
	
	/**
	 * Draws complex in a Window.
	 * @throws InterruptedException 
	 */
	@Test
	public void testDrawImagesFromFile2() throws InterruptedException {
		/*final String filename = DIR + "complex.svg";
		final Image image = new ImageIcon(filename).getImage();
		final int zero = 0;
		g.drawImage(image, zero, zero, null);*/
	}

//	/**
//	 * The JFrame to test with.
//	 * 
//	 * @since 07-01-2014
//	 * @version 07-01-2014
//	 * 
//	 * @see SVGWriter
//	 * 
//	 * @author stefanboodt
//	 *
//	 */
//	protected static class TestFrame extends JFrame {
//		
//		/**
//		 * Serial number.
//		 */
//		private static final long serialVersionUID = 1917119557436281355L;
//
//		/**
//		 * Creates a new test frame.
//		 */
//		protected TestFrame() {
//			setSize(1000, 800);
//			setVisible(true);
//		}
//		
//		/**
//		 * Does the test draw.
//		 */
//		public void paint(Graphics g) {
//			final String filename = DIR + "complex.svg";
//			final Image image = new ImageIcon(filename).getImage();
//			final int zero = 0;
//			g.drawImage(image, zero, zero, null);
//		}
//	}
}
