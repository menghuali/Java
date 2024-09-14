package myjava.lambda;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ChainLambda {

    public static void main(String[] args) {
        Consumer<String> c1 = s -> System.out.println("C1: " + s);
        Consumer<String> c2 = s -> System.out.println("C2: " + s);
        Consumer<String> c3 = s -> {
            c1.accept(s);
            c2.accept(s);
        };
        c3.accept("Hello!");

        Consumer<String> c4 = c1.andThen(c2);
        c4.accept("Hi!");

        Predicate<String> isNull = s -> s == null;
        Predicate<String> isEmpty = s -> s.isEmpty();
        Predicate<String> isNotNullOrEmpty = isNull.negate().and(isEmpty.negate());
        System.out.println("null: " + isNotNullOrEmpty.test(null));
        System.out.println("empty: " + isNotNullOrEmpty.test(""));
        System.out.println("hello: " + isNotNullOrEmpty.test("hello"));
    }

}
