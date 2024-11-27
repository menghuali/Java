package myjava.concurrent_pattern.m04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierInAction {

    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1() {
        System.out.println("== Demo 1 ==");
        final CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            System.out.println("Barrier openning");
        });
        final Random random = new Random();

        Callable<String> friend = () -> {
            Thread.sleep(random.nextInt(20) * 100 + 100);
            String threadName = Thread.currentThread().getName();
            System.out.println(String.format("%s: I just arrived, waiting for the others...", threadName));
            try {
                barrier.await(900, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                System.out.println(String.format("%s: Timeout!", threadName));
                throw e;
            } catch (InterruptedException e) {
                System.out.println(String.format("%s: Interrupted!", threadName));
                throw e;
            } catch (BrokenBarrierException e) {
                System.out.println(String.format("%s: Broken!", threadName));
                throw e;
            }
            System.out.println(String.format("%s: Let's go to the cinema!", threadName));
            return String.format("%s: OK", threadName);
        };

        ExecutorService exector = Executors.newFixedThreadPool(4);
        List<Future<String>> futures = new ArrayList<>();
        try {
            for (int j = 0; j < 4; j++) {
                futures.add(exector.submit(friend));
            }
            for (Future<String> future : futures) {
                try {
                    System.out.println(future.get());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } finally {
            exector.shutdown();
        }
        System.out.println("== End ==");
    }

    private static void demo2() {
        System.out.println("\n\n\n== Demo 2 ==");
        final CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            System.out.println("Barrier openning");
        });
        final Random random = new Random();

        Callable<String> friend = () -> {
            Thread.sleep(random.nextInt(20) * 100 + 100);
            String threadName = Thread.currentThread().getName();
            System.out.println(String.format("%s: I just arrived, waiting for the others...", threadName));
            try {
                barrier.await();
            } catch (InterruptedException e) {
                System.out.println(String.format("%s: Interrupted!", threadName));
                throw e;
            } catch (BrokenBarrierException e) {
                System.out.println(String.format("%s: Broken!", threadName));
                throw e;
            }
            System.out.println(String.format("%s: Let's go to the cinema!", threadName));
            return String.format("%s: OK", threadName);
        };

        ExecutorService exector = Executors.newFixedThreadPool(4);
        List<Future<String>> futures = new ArrayList<>();
        try {
            for (int j = 0; j < 4; j++) {
                futures.add(exector.submit(friend));
            }
            for (Future<String> future : futures) {
                try {
                    System.out.println(future.get(1000, TimeUnit.MILLISECONDS));
                } catch (TimeoutException e) {
                    System.out.println("Timeout!");
                    future.cancel(true);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println(e);
                }
            }
        } finally {
            exector.shutdown();
        }
        System.out.println("== End ==");
    }

}
