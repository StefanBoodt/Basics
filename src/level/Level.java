package level;

import game.Enemy;
import interfaces.Drawable;

import java.awt.Graphics;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * This class monitors a level and contains some methods
 * to play around with levels. It uses a Set to store all the Enemies in
 * the level.
 * 
 * @since 30-10-2014
 * @version 30-10-2014
 * 
 * @author Stefan Boodt
 *
 */
public abstract class Level implements Drawable {

	/**
	 * Keeps track of whether or not the level is finished.
	 */
	private boolean finished;
	
	/**
	 * The enemies found in the game.
	 */
	private Set<Enemy> enemies;
	
	/**
	 * Creates a level.
	 */
	public Level() {
		this(new HashSet<Enemy>());
	}
	
	/**
	 * Creates a level with the given enemies.
	 * @param enemies The enemies found in the enemies.
	 */
	public Level(Set<Enemy> enemies) {
		setEnemies(enemies);
		setUp();
	}
	
	/**
	 * Creates a level with the given enemies.
	 * @param enemies The enemies found in the level.
	 */
	public Level(Collection<Enemy> enemies) {
		setEnemies(enemies);
		setUp();
	}
	
	/**
	 * Does some set up.
	 */
	protected void setUp() {
		setFinished(false);
	}

	/**
	 * Gets the finished state of the level.
	 * @return true if the level is finished.
	 */
	public final boolean isFinished() {
		return finished;
	}

	/**
	 * Sets the finished state to the given state.
	 * @param finished the new value.
	 */
	public synchronized final void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	/**
	 * Starts the level.
	 */
	public abstract void start();
	
	/**
	 * Sets the enemies that are present in the level.
	 * @param enemies The enemies present.
	 */
	public void setEnemies(Collection<Enemy> enemies) {
		if (!(getEnemies().equals(enemies))) {
			Set<Enemy> en = new HashSet<Enemy>();
			for (Enemy enemy: enemies) {
				en.add(enemy);
			}
			setEnemies(en);
		}
	}
	
	/**
	 * Sets the enemies to the given set.
	 * @param enemies The new enemies in this level.
	 */
	public final synchronized void setEnemies(Set<Enemy> enemies) {
		if (!(getEnemies() != null && getEnemies().equals(enemies))) {
			this.enemies = enemies;
		}
	}
	
	/**
	 * Gets all the enemies in this game.
	 * @return The enemies in this game.
	 */
	public Collection<Enemy> getEnemies() {
		return enemies;
	}

	@Override
	public void draw(Graphics g) {
		for (Enemy enemy: getEnemies()) {
			enemy.draw(g);
		}
	}
}
