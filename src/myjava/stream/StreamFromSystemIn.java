package myjava.stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class StreamFromSystemIn {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter input (type 'exit' to finish):");

            Stream<String> stream = reader.lines(); // Create a stream from System.in input
            stream.takeWhile(line -> !"exit".equalsIgnoreCase(line)) // Continue until "exit"
                    .forEach(System.out::println); // Print each line entered by the user

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
