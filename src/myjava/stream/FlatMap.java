package myjava.stream;

import java.util.Arrays;
import java.util.List;

public class FlatMap {

    public static void main(String[] args) {
        List<City> cities = Arrays.asList(
                new City("New York",
                        new Person("Pual", 25),
                        new Person("Sarah", 27),
                        new Person("James", 31)),
                new City("Paris",
                        new Person("Julie", 25),
                        new Person("Charlies", 22),
                        new Person("Charlotte", 31)),
                new City("London", new Person("Ann", 27),
                        new Person("Bpris", 29),
                        new Person("Emily", 34)));

        long peopleCount = cities.stream().flatMap(city -> city.getPeople().stream()).count();
        System.out.printf("Poeple count: %d\n", peopleCount);

        cities.stream().flatMap(city -> city.getPeople().stream()).map(person -> person.getName())
                .forEach(System.out::println);
    }

}
