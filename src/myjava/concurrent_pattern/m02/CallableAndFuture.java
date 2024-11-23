package myjava.concurrent_pattern.m02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableAndFuture {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        demo1();
        demo2();
    }

    private static void demo1() throws InterruptedException, ExecutionException, TimeoutException {
        Callable<String> task = () -> {
            Thread.sleep(10);
            return String.format("I am on thread %s", Thread.currentThread().getName());
        };

        ExecutorService svc = Executors.newFixedThreadPool(4);

        try {
            for (int i = 0; i < 10; i++) {
                Future<String> future = svc.submit(task);
                System.out.println(String.format("I get: %s", future.get(100, TimeUnit.MILLISECONDS)));
            }
        } finally {
            svc.shutdown();
        }
    }

    private static void demo2() throws InterruptedException, ExecutionException, TimeoutException {
        Callable<String> task = () -> {
            throw new IllegalStateException(
                    String.format("I throw an exception in thread %s", Thread.currentThread().getName()));
        };

        ExecutorService svc = Executors.newFixedThreadPool(4);

        try {
            for (int i = 0; i < 10; i++) {
                Future<String> future = svc.submit(task);
                System.out.println(String.format("I get: %s", future.get(100, TimeUnit.MILLISECONDS)));
            }
        } finally {
            svc.shutdown();
        }
    }

}
