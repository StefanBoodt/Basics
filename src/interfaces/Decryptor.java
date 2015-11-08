package interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This Interface makes the decryption of messages easy and possible. It
 * can work together with the Encryptor classes to hide messages and only
 * show them to trusted people, thereby increasing the privacy.
 * 
 * @since 08-11-2015
 * @version 08-11-2015
 * 
 * @see Encoder
 * 
 * @author stefanboodt
 *
 */
public interface Decryptor {
	
	/**
	 * Decrypts the message using the decryptors algorithm. When used
	 * on a differently encrypted method the result will become unreadable.
	 * The Decrypted message is returned.
	 * @param message The message to decrypt.
	 * @return The decrypted message.
	 */
	public String decrypt(String message);
	
	/**
	 * Decrypts the given file.
	 * @param file The file to decrypt.
	 * @return The decrypted message.
	 * @throws FileNotFoundException If the file cannot be located.
	 */
	public default String decrypt(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		StringBuffer buffer = new StringBuffer();
		while (sc.hasNextLine()) {
			buffer.append("\n" + sc.nextLine());
		}
		sc.close();
		if (buffer.length() > 0) {
			buffer.deleteCharAt(0);
		}
		return decrypt(buffer.toString());
	}
	
	/**
	 * Sets the decryptor that is being decorated. This provides an easy
	 * way to combine decryptors. The decorated decryptor should finish
	 * before this one.
	 * @param decorated The decorated decryptor.
	 */
	public void setSubDecryptor(Decryptor decorated);

}
