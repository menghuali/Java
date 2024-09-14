package myjava.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class UseComparator {

    private static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "(name=" + name + ", age=" + age + ")";
        }

    }

    public static void main(String[] args) {
        List<User> users = Arrays.asList(new User("Sarah", 28), new User("James", 35), new User("Mary", 35),
                new User("John", 25), new User("John", 24));
        
        Comparator<User> cmpName = (a, b) -> a.name.compareTo(b.name);
        Comparator<User> cmpAge = (a, b) -> Integer.compare(a.age, b.age);
        users.sort(cmpName.thenComparing(cmpAge).reversed());
        System.out.println(users);
    }

}
