package myjava.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamFromArray {

    public static void main(String[] args) {
        Person[] people = new Person[] {
                new Person("Pual", 25),
                new Person("Sarah", 27),
                new Person("James", 31),
                new Person("Julie", 25),
                new Person("Charlies", 22),
                new Person("Charlotte", 31),
                new Person("Ann", 27),
                new Person("Bpris", 29),
                new Person("Emily", 34)};

        System.out.printf("Count of people: %d\n", Stream.of(people).count());
        Arrays.stream(people).map(person -> person.getName()).forEach(System.out::println);
    }

}
