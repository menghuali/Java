package myjava.concurrency.m01;

public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();

        Runnable runnable = () -> {
            for (int i = 0; i < 1_000; i++) {
                c1.increase();
                c2.selfIncrease();
                c3.syncIncrease();
            }
        }; 

        Thread[] threads = new Thread[1_000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Count 1: " + c1.getValue());
        System.out.println("Count 2: " + c2.getValue());
        System.out.println("Count 3: " + c3.getValue());
    }

}
