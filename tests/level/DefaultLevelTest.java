package level;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

/**
 * Tests the Default Level implementation.
 * 
 * @since 30-10-2014
 * @version 31-10-2014
 * 
 * @see DefaultLevel
 * @see Level
 * @see LevelTest
 * 
 * @author stefanboodt
 *
 */
public class DefaultLevelTest extends LevelTest {

	@Override
	@Before
	public void setUp() throws Exception {
		setLevel(new DefaultLevel());
	}

	@Override
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * An inner class used for testing that does nothing at all.
	 * 
	 * @since 30-10-2014
	 * @version 31-10-2014
	 * 
	 * @author stefanboodt
	 *
	 */
	public final class DefaultLevel extends Level {
		
		/**
		 * Creates a new Default level.
		 */
		public DefaultLevel() {
			this("Default Level");
		}
		
		/**
		 * Creates a new Default level.
		 * @param name The name of the level.
		 */
		public DefaultLevel(String name) {
			super(name);
		}

		@Override
		public void start() {
			
		}
		
	}

	@Override
	public void testEqualsCopy() {
		final Level other = new DefaultLevel();
		assertEquals(getLevel(), other);
	}

	@Override
	public void testHashCodeCopy() {
		final Level other = new DefaultLevel();
		assertEquals(other.hashCode(), getLevel().hashCode());
	}

}
