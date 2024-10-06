# Java Stream
Java Stream is a powerful feature introduced in Java 8 that allows for <span style="color:red">functional-style operations on collections of data</span>. It provides a high-level abstraction for processing sequences of elements, such as lists and arrays, in a clean and concise way. 

## Overview

**What is a Stream?**<br>
A Stream represents a sequence of elements that can be processed in a functional manner. <span style="color:red">It is not a data structure but rather a view of a data source</span>, allowing for operations to be performed without modifying the underlying data.

**Key Features of Java Streams**<br>
* **Functional Programming**: Streams promote functional programming by allowing operations such as map, filter, and reduce.
* **Lazy Evaluation**: Operations on streams are evaluated lazily. This means that <span style="color:red">computations are not performed until the result is actually needed</span>, which can improve performance.
* **Pipeline Operations**: Streams support a pipeline of operations, consisting of intermediate operations (like map, filter) and a terminal operation (like forEach, collect).
* **Parallel Processing**: Streams can be easily parallelized using the ```parallelStream()``` method, enabling concurrent processing of data.

**Stream Creation**<br>
A Stream can be created from various types of objects:
* **Collections** (like List, Set, Map)
* **Arrays** (using Arrays.stream())
* **Files** (using Files.lines())
* **I/O Channels**
* **Generated Streams** (using Stream.generate() and Stream.iterate())

**Intermediate Operations**<br>
Intermediate Operations are <span style="color:red">operations that transform a stream into another stream</span>. They are used to set up a chain of transformations on the data but do not trigger any processing or produce a result until a terminal operation is invoked. <span style="color:red">These operations are typically lazy, meaning they do not execute until necessary.</span>

Common intermediate operations include map, filter, distinct, sorted, flatMap.

**Terminal Operations**<br>
Terminal Operations are operations that produce a result or a side-effect and terminate the stream pipeline. <span style="color:red">Once a terminal operation is invoked, the stream is consumed and cannot be used again.</span> Terminal operations trigger the processing of the stream, executing any intermediate operations that have been defined. Unlike intermediate operations, <span style="color:red">terminal operations are eagerly evaluated</span>, meaning they execute and process the stream data immediately.

Common terminal operations include forEach, collect, reduce, count, sum, anyMatch.

## Create Stream
You can create a stream from arrays, collections, files, I/O channels, and other sources.

### Stream from Array
You can create a Stream from arrays using ```Arrays.stream()``` method. This works for arrays of objects, primitives, and more.

**Stream from Object Array**
```java
String[] array = {"A", "B", "C"};
Stream<String> streamFromArray = Arrays.stream(array);
```

**Stream from Primitve Array**
```java
int[] intArray = {1, 2, 3};
IntStream streamFromIntArray = Arrays.stream(intArray);
```

### Stream from Collection
Streams can be created from Java Collections (such as ```List```, ```Set```, and ```Map```). You can use the stream() method for sequential streams or parallelStream() for parallel processing.

**Stream from List**
```java
List<String> list = Arrays.asList("A", "B", "C");
Stream<String> streamFromList = list.stream();
```

**Stream from Map**
For a ```Map```, you can obtain a stream of the entries, keys, or values:
```java
Map<String, Integer> map = new HashMap<>();
Stream<Map.Entry<String, Integer>> streamFromMapEntries = map.entrySet().stream();
```

### Stream from File
Streams can be created from files using the ```Files.lines()``` method, which returns a Stream of lines from a file.

```java
Path filePath = Paths.get("example.txt");
Stream<String> streamFromFile = Files.lines(filePath);
```

### Stream from I/O Channel
Streams can also be created from I/O channels (such as network sockets) using methods that provide byte or character streams.

