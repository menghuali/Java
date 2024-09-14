package myjava.lambda;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class LambdaSandbox {

    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Hello, Supplier Lambda!";
        System.err.println(supplier);
        System.out.println(supplier.get());

        Consumer<String> consumer = (s) -> {
            System.out.println(s);
            System.out.println("Second line in Lambda!");
        };
        consumer.accept("Hello, Consumer Lambda!");
    }

}
