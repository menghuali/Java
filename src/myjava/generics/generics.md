# Java Generics

## What is Java Generics?
Java Generics is a powerful feature introduced in Java 5 that enables developers to write <span style="color:red;">type-safe code</span> that can work with any object type while maintaining <span style="color:red;">compile-time type checking</span>. <span style="color:red;">Generics allow you to write classes, interfaces, and methods that can operate on objects of various types while ensuring that the code remains type-safe and reusable.</span>

### Benefits
1. **Type Safety**: Generics provide stronger type checks at compile time, ensuring that you can't insert an object of the wrong type into a collection.
2. **Code Reusability**: Generic code can be written once and used with different types, reducing duplication.
3. **Elimination of Casts**: Generics eliminate the need for casting, as the type is known at compile time.

## Rules to Define Generic Methods
* All generic method declarations have a type parameter section delimited by angle brackets (< and >) that precedes the method's return type ( < E > in the next example).
* Each type parameter section contains one or more type parameters separated by commas. A type parameter, also known as a type variable, is an identifier that specifies a generic type name.
* The type parameters can be used to declare the return type and act as placeholders for the types of the arguments passed to the generic method, which are known as *actual type arguments*.
* A generic method's body is declared like that of any other method. <span style="color:red;">Note that type parameters can represent only reference types, not primitive types (like int, double and char).</span>

Sample: [BasicGenericSample.java](./BasicGenericSample.java)

### Bounded Type Parameters
There may be times when you'll want to restrict the kinds of types that are allowed to be passed to a type parameter. For example, a method that operates on numbers might only want to accept instances of Number or its subclasses. This is what bounded type parameters are for.

To declare a bounded type parameter, list the type parameter's name, followed by the extends keyword, followed by its upper bound.
```java
 public <T extends Comparable<T>> T max(T a, T b);
```

Sample: [BoundedTypeSample.java](./BoundedTypeSample.java)

### Wildcard in Java Generics
The *?* (question mark) symbol represents the wildcard element. It means any type. If we write <? extends Number>, it means any child class of Number, e.g., Integer, Float, and double. Now we can call the method of Number class through any child class object.

We can use a wildcard as a type of a parameter, field, return type, or local variable. <span style="color:red;"> However, it is not allowed to use a wildcard as a type argument for a generic method invocation, a generic class instance creation, or a supertype.</span>

#### Upper Bounded Wildcards
The purpose of upper bounded wildcards is to decrease the restrictions on a variable. It restricts the unknown type to be a specific type or a subtype of that type. It is used by declaring wildcard character ("?") followed by the extends (in case of, class) or implements (in case of, interface) keyword, followed by its upper bound.

Syntax: `List<? extends Number>`

#### Lower Bounded Wildcards
The purpose of lower bounded wildcards is to restrict the unknown type to be a specific type or a supertype of that type. It is used by declaring wildcard character ("?") followed by the super keyword, followed by its lower bound.

Syntax: `List<? super Integer>`

### Generic Classes
A generic class declaration looks like a non-generic class declaration, except that the class name is followed by a type parameter section.

As with generic methods, the type parameter section of a generic class can have one or more type parameters separated by commas. These classes are known as parameterized classes or parameterized types because they accept one or more parameters.
```java
class Box<T> {
    private T item;

    public void set(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }
}
```

### Naming Convension of Type Parameters
**T** - Type

**E** - Element

**K** - Key

**N** - Number

**V** - Value

## [Generics and Subtyping](https://docs.oracle.com/javase/tutorial/extra/generics/subtype.html)
```java
List<String> ls = new ArrayList<String>(); // 
List<Object> lo = ls; // Compile error! List<String> is NOT subtype of List<Object>
```

However, the following code works! It's the feature *bounded wildcard*.
```java
List<String> ls = new ArrayList<>();
List<? extends Object> lo = ls;
```


## Reference
[Oracle Generics Tutorial](https://docs.oracle.com/javase/tutorial/extra/generics/index.html)

