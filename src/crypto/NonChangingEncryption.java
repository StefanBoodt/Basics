package crypto;

import interfaces.Decryptor;
import interfaces.Encryptor;

/**
 * This is a base class for all the decorating coders.
 * 
 * @since 08-11-2015
 * @version 08-11-2015
 * 
 * @see Decryptor
 * @see Encryptor
 * 
 * @author stefanboodt
 *
 */
public class NonChangingEncryption implements Encryptor, Decryptor {

	/**
	 * Creates a new NonChangingEncryption instance.
	 */
	public NonChangingEncryption() {
	}
	
	@Override
	public String decrypt(String message) {
		return message;
	}

	/**
	 * Does nothing.
	 */
	@Override
	public void setSubDecryptor(Decryptor decorated) {
	}

	@Override
	public String encrypt(String message) {
		return message;
	}

	/**
	 * Does nothing.
	 */
	@Override
	public void setSubEncryptor(Encryptor encryptor) {
	}

}
