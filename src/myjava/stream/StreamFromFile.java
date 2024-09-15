package myjava.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class StreamFromFile {

    public static void main(String[] args) {
        Path path = Path.of("src/myjava/stream/names.txt");
        
        try (Stream<String> lines = Files.lines(path)) {
            System.out.println("Count: " + lines.count());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
