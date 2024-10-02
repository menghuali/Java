package myjava.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Sandbox {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        List<? extends Object> lo = ls;

        Collection<?> c = new ArrayList<String>();
        // c.add(new Object()); // Compile error!

        List<? extends Number> numbers = new ArrayList<Integer>();
        // numbers.add(Integer.valueOf(1)); // Compile Error
        // numbers.add((Number) Integer.valueOf(1)); // Compile Error

        List<Number> numbers2 = new ArrayList<>();
        numbers2.add(1);

        InnerClass<Integer> innerClass = new InnerClass<>();
        innerClass.element = 1;

        InnerClass2<Number> innerClass2 = new InnerClass2<>();
        innerClass2.containsAll(new ArrayList<Integer>());
        innerClass2.addAll(new ArrayList<Integer>());
    }

    @SuppressWarnings("unused")
    private static class InnerClass<T extends Number> {
        private T element;
    }

    private static class InnerClass2<E> {
        private List<E> elements = new ArrayList<>();

        public boolean containsAll(Collection<? extends E> c) {
            return elements.containsAll(c);
        }

        public boolean addAll(Collection<? extends E> c) {
            return elements.addAll(c);
        }
    }

}
