package myjava.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DropWhileTakeWhile {

    public static void main(String[] args) {
        Class<?> clzz = ArrayList.class;
        Stream.<Class<?>>iterate(clzz, c -> c.getSuperclass())
                .takeWhile(c -> c != null)
                .forEach(System.out::println);

        Stream<Integer> stream1 = Stream.of(4, 4, 4, 5, 6, 7, 8, 5, 9, 10);
        List<Integer> list1 = stream1.takeWhile(c -> c / 4 == 1).collect(Collectors.toList());
        System.out.println(list1);

        Stream<Integer> stream2 = Stream.of(4, 4, 4, 5, 6, 7, 8, 5, 9, 10);
        List<Integer> list2 = stream2.dropWhile(c -> c / 4 == 1).collect(Collectors.toList());
        System.out.println(list2);
    }

}
