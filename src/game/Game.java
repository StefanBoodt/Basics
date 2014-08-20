package game;

/**
 * The game class. It represents a game.
 * 
 * @since 9-8-2014
 * @version 9-8-2014
 * 
 * @author stefanboodt
 *
 */
public abstract class Game {

	/**
	 * Checks whether or not the game is paused.
	 */
	private boolean paused;
	
	/**
	 * The time the System was last updated.
	 */
	private long lastTime;
	
	/**
	 * Creates a new Game, and sets it pause to false.
	 */
	public Game() {
		super();
		getElapsedTime();
		paused = false; // So the stop method can be run.
		stop();
	}

	/**
	 * Updates the game and all its game objects. You should call
	 * the {@link #getElapsedTime()} method to get the amount of time
	 * that passed since the last time.
	 */
	public abstract void update();
	
	/**
	 * Gets the elapsed time in milliSeconds.
	 * It calls #System.currentTimeMillis() to calculate the
	 * elapsed time and then sets it so that the next time is 
	 * @return the elapsed time in milliseconds.
	 */
	public synchronized long getElapsedTime() {
		long newtime = System.currentTimeMillis();
		long elapsed = newtime - getLastTime();
		lastTime = newtime;
		return elapsed;
	}
	
	/**
	 * Returns the last time the time was updated.
	 * @return The time stored in {@link #lastTime}.
	 */
	public long getLastTime() {
		return lastTime;
	}

	/**
	 * Toggles the being paused status of the game.
	 */
	public void pause() {
		if (isPaused()) {
			start();
		}
		else {
			stop();
		}
	}
	
	/**
	 * Stops the game. Updates one last time before the pause.
	 */
	public void stop() {
		if (!paused) {
			paused = true;
			update();
		}
	}
	
	/**
	 * Starts the game. Sets the last checked time to now so you get
	 * to continue where you left of.
	 */
	public void start() {
		if (paused) {
			paused = false;
			getElapsedTime();
		}
	}
	
	/**
	 * Gets the paused state of the game.
	 * @return true iff the game is paused.
	 */
	public final boolean isPaused() {
		return paused;
	}
}
