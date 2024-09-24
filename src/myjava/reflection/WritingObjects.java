package myjava.reflection;

import myjava.reflection.model.Person;
import myjava.reflection.orm.EntityManger;

public class WritingObjects {

    public static void main(String[] args) {
        EntityManger<Person> em = EntityManger.of(Person.class);

        Person linda = new Person("Linda", 31);
        Person james = new Person("James", 24);
        Person susan = new Person("Susan", 34);
        Person john = new Person("John", 33);

        em.persist(linda);
        em.persist(james);
        em.persist(susan);
        em.persist(john);

        System.out.println(linda);
        System.out.println(james);
        System.out.println(susan);
        System.out.println(john);
    }

}
