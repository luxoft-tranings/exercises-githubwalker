package com.luxoft.jva.multithreading.ch11_forkjoin_framework;

import java.util.concurrent.RecursiveTask;

public class MyRecursivesumTask extends RecursiveTask<Integer> {
    private int threshold;
    private int[] array;
    private int leftBound;
    private int rightBound;

    public MyRecursivesumTask(int threshold, int[] array, int leftBound, int rightBound) {
        this.threshold = threshold;
        this.array = array;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    @Override
    protected Integer compute() {
        if (rightBound-leftBound > threshold) {
            int middle = (leftBound + rightBound) >> 1;
            MyRecursivesumTask leftTask = new MyRecursivesumTask(threshold, array, leftBound, middle);
            leftTask.fork();
            MyRecursivesumTask rightTask = new MyRecursivesumTask(threshold, array, middle, rightBound);
            // rightTask.fork();
            return rightTask.compute() + leftTask.join();
        } else {
            int sum = 0;
            for(int i = leftBound; i < rightBound; ++i)
                sum += array[i];
            return sum;
        }
    }
}
