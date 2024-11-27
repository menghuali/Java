package myjava.concurrent_pattern.m03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLocks {

    public static void main(String[] args) throws InterruptedException {

        int capacity = 50;
        List<Integer> buffer = new ArrayList<>(capacity);

        final Lock lock = new ReentrantLock();
        final Condition isNotEmpty = lock.newCondition();
        final Condition isNotFull = lock.newCondition();

        Callable<String> consumer = () -> {
            int count = 0;
            while (count++ < 50) {
                try {
                    lock.lock();
                    while (buffer.isEmpty()) {
                        isNotEmpty.await();
                    }
                    buffer.remove(buffer.size() - 1);
                    isNotFull.signal();
                } finally {
                    lock.unlock();
                }
            }
            return String.format("Consumed %d", count - 1);
        };

        Callable<String> producer = () -> {
            int count = 0;
            while (count++ < 50) {
                try {
                    lock.lock();
                    while (buffer.size() == capacity) {
                        isNotFull.await();
                    }
                    buffer.add(1);
                    isNotEmpty.signal();
                } finally {
                    lock.unlock();
                }
            }
            return String.format("Produced %d", count - 1);
        };

        @SuppressWarnings("unchecked")
        List<Callable<String>> tasks = Arrays.asList(
                new Callable[] { consumer, producer });

        // CAUTION: The thread pool and order of consumer/producer matters!! If all
        // thread are assigned to consumers, the program will enter deadlock because
        // consumers will keep waing for producers but there're no thread to execute
        // producers.
        ExecutorService svc = Executors.newFixedThreadPool(2); // Try 1
        try {
            List<Future<String>> futures = svc.invokeAll(tasks);
            for (Future<String> future : futures) {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            svc.shutdown();
        }
    }

}
