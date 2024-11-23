package myjava.concurrency.m01;

public class DeadLock {

    private static class A {

        private Object key1 = new Object();
        private Object key2 = new Object();

        public void a() {
            synchronized (key1) {
                System.out.println(String.format("[%s] I am in a()", Thread.currentThread().getName()));
                b();
            }
        }

        public void b() {
            synchronized (key2) {
                System.out.println(String.format("[%s] I am in b()", Thread.currentThread().getName()));
                c();
            }
        }

        public void c() {
            synchronized (key1) {
                System.out.println(String.format("[%s] I am in c()", Thread.currentThread().getName()));
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        Thread t1 = new Thread(() -> a.a());
        Thread t2 = new Thread(() -> a.b());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.err.println("Exit Deadlock");
    }

}
