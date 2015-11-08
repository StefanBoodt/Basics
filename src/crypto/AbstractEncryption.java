package crypto;

import interfaces.Decryptor;
import interfaces.Encryptor;

public abstract class AbstractEncryption implements Decryptor, Encryptor {

	/**
	 * The encryptor decorated by this one.
	 */
	private Encryptor encryptor;
	
	/**
	 * The decryptor used by this one.
	 */
	private Decryptor decryptor;
	
	/**
	 * Creates a new Abstract Encryption with the given encryptor and
	 * decryptor.
	 * @param encryptor The encryptor to use.
	 * @param decryptor The decryptor to use.
	 */
	public AbstractEncryption(Encryptor encryptor, Decryptor decryptor) {
		setSubEncryptor(encryptor);
		setSubDecryptor(decryptor);
	}

	@Override
	public void setSubEncryptor(Encryptor encryptor) {
		this.encryptor = encryptor;
	}

	@Override
	public void setSubDecryptor(Decryptor decorated) {
		this.decryptor = decorated;
	}

	/**
	 * Gets the decorated decryptor.
	 * @return The decorated decryptor.
	 */
	public Decryptor getSubDecryptor() {
		return decryptor;
	}

	/**
	 * Gets the decorated encryptor.
	 * @return The decorated encryptor.
	 */
	public Encryptor getSubEncryptor() {
		return encryptor;
	}
	
	/**
	 * Sets both the encryptor and decryptor to the given
	 * {@code AbstractEncyption}. It is equivalent to:<br />
	 * {@code setSubDecryptor(encryption)}<br />
	 * {@code setSubEncryptor(encryption)}
	 * @param encryption The new encryption decorated.
	 */
	public void setAbstractEncryption(AbstractEncryption encryption) {
		setSubDecryptor(encryption);
		setSubEncryptor(encryption);
	}

}
