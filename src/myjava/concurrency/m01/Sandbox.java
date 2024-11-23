package myjava.concurrency.m01;

public class Sandbox {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("I'm running in " + Thread.currentThread().getName());
        new Thread(runnable).start(); // Start a thread

        Thread thread = new Thread(runnable);
        thread.setName("MY THREAD"); // Give the thread a name.
        thread.start();

        thread.run(); // DO NOT use run(). Because instead of creating a new thread, it's main thread
                      // execute the run() method of runnable instance.

        try {
            thread.start();
        } catch (Exception e) {
            System.out.print("You can not start a thread instance twice: ");
            e.printStackTrace();
        }
        
    }

}
