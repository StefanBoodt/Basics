package encode;

import interfaces.Zipper;
import io.WritingReader;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Stream;
import java.util.zip.*;

/**
 * This class zips and unzips files. It offers methods that use the
 * <a href="http://docs.oracle.com/javase/8/docs/api/">
 * java.util.zip package</a>. It therefore receives auto updates when
 * that package is updated. It's methods make it easier to use the idea
 * of the package.
 * 
 * @since 20-8-2014
 * @version 21-8-2014
 * 
 * @see ZipFile
 * @see File
 * 
 * @author stefanboodt
 *
 */
public class ZipHandler implements Zipper {
	
	/**
	 * The ZipFile instance used to read the zip files.
	 */
	private ZipFile zipped;
	
	/**
	 * The files array.
	 */
	private File[] files;
	
	/**
	 * Creates a new Zip used for reading the file.
	 * @param file The file to be read.
	 * @throws IOException If an IO exception occurs.
	 * @throws ZipException If a ZIP format exception occurs.
	 */
	public ZipHandler(File file) throws ZipException, IOException {
		this(new ZipFile(file));
	}
	
	/**
	 * Creates a new Zip used for reading the file.
	 * @param file The file to be read.
	 */
	public ZipHandler(ZipFile file) {
		zipped = file;
	}
	
	/**
	 * Creates a new Zip used for reading the file.
	 * @param file The file to be read.
	 * @param set The charset used.
	 * @throws IOException If an IO exception occurs.
	 * @throws ZipException If a ZIP format exception occurs.
	 */
	public ZipHandler(File file, Charset set) throws ZipException, IOException {
		this(new ZipFile(file, set));
	}
	
	/**
	 * Creates a new Zip used for reading the file.
	 * @param filename The name of the file to be read.
	 * @param set The charset used.
	 * @throws IOException If an IO exception occurs.
	 * @throws ZipException If a ZIP format exception occurs.
	 */
	public ZipHandler(String filename, Charset set) throws ZipException, IOException {
		this(new ZipFile(filename, set));
	}
	
	/**
	 * Creates a new Zip used for reading the file.
	 * @param filename The name of the file to be read.
	 * @throws IOException If an IO exception occurs.
	 * @throws ZipException If a ZIP format exception occurs.
	 */
	public ZipHandler(String filename) throws ZipException, IOException {
		this(new ZipFile(filename));
	}
	
	/**
	 * Creates a ZipHandler that zips the files with the given name.
	 * @param filenames The name of the files.
	 */
	public ZipHandler(String... filenames) {
		this(createFileArray(filenames));
	}
	
	/**
	 * Creates a ZipHandler that zips the given files.
	 * @param files The files array that contains files to be zipped.
	 */
	public ZipHandler(File...files) {
		this.files = files;
	}
	
	/**
	 * Creates a ZipHandler that zips the given files.
	 * @param zip The file to read unzipping.
	 * @param files The files array that contains files to be zipped.
	 */
	public ZipHandler(ZipFile zip, File...files) {
		this.files = files;
		zipped = zip;
	}
	
	/**
	 * {@inheritDoc}
	 * This is equivalent to {@link #unzip(ZipFile)} where the given
	 * ZipFile is the zipped parameter.
	 */
	@Override
	public void unzip() throws IOException {
		unzip(zipped);
	}
	
	/**
	 * Unzips the file given by the constructor.
	 * This is equivalent to {@link #unzip(ZipFile, String)} where the given
	 * ZipFile is the zipped parameter.
	 * @param dir The name of the directory to place it in.
	 * @throws IOException If an {@link IOException} occurs.
	 */
	public void unzip(String dir) throws IOException {
		unzip(zipped, dir);
	}
	
	/**
	 * Unzips the file.
	 * @param file The file to be unzipped.
	 * @throws ZipException if a ZIP exception is thrown.
	 * @throws IOException if an IO Exception is thrown.
	 */
	public static void unzip(File file) throws ZipException, IOException {
		unzip(new ZipFile(file));
	}
	
