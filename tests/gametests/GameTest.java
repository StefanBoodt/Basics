package gametests;

import static org.junit.Assert.*;
import game.Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Game class.
 * 
 * @since 9-8-2014
 * @version 9-8-2014
 * 
 * @see Game
 * 
 * @author stefanboodt
 *
 */
public abstract class GameTest {

	/**
	 * The Game under test.
	 */
	private Game game;
	
	/**
	 * Does some set up.
	 * @throws Exception If the set up fails.
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Does some clean up.
	 * @throws Exception If the clean up fails.
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests if the getElapsedTime method updates the last time.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	@Test
	public void testUpdatingGetElapsedTime() throws InterruptedException {
		long currentTime = game.getLastTime();
		Thread.sleep(10);
		game.getElapsedTime();
		assertFalse(game.getLastTime() == currentTime);
	}
	
	/**
	 * Tests if the game starts on paused.
	 */
	@Test
	public void testInitialState() {
		assertTrue(game.isPaused());
	}
	
	/**
	 * Tests if the game starts on paused and is then stopped.
	 */
	@Test
	public void testStopped() {
		game.stop();
		assertTrue(game.isPaused());
	}
	
	/**
	 * Tests if the game starts on paused and is then started and 
	 * stopped.
	 */
	@Test
	public void testStoppedAfterStarting() {
		game.start();
		game.stop();
		assertTrue(game.isPaused());
	}
	
	/**
	 * Tests if the game starts on paused and is then started.
	 */
	@Test
	public void testStarted() {
		game.start();
		assertFalse(game.isPaused());
	}
	
	/**
	 * Tests if the game starts on paused and is then started and then
	 * started again.
	 */
	@Test
	public void testDoubleStarted() {
		game.start();
		game.start();
		assertFalse(game.isPaused());
	}
	
	/**
	 * Tests if the pause method toggles pause
	 */
	@Test
	public void testPause() {
		game.stop();
		game.pause();
		assertFalse(game.isPaused());
	}
	
	/**
	 * Tests if the pause method toggles pause
	 */
	@Test
	public void testPause2() {
		game.start();
		game.pause();
		assertTrue(game.isPaused());
	}
	
	/**
	 * Tests if the last time is updated when pause is called.
	 * @throws InterruptedException If the sleeping thread is
	 * interrupted.
	 */
	@Test
	public void testUpdatingLastTimeWhenPaused() throws InterruptedException {
		game.start();
		long firstTime = game.getLastTime();
		Thread.sleep(10);
		game.pause();
		assertFalse(game.getLastTime() == firstTime);
	}
	
	/**
	 * Tests if the last time is updated when pause is called.
	 * @throws InterruptedException If the sleeping thread is
	 * interrupted.
	 */
	@Test
	public void testUpdatingLastTimeWhenUnPaused() throws InterruptedException {
		game.stop();
		long firstTime = game.getLastTime();
		Thread.sleep(10);
		game.pause();
		assertFalse(game.getLastTime() == firstTime);
	}
	
	/**
	 * Sets the game under test to the given value.
	 * @param game The game under test.
	 */
	protected final synchronized void setGame(Game game) {
		this.game = game;
	}
}
