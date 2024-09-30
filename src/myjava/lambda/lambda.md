# Java Lambda
## What are Lambda Expressions?
A Lambda Expression in Java is a concise way to represent an anonymous function—essentially a method that does not belong to any class. Lambda expressions were introduced in Java 8 as part of the Java Streams API and functional programming enhancements. They allow you to write code in a more functional style by passing behavior (such as a block of code) as parameters to methods.

**Notes:**
* A Lambda expression implements a function inteface.
* A Lambda expresson is not an anonymous class implementation.

### Lambda Expression Syntax
The basic syntax of a lambda expression is:
```java
(parameters) -> expression
```

Or, if the body contains multiple statements:
```java
(parameters) -> { statements; }
```

#### Key Components
1. Parameters: The input to the lambda function.
2. Arrow Token (->): Separates the parameter list from the body of the expression.
3. Expression/Block of Code: The body of the lambda, which can be a single expression or a block of statements.

#### Examples
```java
Comparator<Integer> cmp = (a, b) -> Integer.compare(a, b);
int compared = cmp.compare(10, 20);
```

### Functional Interfaces
<span style="color:red;">Lambda expressions can only be used where there is a functional interface—an interface with a single abstract method.</span> Some common functional interfaces in Java are:
* Runnable (no parameters, no return value)
* Callable<V> (no parameters, returns a value)
* Comparator<T> (two parameters, returns an int)
* Function<T, R> (one parameter, returns a value)

Functional interfaces are in package [java.util.function](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/package-summary.html1). It has about 40 interfaces.

Functional interfaces can be organized in 4 categories: Supplier, Consumer, Predicate, Function

Functional annotation is not mandatory to using Lambdas. It make you can use Lambda with old Java interfaces, such as Runnable.

### Benefits
* **Conciseness**: Lambdas reduce boilerplate code by eliminating the need for anonymous inner classes.
* **Readability**: For many use cases, lambda expressions make the code easier to read and understand.
* **Functional Programming**: Lambdas bring functional programming concepts to Java, allowing for operations like filtering, mapping, and reducing over collections.

## Combine Lambdas
You can combine multiple lambda expressions by using methods such as `andThen()`, `compose()`, or logical operations like `and()` and `or()` depending on the functional interface you are working with. The most common ways to combine lambdas are when using `Function<T, R>`, `Predicate<T>`, and `Consumer<T>` functional interfaces.
1. Combining `Function<T, R>` Lambdas
A `Function<T, R>` represents a function that takes a value of type `T` and returns a value of type `R`. You can combine multiple Function lambdas using the `andThen()` and `compose()` methods.
   * `andThen()`: Executes the first function and then applies the second function to the result.
   * `compose()`: Executes the second function first and then applies the first function to the result.

    **Example**<br>
    ```java
    Function<Integer, Integer> doubleIt = x -> x * 2;
    Function<Integer, Integer> addFive = x -> x + 5;

    Function<Integer, Integer> combined = doubleIt.andThen(addFive);
    System.out.println(combined.apply(3)); // Output: 11

    Function<Integer, Integer> reverseOrder = doubleIt.compose(addFive);
    System.out.println(reverseOrder.apply(3)); // Output: 16
    ```
    In this example, andThen() first doubles the value, then adds 5, while compose() first adds 5, then doubles the result.

2. Combining `Predicate<T>` Lambdas
A `Predicate<T>` is a functional interface that takes a value of type `T` and returns a boolean. You can combine multiple Predicate lambdas using the logical operators:
   * `and()`: Returns true if both predicates are true.
   * `or()`: Returns true if at least one predicate is true.
   * `negate()`: Reverses the result of the predicate.

    **Example**
    ```java
    Predicate<Integer> isEven = x -> x % 2 == 0;
    Predicate<Integer> isPositive = x -> x > 0;

    Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
    System.out.println(isEvenAndPositive.test(4));  // Output: true
    System.out.println(isEvenAndPositive.test(-4)); // Output: false

    Predicate<Integer> isEvenOrPositive = isEven.or(isPositive);
    System.out.println(isEvenOrPositive.test(-2));  // Output: true
    ```

