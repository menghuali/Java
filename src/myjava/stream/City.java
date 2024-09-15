package myjava.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class City {

    private String name;
    private List<Person> people = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }

    public City(String name, Person... people) {
        this.name = name;
        if (people != null && people.length > 0)
            this.people = Arrays.asList(people);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "City [name=" + name + ", people=" + people + "]";
    }

}
