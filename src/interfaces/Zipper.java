package interfaces;

import java.io.IOException;

/**
 * The interface for all classes that Zip or do some encryption based on
 * zipping.
 * 
 * @since 21-8-2014
 * @version 22-8-2014
 * 
 * @see java.util.zip.ZipFile
 * 
 * @author stefanboodt
 *
 */
public interface Zipper {

	/**
	 * Zips files into a zipfile with the given name.
	 * @param zipname The name of the ZipFile.
	 * @throws IOException if an IOException occurs.
	 */
	public void zip(String zipname) throws IOException;
	
	/**
	 * Unzips the given file in the current directory
	 * @param unzippedname The name of the unzipped file.
	 * @param dir The directory to put the unzipped files in.
	 * @throws IOException If an IOException is thrown.
	 */
	public void unzip(String unzippedname, String dir) throws IOException;
	
	/**
	 * Unzips the file given by the constructor.
	 * @throws IOException If an {@link IOException} occurs.
	 */
	public void unzip() throws IOException;
}