3. Combining Consumer<T> Lambdas
A Consumer<T> takes a value of type T and performs some action without returning anything. You can combine multiple Consumer lambdas using the andThen() method.

**Example**
```java
Consumer<String> greet = name -> System.out.println("Hello, " + name);
Consumer<String> askHowAreYou = name -> System.out.println("How are you, " + name + "?");

Consumer<String> combinedConsumer = greet.andThen(askHowAreYou);
combinedConsumer.accept("Alice");
// Output:
// Hello, Alice
// How are you, Alice?
```

4. Combining BinaryOperator<T> Lambdas
A BinaryOperator<T> is a special type of BiFunction that takes two operands of the same type and returns a result of the same type. You can combine binary operators similarly to functions.

**Example**
```java
BinaryOperator<Integer> add = (a, b) -> a + b;
BinaryOperator<Integer> multiply = (a, b) -> a * b;

BinaryOperator<Integer> combined = (a, b) -> add.apply(a, b) * multiply.apply(a, b);
System.out.println(combined.apply(2, 3)); // Output: (2 + 3) * (2 * 3) = 5 * 6 = 30
```
### Conclusion
By using methods like andThen(), compose(), and(), or(), and negate(), you can easily combine multiple lambda expressions in Java. This allows you to chain multiple operations together in a concise and expressive way, improving the flexibility of your functional programming.

## Auto-boxing
Auto-boxing in Java is the automatic conversion between primitive types (e.g., int, double, boolean) and their corresponding wrapper classes (Integer, Double, Boolean, etc.). This feature was introduced in Java 5 to allow primitives to be used in places where objects are expected, like collections (List, Map, etc.), without requiring explicit conversion.

**Example of Auto-boxing**
```java
List<Integer> list = new ArrayList<>();
list.add(5); // auto-boxing from int to Integer
int num = list.get(0); // auto-unboxing from Integer to int
```
Here, the primitive int is automatically converted to its wrapper type Integer when added to the List, and then automatically converted back to int when retrieved.

### Avoid Auto-boxing in Lambdas
While auto-boxing is convenient, it introduces performance overhead because:
* **Object Creation**: Each auto-boxed primitive creates a new object (e.g., boxing an int into an Integer), which increases memory usage.
* **Garbage Collection**: The extra objects created during auto-boxing need to be garbage-collected, adding further overhead to the JVM.
* **Performance Penalty**: Repeated boxing and unboxing operations in performance-critical code, such as in tight loops or lambda expressions (which may be called frequently), can degrade performance.

In lambda expressions, where performance and conciseness are critical, auto-boxing can add unnecessary inefficiency. Since lambda expressions are often used in stream operations and functional programming patterns (e.g., map, filter), these additional object creations and conversions can slow down execution, especially when dealing with large data sets.

**Example in Lambdas**<br>
Consider the following lambda expression that involves auto-boxing:

```java
List<Integer> numbers = List.of(1, 2, 3);
numbers.stream()
       .map(n -> n * 2) // auto-boxing of int to Integer
       .forEach(System.out::println);
```
In this case, n is auto-boxed from int to Integer, leading to extra object creation. If the code involves a large collection or is part of a performance-sensitive operation, this can have a significant impact.

### How to Avoid Auto-boxing?
To avoid auto-boxing in lambdas, you can use primitive streams (like IntStream, DoubleStream, etc.) instead of regular streams (Stream<T>), as primitive streams operate directly on primitive types and avoid unnecessary boxing/unboxing.

**Example without Auto-boxing**<br>
```java
IntStream.of(1, 2, 3)
         .map(n -> n * 2) // No auto-boxing, primitive int
         .forEach(System.out::println);
```
By using IntStream instead of Stream<Integer>, no boxing is required, making the code more efficient.

### Conclusion
Auto-boxing simplifies code by automatically converting between primitives and their wrapper classes, but it introduces performance costs due to object creation and garbage collection. When using lambdas, especially in performance-critical areas, auto-boxing should be avoided by using primitive streams (IntStream, LongStream, DoubleStream) to ensure efficient execution.

