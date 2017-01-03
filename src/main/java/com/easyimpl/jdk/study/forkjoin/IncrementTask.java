package com.easyimpl.jdk.study.forkjoin;

import lombok.Data;

import java.util.concurrent.*;

/**
 * Created by yupeng on 1/3/2017.
 * 这个是正确的
 */
@Data
public class IncrementTask extends RecursiveTask<Integer> {
    private final Integer THRESHOLD = 1;
    private int lo;
    private int hi;

    IncrementTask(int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
    }

    public static void main(String[] args) {
        IncrementTask incrementTask = new IncrementTask(0, 40);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long start = System.nanoTime();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(incrementTask);
        try {
            System.out.println(submit.get());
            long end = System.nanoTime();
            System.out.println(end - start);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ForkJoinPool forkJoinPool2 = new ForkJoinPool();
        CountTask task = new CountTask(0, 40);
        long start2 = System.nanoTime();
        Future<Integer> result = forkJoinPool2.submit(task);
        try {
            System.out.println(result.get());
            long end = System.nanoTime();
            System.out.println(end - start2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Integer compute() {
        Integer sum = 0;
        if (hi - lo <= THRESHOLD) {
            for (int i = lo; i <= hi; i++) {
                sum = CountTask.caculate(sum, i);
            }
        } else {
            int mid = (lo + hi) >>> 1;
            IncrementTask incrementTask = new IncrementTask(lo, mid);
            IncrementTask incrementTask1 = new IncrementTask(mid + 1, hi);
            invokeAll(incrementTask, incrementTask1);
            try {
                Integer l = CountTask.caculate(incrementTask.get(), incrementTask1.get());
                sum = l;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}
