package myjava.stream;

public class StreamFromString {

    public static void main(String[] args) {
        "the quick brown fox jumps over the lazy dog"
                .chars()
                .mapToObj(codePoint -> Character.toString(codePoint))
                .distinct()
                .filter(letter -> !letter.equals(" "))
                .sorted()
                .forEach(System.out::print);
    }

}
