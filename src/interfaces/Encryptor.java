package interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is the interface used to encode text material. It provides an
 * easy way for all different types of encoders to work together. It also
 * provides easy methods to understand while using them.
 * 
 * <p>
 * Encryptors encrypt messages so nobody else can read them. To be allowed
 * to read them yourself most encoders also have decryptors to decrypt the
 * message. However some encryptors are ment to be remain unreadable such
 * as passwords.
 * </p>
 * 
 * @since 08-11-2015
 * @version 08-11-2015
 * 
 * @see Decryptor
 * 
 * @author stefanboodt
 *
 */
public interface Encryptor {
	
	/**
	 * This method encodes the message in the given String. It uses its
	 * own encoding strategy to encrypt the message. After the encoding
	 * has taken place the message should have altered.
	 * @param message The message to encode.
	 * @return The encrypted message.
	 */
	public String encrypt(String message);
	
	/**
	 * Encrypts the file that is used as an argument by it's own
	 * encryption method. This results in an encrypted file which is
	 * unreadable with the eyes.
	 * @param file The file to encrypt.
	 * @return The encrypted message.
	 * @throws FileNotFoundException If the file cannot be located.
	 */
	public default String encrypt(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		StringBuffer buffer = new StringBuffer();
		while (sc.hasNextLine()) {
			buffer.append("\n" + sc.nextLine());
		}
		sc.close();
		if (buffer.length() > 0) {
			buffer.deleteCharAt(0);
		}
		return encrypt(buffer.toString());
	}
	
	/**
	 * Sets the decorated encryptor so you can add multiple encryptors on
	 * top of each other. This makes usage of lots of encryptors possible.
	 * @param encryptor The decorated encryptor that has to do it's work
	 * before this encryptor changes the message.
	 */
	public void setSubEncryptor(Encryptor encryptor);

}
