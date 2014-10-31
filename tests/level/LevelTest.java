package level;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import game.Enemy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the level class.
 * 
 * @since 30-10-2014
 * @version 30-10-2014
 * 
 * @author stefanboodt
 *
 */
public abstract class LevelTest {

	/**
	 * The level under test.
	 */
	private Level level;
	
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
	 * Tests the {@link Level#getEnemies()} method.
	 */
	@Test
	public void testGetEnemiesAfterSetting() {
		Set<Enemy> enemies = new HashSet<Enemy>();
		final Enemy enemy = mock(Enemy.class);
		enemies.add(enemy);
		getLevel().setEnemies(enemies);
		assertEquals(enemies, getLevel().getEnemies());
	}
	
	/**
	 * Tests the {@link Level#getEnemies()} method.
	 */
	@Test
	public void testGetEnemiesAfterSettingWithCollection() {
		Collection<Enemy> enemies = new ArrayList<Enemy>();
		final Enemy enemy = mock(Enemy.class);
		enemies.add(enemy);
		getLevel().setEnemies(enemies);
		Set<Enemy> expected = new HashSet<Enemy>();
		expected.add(enemy);
		assertEquals(expected, getLevel().getEnemies());
	}
	
	/**
	 * Tests the {@link Level#getEnemies()} method.
	 */
	@Test
	public void testGetEnemies() {
		assertNotNull(getLevel().getEnemies());
	}
	
	/**
	 * Tests the {@link Level#isFinished()} method.
	 */
	@Test
	public void testIsFinishedInitial() {
		assertFalse(level.isFinished());
	}
	
	/**
	 * Tests the {@link Level#isFinished()} method after setting.
	 */
	@Test
	public void testIsFinishedAfterSettingFalse() {
		final boolean finished = false;
		level.setFinished(finished);
		assertFalse(level.isFinished());
	}
	
	/**
	 * Tests the {@link Level#isFinished()} method after setting.
	 */
	@Test
	public void testIsFinishedAfterSettingTrue() {
		final boolean finished = true;
		level.setFinished(finished);
		assertTrue(level.isFinished());
	}
	
	/**
	 * Tests the {@link Level#isFinished()} method after setting.
	 */
	@Test
	public void testIsFinishedAfterSettingTwice() {
		final boolean finished = true;
		level.setFinished(finished);
		final boolean secondSet = false;
		level.setFinished(secondSet);
		assertFalse(level.isFinished());
	}
	
	/**
	 * Tests the {@link Level#getBackGround()} method.
	 * @throws IOException If an IOException is thrown.
	 */
	@Test
	public void testGetBackGround() throws IOException {
		Level lv = getLevel();
		lv.setBackGround(LevelTest.class.getResource("/printer.png"));
		assertNotNull(lv.getBackGround());
	}
	
	/**
	 * Sets the level to the given level.
	 * @param level The new level under test.
	 */
	public void setLevel(Level level) {
		this.level = level;
	}
	
	/**
	 * Returns the level under test.
	 * @return the level under test.
	 */
	public Level getLevel() {
		return level;
	}
}