	/**
	 * Unzips the file. Equivalent to {@link #unzip(ZipFile, String)} with
	 * the given string as "".
	 * @see #unzip(ZipFile, String)
	 * @param zipfile The file to be unzipped.
	 * @throws IOException If an IOException is raised.
	 */
	public static void unzip(ZipFile zipfile) throws IOException {
		unzip(zipfile, "");
	}
	
	/**
	 * Unzips the file.
	 * @param zipfile The file to be unzipped.
	 * @param dir The name of the directiony you want to place it in.
	 * @throws IOException If an IOException is raised.
	 */
	public static void unzip(ZipFile zipfile, String dir) throws IOException {
		Enumeration<? extends ZipEntry> enu = zipfile.entries();
		while (enu.hasMoreElements()) {
			ZipEntry entry = enu.nextElement();
			
			String name = entry.getName();
			String comment = entry.getComment();
			long size = entry.getSize();
			long compressedSize = entry.getCompressedSize();
			System.out.println("Name: " + name + " | size: " + size
					+ " | compressed size: " + compressedSize +
					" | comments: " + comment);
			
			File file = new File(dir + name);
			if (name.endsWith("/")) {
				file.mkdirs();
			}
			else {
				File parent = file.getParentFile();
				if (parent != null) {
					parent.mkdirs();
				}
				InputStream is = zipfile.getInputStream(entry);
				FileOutputStream out = new FileOutputStream(file);
				WritingReader wr = new WritingReader(is, out);
				wr.readStream();
				wr.close();
			}
		}
		zipfile.close();
	}
	
	/**
	 * {@inheritDoc}
	 * @param unzippedname The name of the zipped file.
	 */
	@Override
	public void unzip(String unzippedname, String dir) throws IOException {
		unzip(new ZipFile(unzippedname), dir);
	}

	/**
	 * {@inheritDoc}
	 * To use this method you should have used one of the following
	 * constructors: {@link #ZipHandler(File...)} or
	 * {@link #ZipHandler(String...)}
	 * @see #ZipHandler(File...)
	 * @see #ZipHandler(String...)
	 */
	@Override
	public void zip(String zipname) throws IOException {
		if (files == null) {
			throw new IOException("There are no files to zip");
		}
		zip(zipname, files);
	}

	/**
	 * Zips files into making a zip file with the given name.
	 * @param zipname The name the zipfile has after zipping.
	 * @param files The files to be zipped.
	 * @throws IOException If an IO error occurred.
	 */
	public static void zip(String zipname, File...files) throws IOException {
		zip(zipname, null, files);
	}
	
	/**
	 * Zips files into making a zip file with the given name.
	 * @param zipname The name the zipfile has after zipping.
	 * @param comment the comment on the zip file.
	 * @param files The files to be zipped.
	 * @throws IOException If an IO error occurred.
	 */
	public static void zip(String zipname, String comment, File...files) throws IOException {
		CommentedFile[] cfiles = new CommentedFile[files.length];
		for (int i = 0; i < files.length; i++) {
			cfiles[i] = new CommentedFile(files[i], null);
		}
		zip(zipname, comment, cfiles);
	}
	
	/**
	 * Zips files into making a zip file with the given name.
	 * @param zipname The name the zipfile has after zipping.
	 * @param comment the comment on the zip file.
	 * @param files The files to be zipped.
	 * @throws IOException If an IO error occurred.
	 */
	public static void zip(String zipname, String comment, CommentedFile...files) throws IOException {
		FileOutputStream fos = new FileOutputStream(zipname);
		ZipOutputStream zip = new ZipOutputStream(fos);
		
		zip.setComment(comment);
		
		for (CommentedFile cfile: files) {
			addToZip(zip, cfile.getFile(), cfile.getComment());
		}
		
		zip.close();
		fos.close();
	}
	
	/**
	 * Zips files into making a zip file with the given name.
	 * @param zipname The name the zipfile has after zipping.
	 * @param filenames The names of the files to be zipped.
	 * @throws IOException If an IO error occurred.
	 */
	public static void zip(String zipname, String...filenames) throws IOException {
		File[] files = createFileArray(filenames);
		zip(zipname, files);
	}
	
