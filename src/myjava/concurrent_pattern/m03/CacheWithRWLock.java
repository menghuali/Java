package myjava.concurrent_pattern.m03;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheWithRWLock {

    public static void main(String[] args) {
        safeWR();
        // unsafeWR();
    }

    private static void unsafeWR() {
        Map<Long, String> cache = new HashMap<>();

        Callable<String> task = () -> {
            Random random = new Random();
            while (true) {
                long key = random.nextLong();
                cache.put(key, Long.toString(key));
                if (cache.get(key) == null) {
                    System.out.println(String.format("Key %s has not been put in the cache.", key));
                }
            }
        };

        System.out.println("Unsafe R/W: Running...");
        ExecutorService exe = Executors.newFixedThreadPool(4);
        try {
            for (int i = 0; i < 4; i++) {
                exe.submit(task);
            }
        } finally {
            exe.shutdown();
        }
    }

    private static void safeWR() {
        Map<Long, String> cache = new HashMap<>();
        ReadWriteLock rwLock = new ReentrantReadWriteLock();
        Lock rLock = rwLock.readLock();
        Lock wLock = rwLock.writeLock();

        Callable<String> task = () -> {
            Random random = new Random();
            while (true) {
                long key = random.nextLong();
                wLock.lock();
                try {
                    cache.put(key, Long.toString(key));
                } finally {
                    wLock.unlock();
                }

                rLock.lock();
                try {
                    if (cache.get(key) == null) {
                        System.out.println(String.format("Key %s has not been put in the cache.", key));
                    }
                } finally {
                    rLock.unlock();
                }
            }
        };

        System.out.println("Safe R/W: Running...");
        ExecutorService exe = Executors.newFixedThreadPool(4);
        try {
            for (int i = 0; i < 4; i++) {
                exe.submit(task);
            }
        } finally {
            exe.shutdown();
        }
    }

}
