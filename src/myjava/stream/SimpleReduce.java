package myjava.stream;

import java.util.Arrays;
import java.util.OptionalInt;

public class SimpleReduce {

    public static void main(String[] args) {
        int[] array = new int[] { 1, 1, 1, 1, 1 };
        OptionalInt sum = Arrays.stream(array).reduce((a, b) -> a + b);
        System.out.println(sum.getAsInt());

        sum = Arrays.stream(new int[] {}).reduce((a, b) -> a + b); // no identiy
        try {
            System.out.println(sum.getAsInt());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.stream(new int[] {}).reduce(0, (a, b) -> a + b)); // proivde identity.
        // proivde identity. so result is idenity + sum of stream.
        System.out.println(Arrays.stream(array).reduce(10, (a, b) -> a + b));

        
    }

}
