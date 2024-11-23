package myjava.concurrency.m01;

public class Counter {

    private long count = 0;

    public void increase() {
        count = count + 1;
    }

    public void selfIncrease() {
        count++;
    }

    public synchronized void syncIncrease() {
        count = count + 1;
    }

    public long getValue() {
        return count;
    }

}
