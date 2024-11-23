package myjava.concurrent_pattern.m02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("unused")
public class ExecutorAndRunnable {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(String.format("I am in thread %s", Thread.currentThread().getName()));
        };
        // runWithThreads(runnable);
        // runWithSingleThreadExecutor(runnable);
        runWithFixedThreadPool(runnable);
    }

    private static void runWithThreads(Runnable runnable) {
        System.out.println("=== Run with threas ===");
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }

    private static void runWithSingleThreadExecutor(Runnable runnable) {
        System.out.println("=== Run with single thread executor ===");
        ExecutorService svc = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            svc.submit(runnable);
        }
        svc.shutdown();
    }

    private static void runWithFixedThreadPool(Runnable runnable) {
        System.out.println("=== Run with fixed thread pool ===");
        ExecutorService svc = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            svc.submit(runnable);
        }
        svc.shutdown();
    }

}
