package myjava.lambda;

import java.util.ArrayList;
import java.util.List;

public class MoreLambdas {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(List.of("one", "two", "three", "four", "five"));
        strings.removeIf((s) -> s.startsWith("t")); // Predict lambda
        System.out.println(strings);
        strings.forEach((s) -> System.out.println(s)); // Consumer lambda
    }

}
