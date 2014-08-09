/**
 * This package tests the concurrency package. The documentation of the
 * package is in ThreadPoolTest.
 * 
 * @since 7-8-2014
 * @version 7-8-2014
 * 
 * @author stefanboodt
 * 
 * @see concurrency
 * @see concurrencytest.ThreadPoolTest
 */
package concurrencytest;

import static org.junit.Assert.assertEquals;

import java.awt.Image;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import concurrency.ThreadPool;

/**
 * Tests the ThreadPoolTest. The class actually tests if the the tasks
 * are performed, not if the actions are sped up.
 * 
 * @since 7-8-2014
 * @version 7-8-2014
 * 
 * @see ThreadPool
 * 
 * @author stefanboodt
 *
 */
public class ThreadPoolTest {

	/**
	 * The TreadPool under test.
	 */
	private ThreadPool pool;
	
	/**
	 * Returns the Pool.
	 * @return The pool under test.
	 */
	protected synchronized ThreadPool getPool() {
		return pool;
	}

	/**
	 * Sets the pool to the given value.
	 * @param pool The new thread pool used.
	 */
	protected synchronized void setPool(ThreadPool pool) {
		this.pool = pool;
	}

	/**
	 * Does the required set up.
	 * @throws Exception If something doesn't work.
	 */
	@Before
	public void setUp() throws Exception {
		setPool(new ThreadPool(2));
	}

	/**
	 * Does any clean up for the class.
	 * @throws Exception If the clean up can't be done.
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests if the 4 images is loaded within 10 seconds. That is
	 * the 4 images are loaded on the pool in less than 1/100th of a
	 * second. The test waits for all to finish execution to see if
	 * they do. The test simply tests if the images are equal to null.
	 * If the loading doesn't happen the images should be null.
	 * @throws ExecutionException If a method throws an Exception.
	 * @throws InterruptedException If a thread in the pool
	 * is interrupted
	 */
	@Test(timeout = 10000)
	public final void testLoadImages() throws InterruptedException, ExecutionException {
		Future<Image> imageholder_1;
		Future<Image> imageholder_2;
		Future<Image> imageholder_3;
		Future<Image> imageholder_4;
		imageholder_1 = getPool().addImageLoadingTask("default icons/help.png");
		imageholder_2 = getPool().addImageLoadingTask("default icons/open.png");
		imageholder_3 = getPool().addImageLoadingTask("default icons/printer.png");
		imageholder_4 = getPool().addImageLoadingTask("default icons/opslaan.png");
		while (imageholder_1.get() == null) {}
		while (imageholder_2.get() == null) {}
		while (imageholder_3.get() == null) {}
		while (imageholder_4.get() == null) {}
	}
	
	/**
	 * Tests the task to be added is executed. This method involves
	 * taking a while loop to wait for the runnable to
	 * finish execution.
	 */
	@Test
	public void testAddTask() {
		List<String> optional = new ArrayList<String>();
		setX(0);
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				optional.add("Runnable");
				setX(1);
			}
		};
		getPool().addTask(runner);
		while (x != 1) { }
		final int size = optional.size();
		//System.out.println(size);
		assertEquals(1, size);
	}

	/**
	 * Adds a task to the queue.
	 * @throws NoSuchMethodException If the method doesn't exist. 
	 * @throws SecurityException If there is a security violation.
	 * @throws ExecutionException if the method throws an Exception.
	 * @throws InterruptedException If a thread is interrupted.
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testAddTaskWithReflection() throws NoSuchMethodException, SecurityException, InterruptedException, ExecutionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] arg = {new Integer(100)};
		Class<?>[] classes = {Integer.class};
		Future<?> future = getPool().addTask(this, 
				ThreadPoolTest.class.getMethod("setX", classes), arg);
		assertEquals(new Integer(100), future.get());
	}
	
	/**
	 * Tests whether the return 100 method returns 100.
	 */
	@Test
	public final void testReturn100() {
		assertEquals(100, return100(200));
	}
	
	/**
	 * Tests the close method.
	 * @throws IOException if the close method fails.
	 */
	@Test
	public void testCloseThread() throws IOException {
		pool.close();
		assertEquals(0, pool.getActiveCount());
	}
	
	/**
	 * Tests the close method.
	 * @throws IOException if the close method fails.
	 */
	@Test
	public void testCloseTask() throws IOException {
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				try {
					wait(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		pool.addTask(runner);
		pool.addTask(runner);
		pool.addTask(runner);
		pool.close();
		assertEquals(0, pool.getQueue().size());
	}
	
	/**
	 * Returns 100.
	 * @param x Ignored.
	 * @return 100.
	 */
	private int return100(Integer x) {
		return 100;
	}
	
	/**
	 * Sets the x and returns it.
	 */
	public Integer setX(Integer x) {
		this.x = x;
		return x;
	}
	
	/**
	 * x used in some tests.
	 */
	private Integer x;
}
