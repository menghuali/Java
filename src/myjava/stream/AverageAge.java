package myjava.stream;

import java.util.Arrays;
import java.util.List;

public class AverageAge {

    public static void main(String[] args) {
        forloop();
        stream();
    }

    private static void stream() {
        List<Person> people = Arrays.asList(
                new Person("Paul", 25),
                new Person("Sarah", 27),
                new Person("James", 31),
                new Person("Julie", 25),
                new Person("Charles", 22));

        double average = people.stream().mapToInt(p -> p.getAge()).filter(age -> age > 20).average().orElseThrow();
        System.out.println("Average: " + average);
    }

    private static void forloop() {
        List<Person> people = Arrays.asList(
                new Person("Paul", 25),
                new Person("Sarah", 27),
                new Person("James", 31),
                new Person("Julie", 25),
                new Person("Charles", 22));

        int sum = 0, count = 0;
        for (Person person : people) {
            if (person.getAge() > 20) {
                count++;
                sum += person.getAge();
            }
        }
        double average = sum / count;
        System.out.println("Average: " + average);
    }

}
