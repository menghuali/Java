package myjava.generics;

public class BoundedTypeSample {

    private static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static void main(String[] args) {
        System.out.println(max(1, 2));
        System.out.println(max('a', 'b'));
        System.err.println(max("abc", "abd"));
    }

}
