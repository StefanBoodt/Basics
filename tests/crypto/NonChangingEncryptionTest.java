package crypto;

/**
 * Tests the NonChangingEncryption class.
 * 
 * @since 08-11-2015
 * @version 08-11-2015
 * 
 * @see NonChangingEncryption
 * 
 * @author stefanboodt
 *
 */
public class NonChangingEncryptionTest extends EncryptionTest {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.setDecryptor(new NonChangingEncryption());
		setEncryptor(new NonChangingEncryption());
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Invalidated.
	 */
	@Override
	public void testEncryptionChangesMessage() {
	}
	
	/**
	 * Invalidated.
	 */
	@Override
	public void testDecryptionChangesMessage() {
	}

}
