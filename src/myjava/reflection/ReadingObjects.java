package myjava.reflection;

import myjava.reflection.model.Person;
import myjava.reflection.orm.EntityManger;

public class ReadingObjects {

    public static void main(String[] args) {
        EntityManger<Person> em = EntityManger.of(Person.class);

        System.out.println(em.find(1L));
        System.out.println(em.find(2L));
        System.out.println(em.find(3L));
        System.out.println(em.find(4L));
        System.out.println(em.find(5L));
    }

}
