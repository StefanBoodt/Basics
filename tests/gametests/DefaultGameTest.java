package gametests;

import game.Game;

import org.junit.After;
import org.junit.Before;

/**
 * Tests the default game. Also declares the default game.
 * 
 * @since 9-8-2014
 * @version 9-8-2014
 * 
 * @author stefanboodt
 *
 */
public class DefaultGameTest extends GameTest {

	/**
	 * Does some set up.
	 * @throws Exception If the set up fails.
	 */
	@Before
	public void setUp() throws Exception {
		setGame(new DefaultGame());
	}

	/**
	 * Does some clean up.
	 * @throws Exception If the clean up fails.
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Some Default game that isn't usable but allows the previous
	 * tests to run.
	 * 
	 * @version 9-8-2014
	 * @since 9-8-2014
	 * 
	 * @see Game
	 * 
	 * @author stefanboodt
	 *
	 */
	private static class DefaultGame extends Game{

		/**
		 * Simply calls {@link #getElapsedTime()}.
		 */
		@Override
		public void update() {
			getElapsedTime();
		}
	}
}
