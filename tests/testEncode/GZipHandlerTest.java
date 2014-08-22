package testEncode;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import encode.*;

/**
 * Tests the GZipHandler class
 * 
 * @since 22-8-2014
 * @version 22-8-2014
 * 
 * @see ZipHandler
 * 
 * @author stefanboodt
 *
 */
public class GZipHandlerTest {

	/**
	 * The GZipHandler under test.
	 */
	private GZipHandler handler;
	
	/**
	 * The loc for the String.
	 */
	private final String GZIP_LOC = "files/GZip/zipped.gz";
	
	/**
	 * Does the necessary set up.
	 * @throws Exception If the set up fails.
	 */
	@Before
	public void setUp() throws Exception {
		handler = new GZipHandler(new File(GZIP_LOC));
	}

	/**
	 * Does any necessary clean up.
	 * @throws Exception If the clean up fails.
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Tests the {@link GZipHandler#zip(String, String)} method.
	 * @throws IOException If an IOException is thrown.
	 */
	@Test
	public void testZip() throws IOException {
		GZipHandler.zip("files/Zippart2.txt", GZIP_LOC);
	}
	
	/**
	 * Tests the {@link GZipHandler#unzip(String, String)} method.
	 * @throws IOException When an IOException occurs.
	 */
	@Test
	public void testUnzipLOC() throws IOException {
		handler.unzip("testUnGZIP.txt", "files/unzipped/");
	}
}
