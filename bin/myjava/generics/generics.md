# Java Generics
## Rules to Define Generic Methods
* All generic method declarations have a type parameter section delimited by angle brackets (< and >) that precedes the method's return type ( < E > in the next example).
* Each type parameter section contains one or more type parameters separated by commas. A type parameter, also known as a type variable, is an identifier that specifies a generic type name.
* The type parameters can be used to declare the return type and act as placeholders for the types of the arguments passed to the generic method, which are known as actual type arguments.
* A generic method's body is declared like that of any other method. Note that type parameters can represent only reference types, not primitive types (like int, double and char).

Sample: [BasicGenericSample.java](./BasicGenericSample.java)

### Bounded Type Parameters
There may be times when you'll want to restrict the kinds of types that are allowed to be passed to a type parameter. For example, a method that operates on numbers might only want to accept instances of Number or its subclasses. This is what bounded type parameters are for.

To declare a bounded type parameter, list the type parameter's name, followed by the extends keyword, followed by its upper bound.

Sample: [BoundedTypeSample.java](./BoundedTypeSample.java)

### Wildcard in Java Generics
The *?* (question mark) symbol represents the wildcard element. It means any type. If we write <? extends Number>, it means any child class of Number, e.g., Integer, Float, and double. Now we can call the method of Number class through any child class object.

We can use a wildcard as a type of a parameter, field, return type, or local variable. However, it is not allowed to use a wildcard as a type argument for a generic method invocation, a generic class instance creation, or a supertype.

#### Upper Bounded Wildcards
The purpose of upper bounded wildcards is to decrease the restrictions on a variable. It restricts the unknown type to be a specific type or a subtype of that type. It is used by declaring wildcard character ("?") followed by the extends (in case of, class) or implements (in case of, interface) keyword, followed by its upper bound.

Syntax: `List<? extends Number>`

#### Lower Bounded Wildcards
The purpose of lower bounded wildcards is to restrict the unknown type to be a specific type or a supertype of that type. It is used by declaring wildcard character ("?") followed by the super keyword, followed by its lower bound.

Syntax: `List<? super Integer>`

## Generic Classes
A generic class declaration looks like a non-generic class declaration, except that the class name is followed by a type parameter section.

As with generic methods, the type parameter section of a generic class can have one or more type parameters separated by commas. These classes are known as parameterized classes or parameterized types because they accept one or more parameters.

## Why Generics?
### Type-safety
We can hold only a single type of objects in generics. It doesn't allow to store other objects.

### Type casting is not required
There is no need to type-cast the object.

### Compile-Time Checking
It is checked at compile time so problem will not occur at runtime. The good programming strategy says it is far better to handle the problem at compile time than runtime.

## Naming Convension of Type Parameters
**T** - Type

**E** - Element

**K** - Key

**N** - Number

**V** - Value

## Reference
https://www.tutorialspoint.com/java/java_generics.htm

https://www.javatpoint.com/generics-in-java

