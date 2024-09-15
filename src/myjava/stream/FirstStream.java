package myjava.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FirstStream {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Pual", 25),
                new Person("Sarah", 27),
                new Person("James", 31),
                new Person("Julie", 25),
                new Person("Charlies", 22),
                new Person("Charlotte", 31),
                new Person("Ann", 27),
                new Person("Bpris", 29),
                new Person("Emily", 34),
                new Person("", 0));

        Stream<Person> personStream = people.stream();
        Stream<String> nameStream = personStream.map(p -> p.getName());
        Stream<String> emptyNameStream = nameStream.filter(name -> name.isEmpty());
        long emptyNameCount = emptyNameStream.count();
        System.out.printf("Empty name count: %d\n", emptyNameCount);

        try {
            // The following line will cause exception because stream has already been
            // operated or closed.
            Stream<String> nonEmptyNameStream = nameStream.filter(name -> !name.isEmpty());
            long nonEmptyNameCount = nonEmptyNameStream.count();
            System.out.printf("Non empty name count: %d\n", nonEmptyNameCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Right way to operate second stream.
        System.out.printf("Non empty name count: %d\n",
                people.stream().map(person -> person.getName()).filter(name -> !name.isEmpty()).count());
    }

}
