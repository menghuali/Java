# Java Reflection
## What is Java Reflection
Java Reflection API allows for introspection and modification of classes, methods, fields, and constructors at runtime. It provides tools to dynamically examine or modify the behavior of applications during runtime, bypassing typical compile-time constraints.

**Key Use Cases**
1. Runtime Introspection: Allows querying the structure of classes, methods, fields, and constructors at runtime.
2. Dynamic Invocation: Supports calling methods, accessing fields, or instantiating objects dynamically.
3. Frameworks and Libraries: Widely used in Java frameworks like Spring, Hibernate, and testing libraries like JUnit to perform operations without knowing the structure at compile-time.

**Key Classes of Relection API**
* Class
* Field
* Method
* Constructor
* Annotation

## Class
Represents a class or interface in Java. 

**How to get Class**
You can obtian a Class object through the followong methods:
* From known class
   ```java
   Class<?> clss = String.class;
   ```
* From object
   ```java
   String obj = "Hello";
   Class<?> clss = obj.getClass();
   ```
* From class name
   ```java
   Class<?> clss = Class.forName("java.lang.String");
   ```

Through a Class object, you can get the following objects:
* Fields
* Method
* Constructor
* Annotation
* Super classes
* Interfaces implemented

## Fields
Represents a field of a class. Using reflection, fields can be read or modified, even if they are private.

### Get Field object
You get Field objects using one of the following methods of a Class object:
* ```Field getDeclaredField(String name)```: Returns a Field object that reflects the specified declared field of the class or interface represented by this Class object.
* ```Field[] getDeclaredFields()```: Returns an array of Field objects reflecting all the fields declared by the class or interface represented by this Class object.
* ```Field getField(String name)```: Returns a Field object that reflects the specified public member field of the class or interface represented by this Class object.
* ```Field[] getFields()```: Returns an array containing Field objects reflecting all the accessible public fields of the class or interface represented by this Class object.

In general, *declared field* are the fields declared in the class; *field* are the public field of the class, superclass, and interfaces.

### Set Field
1. Get the Field object
2. Make the fied accessible
3. Set field to a value
```java
Person obj; // Object
...
Class<?> clss = Person.class; // Get class of the object
Field field = clss.getDeclaredField("name"); // Get field by name.
field.setAccessible(true); // Make field accessible.
field.set(obj, "Peter"); // Set field to a value.
```

### Read Field
1. Get the Field object
2. Make the fied accessible
3. Read field to a value
```java
Person obj = new Person(); // Object
obj.setName("Peter");

Class<?> clss = obj.getClass(); // Get class
Field field = clss.getDeclaredField("name"); // Get field
field.setAccessible(true); // Make field accessible
Object value = field.get(obj); // Get value from the object
System.out.println(value);
```

## Method
Represents a method of a class. It provides the ability to invoke a method dynamically.

### Get Method object
You get Method objects using one of the following methods of a Class object:
* ```Method getDeclaredMethod(String name, Class<?>... parameterTypes)```: Returns a Method object that reflects the specified declared method of the class or interface represented by this Class object.
* ```Method[] getDeclaredMethods()```: Returns an array containing Method objects reflecting all the declared methods of the class or interface represented by this Class object, including public, protected, default (package) access, and private methods, but excluding inherited methods.
* ```Method getMethod(String name, Class<?>... parameterTypes)```: Returns a Method object that reflects the specified public member method of the class or interface represented by this Class object
* ```Method[] getMethods()```: Returns an array containing Method objects reflecting all the public methods of the class or interface represented by this Class object, including those declared by the class or interface and those inherited from superclasses and superinterfaces.

In general, *declared method* are the methods declared in the class; *methods* are the public methods of the class, superclass, and interfaces.

### Invoke no-argument Method
```java
Class<?> clss = Person.class;
Method method = clss.getDeclaredMethod("toString");
Person obj = new Person("Peter", 23);
method.invoke(obj);
```

