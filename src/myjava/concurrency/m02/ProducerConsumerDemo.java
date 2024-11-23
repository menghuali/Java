package myjava.concurrency.m02;

import java.util.Random;

public class ProducerConsumerDemo {

    private static class Buffer {

        int[] buffer;
        int count;

        public Buffer(int capacity) {
            buffer = new int[capacity];
            count = 0;
        }

        public boolean isFull() {
            return count == buffer.length - 1;
        }

        public void produce(int element) {
            buffer[count++] = element;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public int consume() {
            return buffer[--count];
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer(15);
        // int loop = 200;

        Runnable producer = () -> {
            Random r = new Random();
            try {
                while (true) {
                    synchronized (buffer) {
                        while (buffer.isFull()) {
                            System.out.println(String.format("[%s]Waiting", Thread.currentThread().getName()));
                            buffer.wait();
                        }
                        int element = r.nextInt();
                        // System.out.println(String.format("[%s:%d] Producing",
                        // Thread.currentThread().getName(), i));
                        System.out.println(String.format("[%s]%d", Thread.currentThread().getName(), element));
                        buffer.produce(element);
                        buffer.notifyAll();
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread[] producers = new Thread[10]; // Won't for mulitple producer
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(producer, String.format("Producer_%d", i));
        }

        Runnable consumer = () -> {
            try {
                while (true) {
                    synchronized (buffer) {
                        while (buffer.isEmpty()) {
                            System.out.println(String.format("[%s]Waiting", Thread.currentThread().getName()));
                            buffer.wait();
                        }
                        System.out
                                .println(String.format("[%s]%d", Thread.currentThread().getName(), buffer.consume()));
                        buffer.notifyAll();
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread[] consumers = new Thread[1]; // Won't work for multiple consumers
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(consumer, String.format("Consumer_%d", i));
        }

        for (Thread thread : producers) {
            thread.start();
        }

        for (Thread thread : consumers) {
            thread.start();
        }

        for (Thread thread : producers) {
            thread.join();
        }
        for (Thread thread : consumers) {
            thread.join();
        }
    }

}
