package svg;

import java.io.*;

import org.jfree.graphics2d.svg.*;

/**
 * Simply Writes things to an SVG file.
 * @author stefanboodt
 *
 */
public class SVGWriter {

	/**
	 * Unnecessary
	 */
	public SVGWriter() {
		
	}
	
	/**
	 * Writes an SVGGraphics Object to the SVG file.
	 * @param graphics The graphics of the SVG Image.
	 * @param file The file to write to.
	 * @throws IOException If an IOException occurs.
	 */
	public static void writeSVG(SVGGraphics2D graphics, File file) throws IOException {
		writeSVG(graphics.getSVGElement(), file);
	}
	
	/**
	 * Writes an SVGGraphics Object to the SVG file.
	 * @param graphics The graphics of the SVG Image.
	 * @param filename The name of the file to write to.
	 * @throws IOException If an IOException occurs.
	 */
	public static void writeSVG(SVGGraphics2D graphics, String filename) throws IOException {
		writeSVG(graphics, new File(filename));
	}
	
	/**
	 * Writes an SVGGraphics Object to the SVG file.
	 * @param graphics The graphics of the SVG Image.
	 * @param file The file to write to.
	 * @param id The id of the SVG element.
	 * @throws IOException If an IOException occurs.
	 */
	public static void writeSVG(SVGGraphics2D graphics, File file, String id)
			throws IOException {
		writeSVG(graphics.getSVGElement(id), file);
	}
	
	/**
	 * Writes an SVGGraphics Object to the SVG file.
	 * @param graphics The graphics of the SVG Image.
	 * @param filename The name of the file to write to.
	 * @param id The id of the SVG element.
	 * @throws IOException If an IOException occurs.
	 */
	public static void writeSVG(SVGGraphics2D graphics, String filename, String id)
			throws IOException {
		writeSVG(graphics, new File(filename), id);
	}
	
	/**
	 * Writes an SVGGraphics Object to the SVG file.
	 * @param element The SVG element in String form.
	 * @param file The file to write to.
	 * @throws IOException If an IOException occurs.
	 */
	public static void writeSVG(String element, File file) throws IOException {
		SVGUtils.writeToSVG(file, element);
	}
}