**Stream from System.in**
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class StreamFromSystemIn {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter input (type 'exit' to finish):");

            Stream<String> stream = reader.lines(); // Create a stream from System.in input
            stream.takeWhile(line -> !"exit".equalsIgnoreCase(line)) // Continue until "exit"
                    .forEach(System.out::println); // Print each line entered by the user

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

**Stream from Network Socket**
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.util.stream.Stream;

public class StreamFromSocket {
    public static void main(String[] args) {
        try (Socket socket = new Socket("example.com", 80); // Connect to server
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            Stream<String> stream = reader.lines(); // Create a stream from the socket input
            stream.forEach(System.out::println); // Print each line received from the server
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Stream from Other Sources
**Stream from String**
```java
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
```

**Stream from Regular Expression**
```java
import java.util.regex.Pattern;

public class StreamFromRegex {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(" ");
        long count = pattern.splitAsStream("the quick brown fox jumps over the lazy dog").count();
        System.err.println(count);
    }
}
```

## Intermediate Operations
Intermediate Operations in Java Streams are operations that transform a stream into another stream. They are used to set up a chain of transformations on the data but do not trigger any processing or produce a result until a terminal operation is invoked. These operations are typically lazy, meaning they do not execute until necessary.

### Characteristics of Intermediate Operations
* **Return a Stream**: Each intermediate operation returns a new stream, allowing for chaining multiple operations together.
* **Lazy Evaluation**: Intermediate operations are not executed immediately. Instead, they are executed only when a terminal operation is invoked.
* **Can Be Chained**: Multiple intermediate operations can be chained together, enabling a fluent programming style.
  
### Common Intermediate Operations
* ```filter(Predicate<? super T> predicate)```: Filters the elements of the stream based on a given condition. Only elements that match the predicate are included in the new stream.
    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
    Stream<String> filteredNames = names.stream().filter(name -> name.startsWith("A"));
    ```
* ```map(Function<? super T, ? extends R> mapper)```: Transforms each element of the stream into a new form based on a mapping function.
    ```java
    Stream<Integer> nameLengths = names.stream().map(String::length);
    ```
* ```distinct()```: Removes duplicate elements from the stream, ensuring all elements are unique.
    ```java
    Stream<String> distinctNames = names.stream().distinct();
    ```
* ```sorted(Comparator<? super T> comparator)```: Sorts the elements of the stream according to a specified comparator.
    ```java
    Stream<String> sortedNames = names.stream().sorted();
    ```
* ```flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)```: transform each element of a stream into another stream and then flatten the resulting streams into a single, unified stream. This operation is especially useful when working with collections of collections or when you need to process hierarchical data structures like lists of lists.. Also see [Stream flatMap() in Java with examples](https://www.geeksforgeeks.org/stream-flatmap-java-examples/).
    ```java
    import java.util.Arrays;
    import java.util.List;
    import java.util.stream.Collectors;

    public class FlatMapExample {
        public static void main(String[] args) {
            List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("a", "b", "c"),
                Arrays.asList("d", "e", "f"),
                Arrays.asList("g", "h", "i")
            );

            List<String> flattenedList = nestedList.stream()
                                                .flatMap(List::stream)  // Flattens each inner list into a single stream
                                                .collect(Collectors.toList());

            System.out.println(flattenedList);  // Output: [a, b, c, d, e, f, g, h, i]
        }
    }
    ```
* ```skip(long n)```: Returns a stream that skips the first n elements of the original stream. It effectively "drops" the initial elements and processes only the remaining elements, making it useful in situations where you want to ignore a certain number of elements from the beginning of a stream.
    ```java
    import java.util.Arrays;
    import java.util.List;

    public class StreamSkipExample {
        public static void main(String[] args) {
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

            numbers.stream()
                .skip(3)  // Skips the first 3 elements: 1, 2, and 3
                .forEach(System.out::println);  // Output: 4, 5, 6, 7
        }
    }
    ```
* ```limit(long n)```: Limits the number of elements in a stream. It returns a new stream that contains only the first maxSize elements from the original stream. If the stream has fewer elements than maxSize, the entire stream is returned.
    ```java
    import java.util.Arrays;
    import java.util.List;

    public class StreamLimitExample {
        public static void main(String[] args) {
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

            numbers.stream()
                .limit(3)  // Limits the stream to the first 3 elements
                .forEach(System.out::println);  // Output: 1, 2, 3
        }
    }
    ```
* ```takeWhile(Predicate<? super T> predicate)```: Processes elements of a stream until a given condition (predicate) is no longer satisfied. It returns a stream consisting of the longest prefix of elements that match the predicate.
    ```java
    import java.util.List;
    import java.util.stream.Stream;

    public class TakeWhileExample {
        public static void main(String[] args) {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

            numbers.stream()
                .takeWhile(n -> n < 5)  // Takes elements as long as the value is less than 5
                .forEach(System.out::println);  // Output: 1, 2, 3, 4
        }
    }
    ```
* ```dropWhile(Predicate<? super T> predicate)```: return a stream consisting of the remaining elements after dropping the longest prefix of elements that match a given predicate. It continues dropping elements as long as the predicate evaluates to true and stops when it encounters the first element for which the predicate evaluates to false. The remaining elements are returned in the resulting stream, including the element that caused the predicate to fail.
```java
import java.util.List;

public class DropWhileExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        numbers.stream()
               .dropWhile(n -> n < 5)  // Drops elements while they are less than 5
               .forEach(System.out::println);  // Output: 5, 6, 7, 8, 9
    }
}
```

## Terminal Operations
Terminal Operations in Java Streams are operations that produce a result or a side-effect and terminate the stream pipeline. Once a terminal operation is invoked, the stream is consumed and cannot be used again. Terminal operations trigger the processing of the stream, executing any intermediate operations that have been defined.

### Characteristics of Terminal Operations
* **Produce a Result**: Terminal operations produce a final result, such as a collection, a primitive value, or a side effect (like printing to the console).
* **Terminate the Stream**: Once a terminal operation is executed, the stream pipeline is complete, and the stream cannot be reused.
* **Eager Evaluation**: Unlike intermediate operations, terminal operations are eagerly evaluated, meaning they execute and process the stream data immediately.

### Common Terminal Operations
* forEach(Consumer<? super T> action): Performs an action for each element of the stream. This is often used for side-effects.
    ```java
    names.stream().forEach(System.out::println);
    ```
* collect(Collector<? super T, A, R> collector): Accumulates the elements of the stream into a collection, such as a List, Set, or Map. It’s one of the most commonly used terminal operations.
    ```java
    List<String> list = names.stream().collect(Collectors.toList()); // Collect to a List
    Set<String> set = names.stream().collect(Collectors.toSet()); // Collect to a Set
    String[] array = names.stream().collect(String[]::new); // Collect to an array
    String join = names.stream().collect(Collectors.joining(", ")); // Join with delimiter
    String join2 = names.stream().collect(Collectors.joining(", ", "[", "]")); // Join with delimiter, prefix, and suffix
    ```
* ````reduce(T identity, BinaryOperator<T> accumulator)````: Performs a reduction on the elements of the stream using an associative accumulation function and returns an Optional.
    ```java
    Optional<String> concatenated = names.stream().reduce((s1, s2) -> s1 + s2);
    ```
* ```count()```: Counts the number of elements in the stream and returns a long value.
    ```java
    long count = names.stream().count();
    ```
* ```anyMatch(Predicate<? super T> predicate)```: Checks if any elements of the stream match the given predicate.
    ```java
    boolean hasAlice = names.stream().anyMatch(name -> name.equals("Alice"));
    ```
* ```allMatch(Predicate<? super T> predicate)```: Checks if all elements match the given predicate.
    ```java
    boolean allStartWithA = names.stream().allMatch(name -> name.startsWith("A"));
    ```

### Reduce, Identity Element, and ```Optional<T>```
The identity element is a special initial value that represents a neutral element for the reduction operation. It is used as the starting point in the reduction process.

Key Characteristics of the Identity Element:
* Neutral Element: <span style="color:red">The identity element is neutral in the sense that combining it with any element from the stream using the reduction operation leaves the element unchanged.</span> For example, 0 is the identity for addition because 0 + x = x, and 1 is the identity for multiplication because 1 * x = x.
* Initial Value: The identity element is the initial result when the reduction operation starts. <span style="color:red">If the stream is empty, the identity element is returned as the result.</span>
* Associativity: <span style="color:red">The reduction operation must be associative, meaning the order in which operations are performed does not change the result.</span> This ensures that the identity element can be applied at any stage without affecting the final result.
* Parallel Streams: The identity element ensures correctness in parallel stream operations, as intermediate results from different threads can safely start from the identity value without affecting the final result.

#### Syntax of ```reduce()``` with an Identity Element
```java
T reduce(T identity, BinaryOperator<T> accumulator)
```

**Examples**:<br>
```java
// Example of IntStream
int[] array = new int[] { 1, 1, 1, 1, 1 };
Arrays.stream(array).reduce(0, (a, b) -> a + b);
```

```java
// Example of object stream
import java.util.List;

