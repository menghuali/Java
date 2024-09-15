package myjava.stream;

import java.util.regex.Pattern;

public class StreamFromRegex {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(" ");
        long count = pattern.splitAsStream("the quick brown fox jumps over the lazy dog").count();
        System.err.println(count);
    }
}
