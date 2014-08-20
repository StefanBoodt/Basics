package testEncode;

import static encode.ZipHandler.CommentedFile;

import java.io.*;
import java.util.zip.ZipFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import encode.ZipHandler;

/**
 * Tests the ZipHandler class
 * 
 * @since 20-8-2014
 * @version 20-8-2014
 * 
 * @see ZipHandler
 * 
 * @author stefanboodt
 *
 */
public class ZipHandlerTest {

	/**
	 * The ZipHandler under test.
	 */
	private ZipHandler handler;
	
	/**
	 * The Zipfile used by the handler.
	 */
	private ZipFile zip;
	
	/**
	 * The files folder.
	 */
	private static final String FILES_FOLDER = "files/";
	
	/**
	 * The foreign zip used by the test.
	 */
	private static final String FOREIGN_ZIP_LOCATION = FILES_FOLDER + 
			"ForeignZip.zip";
	
	private final static String COMMENTED = "commented/";
	
	/**
	 * A file in the zip.
	 */
	private static final String ZIP2_NAME = "Zippart2.txt";
	
	/**
	 * A file in the zip.
	 */
	private static final String ZIP1_NAME = "Zippart1.txt";
	
	/**
	 * A file to be zipped.
	 */
	private static final String ZIP1_LOC = FILES_FOLDER + ZIP1_NAME;
	
	/**
	 * A file to be zipped.
	 */
	private static final String ZIP2_LOC = FILES_FOLDER + ZIP2_NAME;
	
	/**
	 * The name of the created zip.
	 */
	private static final String ZipName = "testZip.zip";
	
	/**
	 * Remembers the location of the zip
	 */
	private static final String ZipLOC = FILES_FOLDER + ZipName;
	
	/**
	 * The name of the created zip.
	 */
	private static final String ZipName2 = "testZip2.zip";
	
	/**
	 * Remembers the location of the zip
	 */
	private static final String ZipLOC2 = FILES_FOLDER + COMMENTED + ZipName2;
	
	/**
	 * Does the necessary set up.
	 * @throws Exception If the set up fails.
	 */
	@Before
	public void setUp() throws Exception {
		zip = new ZipFile(ZipLOC);
		setZipHandler(new ZipHandler(zip));
	}

	/**
	 * Does any necessary clean up.
	 * @throws Exception If the clean up fails.
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Tests the {@link ZipHandler#zip(String, String...)} method.
	 * @throws IOException If an IOException is thrown.
	 */
	@Test
	public void testZip() throws IOException {
		ZipHandler.zip(ZipLOC, ZIP1_LOC, ZIP2_LOC);
	}
	
	/**
	 * Tests the {@link ZipHandler#zip(String, String...)} method.
	 * @throws IOException If an IOException is thrown.
	 */
	@Test
	public void testZipAndUnzipCommented() throws IOException {
		ZipHandler.zip(ZipLOC2, "Some random comment",
			new CommentedFile(ZIP1_LOC, "Comment1"),
			new CommentedFile(ZIP2_LOC, "The comment can also include spaces"));
		ZipHandler.unzip(new ZipFile(ZipLOC2), FILES_FOLDER + COMMENTED);
	}
	
	/**
	 * Tests the {@link ZipHandler#unzip(String)} method.
	 * @throws IOException When an IOException occurs.
	 */
	@Test
	public void testUnzipLOC() throws IOException {
		handler.unzip(FILES_FOLDER + "unzipped/");
	}
	
	/**
	 * Sets the handler to the given value.
	 * @param handler The new handler.
	 */
	public final void setZipHandler(ZipHandler handler) {
		this.handler = handler;
	}
}
