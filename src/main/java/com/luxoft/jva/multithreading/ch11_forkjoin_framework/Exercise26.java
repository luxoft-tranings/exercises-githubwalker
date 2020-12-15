package com.luxoft.jva.multithreading.ch11_forkjoin_framework;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * In this exercise we will:
 * <ul>
 * <li>Create class {@link SimpleRunnable} that implements {@link Runnable}.</li>
 * <li>Create new {@link Thread} that takes instance of our class.</li>
 * <li>Run this thread and observe results</li>
 * </ul>
 * <p>
 * Class {@link SimpleRunnable} should print us some basic information about thread.
 * Use {@link Thread#getName()} and {@link Thread#getId()}.
 *
 * @author BKuczynski.
 */
public class Exercise26 {

	private static void testForkJoin() {
		int[] ints = new Random().ints(0, 1000000).limit(1000000).toArray();

		ForkJoinPool forkJoinPool = new ForkJoinPool(10);
		MyRecursivesumTask myRecursivesumTask = new MyRecursivesumTask(100000, ints, 0, ints.length);
		Integer sum = forkJoinPool.invoke(myRecursivesumTask);

		int sumCheck = Arrays.stream(ints).sum();
		assert (sum == sumCheck);

		forkJoinPool.shutdown();
	}

	public static void main(String[] args) {
		// your code goes here
		testForkJoin();
	}

}
