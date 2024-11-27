package myjava.concurrent_pattern.m04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CountdownLatchInAction {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(4);
        final Random random = new Random();

        Callable<String> friend = () -> {
            try {
                Thread.sleep(random.nextInt(20) * 100 + 100);
                String threadName = Thread.currentThread().getName();
                System.out.println(String.format("%s: I just arrived, waiting for the others...", threadName));
                return String.format("%s: OK", threadName);
            } finally {
                latch.countDown();
            }
        };

        ExecutorService exector = Executors.newFixedThreadPool(4);
        List<Future<String>> futures = new ArrayList<>();
        try {
            for (int j = 0; j < 4; j++) {
                futures.add(exector.submit(friend));
            }
            boolean everyoneArrived = latch.await(1700, TimeUnit.MILLISECONDS);
            if (everyoneArrived) {
                System.out.println("Everyone arrived. Let's go to the cinema!");
                for (Future<String> future : futures) {
                    try {
                        System.out.println(future.get());
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            } else {
                System.out.println("Someone is late!");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            exector.shutdown();
        }
    }

}
