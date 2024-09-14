package myjava.generics;

public class BasicGenericSample {

    private static <E> void pringArray(E[] array) {
        for (E e : array) {
            System.out.printf("%s, ", e);
        }
        System.err.println();
    }

    public static void main(String[] args) {
        pringArray(new Integer[] { 1, 2, 3 });
        pringArray(new String[] { "A", "B", "C" });
        pringArray(new Double[] { 1.0, 2.0, 3.0 });
        pringArray(new Character[] { 'a', 'b', 'c' });
    }

}
