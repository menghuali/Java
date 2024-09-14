package myjava.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SomeMapping {

    public static void main(String[] args) {
        List<String> uppers = new ArrayList<>(List.of("A", "B", "C"));
        Function<String, String> lower = (s) -> s.toLowerCase(); // Function lambda
        List<String> lowers = new ArrayList<>();
        for (String upper : uppers) {
            lowers.add(lower.apply(upper));
        }
        lowers.forEach(System.out::println); // Consumer lambda
    }

}
