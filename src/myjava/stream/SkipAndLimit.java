package myjava.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SkipAndLimit {
    public static void main(String[] args) {
        IntStream
                .range(0, 30)
                .skip(10) // skip the first 10 elements
                .limit(10) // maximum elements to be processed
                .forEach(num -> System.out.printf("%d ", num));

        System.out.println();
        try (Stream<String> lines = Files.lines(Path.of("src/myjava/stream/names.txt"))) {
            lines.skip(10).limit(5).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