public class ReduceStringExample {
    public static void main(String[] args) {
        List<String> words = List.of("apple", "banana", "cherry");

        // Reduce the stream with the identity of an empty string ("")
        String concatenated = words.stream().reduce("", (a, b) -> a + b);
        System.out.println(concatenated);  // Output: applebananacherry
    }
}
```

#### ```Optional<T>```
If the stream is empty and an identity element is not provided, there may be no result to return from the reduction operation. The reduce() method in this case returns an Optional to indicate the absence of a value safely, avoiding the potential of a NoSuchElementException or NullPointerException. Following is the syntax.
```java
Optional<T> reduce(BinaryOperator<T> accumulator)
```

**Checking for a Result**:<br>
Since the reduction might not yield a result (e.g., an empty stream), the Optional allows the developer to check whether a value is present, making the code safer and more readable. For example:
* ```boolean isPresent()```: Check if a value is present.
* ```void ifPresent(Consumer<? super T> action)```: If a value is present, performs the given action with the value, otherwise does nothing.
* ```T orElse(T other)```: If a value is present, returns the value, otherwise returns other.
* ```T orElseThrow()```: If a value is present, returns the value, otherwise returns the result produced by the supplying function.

For detail documents, see [java.util.Optional](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html).

### Stream.groupingBy()
The Stream.groupingBy() method in Java is a collector that groups elements of a stream by a specified classifier function. This collector is part of the Collectors utility class in the java.util.stream package and is used to group data into a Map where the keys represent the group categories and the values represent lists of stream elements corresponding to each category.

**Syntax**<br>
```java
Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier)
```
* classifier: A function that takes an element from the stream and maps it to a key.
* T: The type of elements in the input stream.
* K: The type of keys in the resulting map.

**Example**:<br>
```java
import java.util.*;
import java.util.stream.*;

