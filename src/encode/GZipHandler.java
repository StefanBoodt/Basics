package encode;

import interfaces.Zipper;
import io.*;

import java.io.*;
import java.util.zip.*;

/**
 * This class contains methods to support GZipping.
 * GZipping is compressing one single file. See the 
 * <a href = "http://en.wikipedia.org/wiki/Gzip">Wikipedia page on GZip</a>
 * and the <a href = "http://www.ietf.org/rfc/rfc1952.txt">GZipping guide</a>
 * for more information on GZip.
 * 
 * <p>
 * See the {@link ZipHandler} class for methods to aid in Zipping.
 * Zipping can compress multiple files to one single file where
 * GZipping can only compress one file.
 * </p>
 * 
 * @since 21-8-2014
 * @version 31-10-2014
 * 
 * @see ZipHandler
 * @see GZIPInputStream
 * @see GZIPOutputStream
 * @see BufferedReader
 * @see FileWriter
 * 
 * @author stefanboodt
 *
 */
public class GZipHandler implements Zipper {

	/**
	 * The file to operate on.
	 */
	private File file;
	
	/**
	 * Creates a new GZipHandler that uses the given file.
	 * @param file The file used by this GZipHandler.
	 */
	public GZipHandler(File file) {
		this.file = file;
	}
	
	/**
	 * Zips the file with the given name and names the compressed file
	 * as the output.
	 * @param filename The name of the file to zip.
	 * @param output The name of the zipped file after completion.
	 * @throws IOException If an IOException occurs.
	 */
	public static void zip(String filename, String output)
				throws IOException{
		FileInputStream input = new FileInputStream(filename);
		File f = new File(output);
		File parent = f.getParentFile();
		if (parent != null) {
			parent.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(f);
		GZIPOutputStream stream = new GZIPOutputStream(out);
		WritingReader reader = new WritingReader(input, stream);
		reader.readStream();
		reader.close();
	}
	
	/**
	 * Zips the file given by the constructor.
	 * @throws IOException If an IOException occurs.
	 */
	public void zip() throws IOException {
		zip(file.getPath() + ".gz");
	}

	@Override
	public void zip(String zipname) throws IOException {
		zip(file.getPath(), zipname);
	}

	@Override
	public void unzip(String unzippedname, String dir) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		GZIPInputStream stream = new GZIPInputStream(fis);
		if (dir.length() > 0 && dir.endsWith("/")) {
			dir += "/";
		}
		File directory = new File(dir);
		directory.mkdirs();
		File file = new File(dir + unzippedname);
		FileOutputStream fos = new FileOutputStream(file);
		WritingReader wr = new WritingReader(stream, fos);
		wr.readStream();
		wr.close();
	}

	@Override
	public void unzip() throws IOException {
		String filename = file.getName();
		int extindex = filename.lastIndexOf(".");
		if (extindex >= 0) {
			int dirIndex = filename.lastIndexOf("/");
			if (dirIndex < 0) {
				dirIndex = 0;
			}
			else if (dirIndex == filename.length()) {
				dirIndex--;
			}
			if (dirIndex >= extindex) {
				throw new IOException("No file name given.");
			}
			String dir = filename.substring(0, dirIndex + 1);
			filename = filename.substring(dirIndex + 1, extindex);
			unzip(filename, dir);
		}
		else {
			throw new IOException("No . in filename");
		}
	}
}
