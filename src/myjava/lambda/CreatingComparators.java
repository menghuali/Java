package myjava.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CreatingComparators {

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight");

        // Comparator<String> alphabetic = (a, b) -> a.compareTo(b);
        strs.sort((a, b) -> a.compareTo(b));
        System.out.println(strs);

        // ToIntFunction<String> toLen = s -> s.length();
        Comparator<String> len = Comparator.comparingInt(s -> s.length());
        strs.sort(len);
        System.out.println(strs);
    }
}
