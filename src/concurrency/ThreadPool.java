package concurrency;

import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.swing.ImageIcon;

/**
 * <p>
 * This class builts a pool of Threads. This pool can be used to
 * do a lot of things at the same time. Allowing the time spend to
 * decrease significantly. It allows datastructures to keep a list
 * of tasks the Threads should do. It provides extra functionality
 * for the ScheduledThreadPoolExecutor class as well as some methods
 * to add functionality.
 * </p>
 * 
 * <p>
 * The usage of this class is made easier to comfort the programmers
 * that use it many times. ThreadPool is a more natural name, and it
 * is a lot shorter than it's superclass.
 * </p>
 * 
 * <p>
 * Furthermore the class contains some simple methods to help you
 * along the way. It's methods are primary wrapper methods. By which
 * is meant methods that simply call other methods already
 * in existence and not changing them. This does however not exclude
 * the adding of methods that take different parameters as arguments
 * and use them.
 * </p>
 * 
 * @since 6-8-2014
 * @version 6-8-2014
 * 
 * @see Thread
 * @see Runnable
 * @see ScheduledThreadPoolExecutor
 * @see Future
 * 
 * @author stefanboodt
 *
 */
public class ThreadPool extends ScheduledThreadPoolExecutor {
	
	/**
	 * Sets up a ThreadPool with the given amount of Threads.
	 * @param amount The number of Threads used by this pool.
	 */
	public ThreadPool(int amount) {
		super(amount);
	}
	
	/**
	 * Adds a task to the pool.
	 * It is equivalent to #submit(Runnable task, void).
	 * Since there can be a lot of situations in which you want a
	 * thread to simply run some code without returning anything you
	 * can call this method for convenience.
	 * @param runner The task you want to add.
	 */
	public void addTask(Runnable runner) {
		submit(runner);
	}
	
	/**
	 * This method adds a new runnable to the end of the queue of tasks.
	 * It transforms the method into a runnable. Calling this method
	 * takes a lot of work away if 
	 * @param invoker The object to invoke the method on. If the method
	 * is static this one doesn't matter.
	 * @param method The method to invoke.
	 * @param parameters The parameters of the method.
	 * @return a future object that is a representation of the
	 * calculation. The get method should be used to get it's return
	 * value.
	 * @throws InvocationTargetException if the method throws an Exception
	 * @throws IllegalArgumentException If the method is passed an
	 * Illegal value.
	 * @throws IllegalAccessException If the method is unaccessable.
	 */
	public Future<?> addTask(Object invoker, Method method,
			Object[] parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return submit(new Runnable() {
			public void run() {
				
			}
		}, method.invoke(invoker, parameters));
	}
	
	/**
	 * Adds a task to load an Image. This is done a lot of times by
	 * games and other applications that rely heavily on the loading
	 * of images.
	 * @param location The location the image is in.
	 * @return A future containing the image.
	 */
	public Future<Image> addImageLoadingTask(String location) {
		return submit(new ImageLoader(location));
	}
	
	/**
	 * This class loads an image from the given location and returns it.
	 * This class also implements the callable interface and is the
	 * callable queued for the method above,
	 * (#addImageLoadingTask(location)).
	 * 
	 * @since 6-8-2014
	 * @version 6-8-2014
	 * 
	 * @see Callable
	 * @see Image
	 * @see Future
	 * 
	 * @author stefanboodt
	 *
	 */
	protected static final class ImageLoader implements Callable<Image> {
		
		/**
		 * The location of the image.
		 */
		private String location;
		
		/**
		 * Creates an ImageLoader with the given location.
		 * @param loc The location of the image.
		 */
		protected ImageLoader(String loc) {
			location = loc;
		}
		
		/**
		 * Loads the image.
		 */
		public Image call() {
			ImageIcon icon = new ImageIcon(location);
			return icon.getImage();
		}
	}
}
