package com.easyimpl.jdk.study.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by yupeng on 1/3/2017.
 *
 * 这是反例
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 1;

    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0,40);
        long start = System.nanoTime();
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
            long end = System.nanoTime();
            System.out.println(end - start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        start = System.nanoTime();
//        int sum = 0;
//        for (int i = 0; i <= 40; i++) {
//            sum = CountTask.caculate(sum, i);
//        }
//        System.out.println(sum);
//        long end = System.nanoTime();
//        System.out.println(end - start);

    }

    public static Integer caculate(Integer i1, Integer i2) {
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i1 + i2;
    }


    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum = CountTask.caculate(sum, i);
            }
        } else {
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);

            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            sum = CountTask.caculate(leftResult, rightResult);
        }

        return sum;
    }
}