public class GroupingByExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        Map<Integer, List<String>> result = words.stream()
                                                 .collect(Collectors.groupingBy(String::length));

        System.out.println(result);  // Output: {5=[apple], 6=[banana, cherry], 4=[date], 10=[elderberry]}
    }
}
```

#### Downstream Collectors
Downstream collectors refer to secondary collectors used within the groupingBy() operation to further collect, transform, or reduce the values associated with each group. <span style="color:red">Instead of just collecting values into lists (the default behavior), you can use downstream collectors to apply additional operations like counting, reducing, or collecting into other structures.</span> See the following examples:<br>
```java
import java.util.*;
import java.util.stream.*;

public class GroupingWithCounting {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        Map<Integer, Long> result = words.stream()
                                         .collect(Collectors.groupingBy(String::length, Collectors.counting()));

        System.out.println(result);  // Output: {5=1, 6=2, 4=1, 10=1}
    }
}
```

```java
import java.util.*;
import java.util.stream.*;

public class GroupingWithMaxBy {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        Map<Integer, Optional<String>> result = words.stream()
                                                     .collect(Collectors.groupingBy(
                                                         String::length,
                                                         Collectors.maxBy(Comparator.comparingInt(String::length))
                                                     ));

        System.out.println(result);  // Output: {5=Optional[apple], 6=Optional[banana], 4=Optional[date], 10=Optional[elderberry]}
    }
}
```

**Common Downstream Collectors**:
* ```Collectors.counting()```: Counts the number of elements in each group.
* ```Collectors.summingInt()```: Sums the elements of each group based on an integer mapping function.
* ```Collectors.mapping()```: Transforms elements of each group using a mapping function.
* ```Collectors.collectingAndThen()```: Applies another collector and then performs a finishing transformation.
* ```Collectors.toSet()```: Collects elements of each group into a Set.
* ```Collectors.joining()```: Joins elements of each group into a single string.

## Reference
* [Oralce Package java.util.stream](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)
* [Oracle Stream API Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)