## Performance of Lambda Expressions
The performance of lambda expressions in Java can be comparable to, or in some cases slower than, traditional Java code. However, <span style="color:red;">the performance differences are often minimal</span> and depend on factors like how the lambda is used, the underlying implementation, and the JVM optimizations.

### Factors Affecting Performance
1. **Lambda Implementation in Java**: Java lambdas are implemented using *invokedynamic* bytecode instruction, which relies on LambdaMetafactory to dynamically generate the required code at runtime. This approach allows lambdas to be as efficient as possible but introduces some overhead during the first invocation due to the dynamic nature of the lambda creation.
   * **First Invocation Overhead**: When a lambda expression is used for the first time, the JVM generates a method (or uses a cached one) to execute the lambda. This causes a slight delay during the first execution, but <span style="color:red;">once the lambda is compiled, future invocations are very fast</span>.
2. **Anonymous Classes vs. Lambdas**: In traditional Java code, anonymous inner classes were often used to pass behavior. Lambdas, in contrast, offer better performance because they avoid the creation of a separate inner class and can be optimized by the JVM at runtime.

For example, this traditional Java code:

```java
new Thread(new Runnable() {
    public void run() {
        System.out.println("Traditional code");
    }
}).start();
```

Is equivalent to this lambda expression:
```java
new Thread(() -> System.out.println("Lambda code")).start();
```

The lambda version avoids the overhead of creating an anonymous inner class instance, making it potentially more efficient.

3. **JVM Optimizations**: The JVM is optimized for functional-style programming, and many lambda-related performance improvements are applied automatically by the Just-In-Time (JIT) compiler. This means that after the initial warm-up phase, lambdas may perform similarly to or better than traditional code, especially when used in scenarios like stream processing.
4. **Memory Usage**: Lambdas do not require creating an object like anonymous classes do, which leads to a lower memory footprint. However, the memory savings are not always substantial unless there are many instances of lambdas being created and discarded.
5. **Stream Operations**: Java lambdas are often used with the Stream API, which is optimized for parallelism and functional-style operations (like map, filter, and reduce). <span style="color:red;">The stream processing capabilities of lambdas can be highly performant</span>, especially with parallel streams**, although in some cases it might add a slight overhead due to additional computations and context-switching between threads.

### Performance Comparison
1. **Short-Term Usage**: For short, simple tasks, traditional Java approaches (like anonymous classes or for-loops) might be marginally faster due to the lack of dynamic method generation involved with lambdas.
2. **Long-Term Usage**: Over the course of a longer-running application, the JIT compiler's optimizations generally make lambdas as fast as, if not faster than, traditional code.
3. **Parallel Processing**: <span style="color:red;">The Stream API, when used with lambdas, can offer significant performance benefits in parallel processing tasks, as lambdas can be executed concurrently, utilizing multiple CPU cores.</span>

**Benchmark Example:**<br>
In a common benchmark comparison between traditional for-loops and lambdas, lambdas perform similarly or slightly worse in cases where no parallelism is involved. However, when parallel streams are leveraged, lambdas can outperform traditional loops significantly in terms of execution speed for large datasets.

### Summary of Performance
* Initial Overhead: Lambdas have a slight performance overhead during their first invocation due to dynamic method generation.
* Memory Efficiency: Lambdas are typically more memory-efficient compared to anonymous inner classes.
* Runtime Performance: Once warmed up, lambdas can perform on par with or even outperform traditional code, especially in the context of parallel processing with streams.
* Stream Operations: For operations like filtering or mapping large datasets, lambdas combined with the Stream API can lead to performance gains, particularly when parallelized.

### Conclusion
While there can be minor performance differences between lambdas and traditional Java code, these differences are often outweighed by the improved readability and reduced boilerplate that lambdas provide. For most practical applications, the performance of lambdas is sufficiently fast, especially after JVM optimizations.

## References
* [Oracle Lambda Expression Guide](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
* [Java Auto-boxing and Unboxing](https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html)
* [Understanding Java method invocation with invokedynamic](https://blogs.oracle.com/javamagazine/post/understanding-java-method-invocation-with-invokedynamic)