### Invoke Method requires arguments
```java
Class<?> clss = Person.class;
Method method = clss.getDeclaredMethod("setName", String.class);
Person obj = new Person();
method.invoke(obj, "Peter");
System.out.println(obj);
```

#### Invoke static Method
```java
Class<?> clss = Person.class;
Method method = clss.getDeclaredMethod("of", String.class, int.class);
Object result = method.invoke(null, "Peter", 23); // Object parameter is null
System.out.println(result);
```

## Constructor
Represents a constructor of a class. You can create new instances of a class dynamically using constructors.

### Get Constructor object
* ```Constructor<T> getConstructor(Class<?>... parameterTypes)```: Returns a Constructor object that reflects the specified public constructor of the class represented by this Class object
* ```Constructor<?>[] getConstructors()```: Returns an array containing Constructor objects reflecting all the public constructors of the class represented by this Class object.
* ```Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)```: Returns a Constructor object that reflects the specified constructor of the class or interface represented by this Class object.
* ```Constructor<?>[] getDeclaredConstructors()```: Returns an array of Constructor objects reflecting all the constructors declared by the class represented by this Class object.

### Use Constructor
#### No Arguments Constructor
```java
Class<?> clss = Person.class;
Constructor<?> constructor = clss.getDeclaredConstructor();
Object obj = constructor.newInstance();
System.out.println(obj);
```

#### Constructor requires arguments
```java
Class<?> clss = Person.class;
Constructor<?> constructor = clss.getDeclaredConstructor(String.class, int.class);
Object obj = constructor.newInstance("Peter", 23);
System.out.println(obj);
```

## Modifier
In Java Reflection, the ```Modifier``` class is used to inspect and determine the access level (such as public, private, protected) or other modifiers (like static, final, abstract) of classes, methods, fields, and constructors.

### Key Features of the Modifier Class
* Static Methods: The Modifier class provides static methods to test various types of modifiers (e.g., isPublic(), isPrivate(), isStatic(), etc.).
* Access Modifier Constants: It defines constants that represent Java modifiers (like Modifier.PUBLIC, Modifier.PRIVATE, Modifier.STATIC).

### Common Methods in the Modifier Class
* ```isPublic(int mod)```: Checks if the given modifier is public.
* ```isPrivate(int mod)```: Checks if the given modifier is private.
* ```isProtected(int mod)```: Checks if the given modifier is protected.
* ```isStatic(int mod)```: Checks if the given modifier is static.
* ```isFinal(int mod)```: Checks if the given modifier is final.
* ```isAbstract(int mod)```: Checks if the given modifier is abstract.

### How to use
1. Get the modifies from a Reflection object, e.g. a Field object.
2. Call Modifier methods with the modifies to inspect the Reflection object. 

**Example**
```java
import java.lang.reflect.*;

public class ModifierExample {
    public static void main(String[] args) throws NoSuchFieldException {
Class<?> clazz = SampleClass.class;

// Inspect a field
Field field = clazz.getDeclaredField("name");

int modifiers = field.getModifiers();  // Get modifiers of the field
System.out.println("Is name public? " + Modifier.isPublic(modifiers));
System.out.println("Is name private? " + Modifier.isPrivate(modifiers));
System.out.println("Is name static? " + Modifier.isStatic(modifiers));
    }
}

class SampleClass {
    private String name;
    public static int age;
}
```

**Ouput**
```vbnet
Is name public? false
Is name private? true
Is name static? false
```

## Annotation
It is a form of metadata that provides additional information to the Java compiler and runtime about the code. Annotations are used to give instructions to the compiler, mark methods or classes for special processing, or influence the behavior of frameworks and tools that operate on the code.

### Get Annotation object
1. Get the Relection object, e.g. Field
2. Get the annotation of from the Reflection object.

**Example**
```java
Class<?> clss = Person.class;
Field field = clss.getDeclaredField("name");
Annotation[] annotations = field.getAnnotations();
System.out.println(Arrays.asList(annotations));
```
