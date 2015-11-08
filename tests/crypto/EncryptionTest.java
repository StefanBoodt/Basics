package crypto;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Test;

import basic.BasicTest;
import interfaces.Decryptor;
import interfaces.Encryptor;

/**
 * Tests the Encryptor and Decryptor Interfaces.
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
public abstract class EncryptionTest extends BasicTest {
	
	/**
	 * The encryptor under test.
	 */
	private Encryptor encryptor;
	
	/**
	 * The decryptor under test.
	 */
	private Decryptor decryptor;

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Sets the encryptor under test.
	 * @param encryptor The encryptor under test.
	 */
	protected void setEncryptor(Encryptor encryptor) {
		setObjectUnderTest(encryptor);
		this.encryptor = encryptor;
	}
	
	/**
	 * Sets the decryptor under test.
	 * @param decryptor The decryptor under test.
	 */
	protected void setDecryptor(Decryptor decryptor) {
		this.decryptor = decryptor;
	}

	/**
	 * Tests the encryption and decryption of a message.
	 */
	@Test
	public void testEncryption() {
		final String message = "1879378472809801mnkjnsl9u29hjksn";
		String encrypted = encryptor.encrypt(message);
		assertEquals(decryptor.decrypt(encrypted), message);
	}

	/**
	 * Tests the encryption and decryption of a message.
	 */
	@Test
	public void testEncryptionEmpty() {
		final String message = "";
		final String encrypted = encryptor.encrypt(message);
		assertEquals(decryptor.decrypt(encrypted), message);
	}

	/**
	 * Tests the encryption and decryption of a message.
	 */
	@Test
	public void testEncryptionDifficult() {
		final String message = "987269hvbusgvbjgiuh28uygebih;ia.b,ahbj jakfbn"
				+ "l \\'a\t\n<b>?!$$&##(ja/MKMVjklnjhks,.::~8";
		final String encrypted = encryptor.encrypt(message);
		assertEquals(decryptor.decrypt(encrypted), message);
	}

	/**
	 * Tests the encryption and decryption of a message that is
	 * encrypted twice.
	 */
	@Test
	public void testEncryptionDouble() {
		final String message = "8hsjhjshijbh:A\"9)98u_+!-njsk";
		String encrypted = encryptor.encrypt(message);
		encrypted = encryptor.encrypt(encrypted);
		assertEquals(decryptor.decrypt(decryptor.decrypt(encrypted)),
				message);
	}

	/**
	 * Tests the encryption and decryption of a File.
	 * @throws FileNotFoundException If the file cannot be located.
	 */
	@Test
	public void testEncryptionFile() throws FileNotFoundException {
		final File file = new File("files/encryption-check.txt");
		StringBuffer buffer = new StringBuffer();
		Scanner sc = new Scanner(file);
		try {
			while(sc.hasNextLine()) {
				buffer.append("\n" + sc.nextLine());
			}
		} finally {
			sc.close();
		}
		buffer = buffer.deleteCharAt(0);
		final String message = buffer.toString();
		final String encrypted = encryptor.encrypt(file);
		final File temp = new File("garbage.txt");
		PrintWriter out = new PrintWriter(temp);
		out.write(encrypted);
		out.flush();
		out.close();
		assertEquals(decryptor.decrypt(temp), message);
		assertTrue("The message should start with a k",
				message.startsWith("k"));
		temp.delete();
	}

	/**
	 * Tests the encryption and decryption of a File.
	 * @throws FileNotFoundException If the file cannot be located.
	 */
	@Test (expected = FileNotFoundException.class)
	public void testEncryptionFileNotFound() throws FileNotFoundException {
		final File file = new File("blarghhhhhhh.txt");
		Scanner sc = new Scanner(file);
		try {
			while(sc.hasNextLine()) {
				sc.nextLine();
			}
		} finally {
			sc.close();
		}
	}

	/**
	 * Tests the encryption and decryption of a message.
	 */
	@Test
	public void testEncryptionReversed() {
		final String message = "0jn\\kkk{adskj ajhi±jka;b;asklkn\t";
		final String decrypted = decryptor.decrypt(message);
		assertEquals(encryptor.encrypt(decrypted), message);
	}

	/**
	 * Tests the encryption alteration of a message.
	 */
	@Test
	public void testEncryptionChangesMessage() {
		final String message = "IKwi98ojwm]["
				+ "][s;ldmglkmls'lkd;gk"
				+ "dkjlmg'sbmnkl\n\n\r\rapokjsklm\talkhjfdl!$%U*$ljanlk"
				+ "sjhfnamn;sm;mpot";
		final String encrypted = encryptor.encrypt(message);
		assertNotEquals(encrypted, message);
	}

	/**
	 * Tests the decryption alteration of a message.
	 */
	@Test
	public void testDecryptionChangesMessage() {
		final String message = "@#%aiklhsndaASDHfnslk/nvjksbADKjnfjk S82#$\\d";
		assertNotEquals(decryptor.decrypt(message), message);
	}

}