	/**
	 * Adds the file to the zip.
	 * @param zip The zip to write to.
	 * @param file The file to add to the zip.
	 * @throws IOException If an IOException occurs.
	 */
	protected static void addToZip(ZipOutputStream zip, File file) throws IOException {
		addToZip(zip, file, null);
	}
	
	/**
	 * Adds the file to the zip.
	 * @param zip The zip to write to.
	 * @param file The file to add to the zip.
	 * @param comment The comment the file has.
	 * @throws IOException If an IOException occurs.
	 */
	protected static void addToZip(ZipOutputStream zip, File file, String comment) throws IOException {
		System.out.println("Writing " + file.getName() + " to the zip");
		FileInputStream stream = new FileInputStream(file);
		ZipEntry entry = new ZipEntry(file.getName());
		zip.putNextEntry(entry);
		entry.setComment(comment);
		byte[] kb = new byte[1024];
		int length = 0;
		while (length >= 0) {
			zip.write(kb, 0, length);
			length = stream.read(kb);
		}
		stream.close();
		zip.closeEntry();
	}
	
	/**
	 * Adds the file to the zip.
	 * @param zip The zip to write to.
	 * @param filename The name of the file to add to the zip.
	 * @throws IOException If an IOException occurs.
	 */
	protected static void addToZip(ZipOutputStream zip, String filename) throws IOException {
		addToZip(zip, new File(filename));
	}
	
	/**
	 * Creates an array of File from an array of filenames.
	 * @param filenames The names of the files.
	 * @return An array of File.
	 */
	private static File[] createFileArray(String[] filenames) {
		File[] files = new File[filenames.length];
		for (int i = 0; i < files.length; i++) {
			files[i] = new File(filenames[i]);
		}
		return files;
	}
	
	/**
	 * Returns an enumeration of entries over the ZipFile used
	 * by this class.
	 * @return An enumeration of ZipEntries.
	 * @throws IllegalStateException - if the zip file has been closed
	 */
	public Enumeration<? extends ZipEntry> entries() 
			throws IllegalStateException {
		return zipped.entries();
	}
	
	/**
	 * Return an ordered Stream of the ZIP file entries inside the
	 * ZipFile parameter used by this class. Entries appear in the
	 * Stream in the order they appear in the central directory of
	 * the ZIP file.
	 * @return an ordered Stream of entries in this ZIP file
	 * @throws IllegalStateException - if the zip file has been closed
	 */
	public Stream<? extends ZipEntry> stream()
			throws IllegalStateException {
		return zipped.stream();
	}
	
	/**
	 * Combines the comment and the file.
	 * 
	 * @since 20-8-2014
	 * @version 20-8-2014
	 * 
	 * @see ZipFile
	 * @see ZipHandler
	 * 
	 * @author stefanboodt
	 *
	 */
	public static class CommentedFile {
		
		/**
		 * The file.
		 */
		private File file;
		
		/**
		 * The comment by the file.
		 */
		private String comment;
		
		/**
		 * Creates a new commented file.
		 * @param file The file.
		 * @param comment The comment with the file.
		 */
		public CommentedFile(File file, String comment) {
			setFile(file);
			setComment(comment);
		}

		/**
		 * Creates a commented file with the given name and comment.
		 * @param filename The name of the file.
		 * @param comment The comment with the file.
		 */
		public CommentedFile(String filename, String comment) {
			this(new File(filename), comment);
		}
		
		/**
		 * Sets the file to the given value.
		 * @param file The file.
		 */
		public void setFile(File file) {
			this.file = file;
		}
		
		/**
		 * Sets the comment to the given value.
		 * @param comment The comment.
		 */
		public void setComment(String comment) {
			this.comment = comment;
		}
		
		/**
		 * Gets the file used.
		 * @return The file.
		 */
		public File getFile() {
			return file;
		}
		
		/**
		 * Gets the comment used.
		 * @return The comment.
		 */
		public String getComment() {
			return comment;
		}
	}
}
