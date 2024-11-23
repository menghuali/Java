package myjava.concurrency.m04;

public class SingletonDemo {

    public static void main(String[] args) {
        Singleton singleton = Singleton.INSTANCE;
        singleton.method();
    }

}
