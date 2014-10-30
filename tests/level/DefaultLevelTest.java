package level;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Default Level implementation.
 * 
 * @since 30-10-2014
 * @version 30-10-2014
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
	 * @version 30-10-2014
	 * 
	 * @author stefanboodt
	 *
	 */
	public final class DefaultLevel extends Level {

		@Override
		public void start() {
			
		}
		
	}

}
