# Java Annotation
## What is Annotation?
Java Annotation was introduced in Java 5 in 2004. It is a form of metadata that provides additional information to the Java compiler and runtime about the code. Annotations are used to give instructions to the compiler, mark methods or classes for special processing, or influence the behavior of frameworks and tools that operate on the code. Annotations do not change the program logic, but they provide a mechanism for <span style="color:red">associating metadata with program elements</span> (like classes, methods, variables, etc.) in a readable and flexible way.

### Scenarios to Use Annotation
#### 1. Code Documentation and Compiler Checks:
Annotations like ```@Override```, ```@Deprecated```, and ```@SuppressWarnings``` help developers document code and instruct the compiler to enforce specific rules:
* ```@Override```: Ensures that a method is correctly overriding a method in the superclass, helping avoid errors due to mismatched method signatures.
* ```@Deprecated```: Marks methods or classes as deprecated, warning developers not to use them in new code.
* ```@SuppressWarnings```: Instructs the compiler to ignore specific warnings (e.g., unchecked operations).

#### 2. Framework and Tool Integration:
Many frameworks and libraries use annotations to reduce boilerplate code and offer configuration options without external files. For example:
* Spring Framework: Uses annotations like @Autowired for dependency injection, ```@Controller``` for marking a class as a Spring MVC controller, and ```@Transactional``` to manage database transactions.
* JUnit: Uses ```@Test``` to mark test methods, allowing for unit testing in a clean, declarative way.
* Hibernate: Uses annotations like ```@Entity``` to mark a class as an entity representing a table in a database, and ```@Column``` to specify details about fields mapping to database columns.

#### 3. Runtime Processing and Reflection:
Annotations can be retained at runtime and processed dynamically using reflection. This is useful for frameworks that need to inspect the structure of a class or method at runtime. For example, a framework might look for methods annotated with ```@CustomAnnotation``` to dynamically configure behavior.

#### 4. Code Generation and Custom Annotations:
Annotations can be used in combination with tools like annotation processors (via the Java Annotation Processing Tool, APT) to generate additional code or resources at compile time. Developers can define their own custom annotations using @interface. These annotations can then be processed at compile time or runtime to trigger custom behavior.

**Example**: Using ```@Getter``` and ```@Setter``` annotations in Lombok to automatically generate getter and setter methods, eliminating boilerplate code.

#### 5. Dependency Injection (DI):
Annotations like ```@Inject``` (from JSR-330) or @Autowired (in Spring) allow for automatic injection of dependencies. This makes managing dependencies more declarative and reduces the need for manual wiring of objects.

#### 6. Cross-Cutting Concerns (Aspect-Oriented Programming):
Annotations are used in Aspect-Oriented Programming (AOP) to handle cross-cutting concerns like logging, security, and transaction management. For example, in Spring, you can use ```@Transactional``` to automatically handle transactions without needing to write explicit transaction management code.

## Use Annotations
In Java, using annotations involves attaching them to classes, methods, fields, or other program elements to provide metadata or instructions. Here's the general syntax of using annotations, along with some examples.

### 1. Basic Syntax for Using Annotations
Annotations are applied using the ```@``` symbol followed by the annotation name. Annotations can be placed above classes, methods, variables, parameters, or constructors.
```java
@AnnotationName
public class MyClass {
    // class body
}
```

2. Marker Anotations (Annotations with No Elements)
If an annotation has no elements (members), you simply write the annotation's name.
```java
@Override
public String toString() {
    return "MyClass";
}
```
Here, ```@Override``` is an annotation applied to a method to indicate that it overrides a method from the superclass.

### 3. Annotations with Single Element
For annotations with one element, the syntax involves specifying the value in parentheses right after the annotation.
```java
@JsonProperty("full_name")
private String name;
```
The annotation ```@JsonProperty``` maps the name field to the full_name JSON property during serialization/deserialization.

### 4. Annotations with Multiple Elements
If an annotation has multiple elements (members), you specify each element's name and value in parentheses, separated by commas.
```java
public @interface MyAnnotation {
    String name();
    int age();
}

@MyAnnotation(name = "John", age = 30)
public void myMethod() {
    // method body
}
```
In this example, the @MyAnnotation has two elements, name and age, and the annotation is applied to a method.

### 5. Annotations with Default Values
If an annotation has elements with default values, you don’t have to specify those values unless you want to override the default.
```java
public @interface MyAnnotation {
    String name() default "Unknown";
    int age() default 18;
}

@MyAnnotation(name = "Alice")
public void myMethod() {
    // method logic
}
```
In this case, only the name element is specified, while the age element uses its default value of 18.

## Key Predefined Annotations
### Annotation Types Used by the Java Language
* ```@Deprecated```: Indicates that the marked element is deprecated and should no longer be used.
* ```@Override``: Informs the compiler that the element is meant to override an element declared in a superclass.
* ```@SuppressWarnings```: Tells the compiler to suppress specific warnings that it would otherwise generate.
*  ```@FunctionalInterface```: Introduced in Java SE 8, indicates that the type declaration is intended to be a functional interface, as defined by the Java Language Specification.

### Annotations That Apply to Other Annotations
* ```@Retention```: Specifies how long the annotation should be retained (e.g., in source code, in class files, or at runtime).
* ```@Target```: Specifies where the annotation can be applied (e.g., method, class, field, etc.).
* ```@Inherited```: Marks an annotation that can be inherited by subclasses.

## Define Custom Annotations
Defining custom annotations in Java involves using the ```@interface``` keyword. Here's a step-by-step introduction to the syntax of creating your own annotation:

### 1. Create a Custom Annotation
To define a custom annotation, use the ```@interface``` keyword followed by the name of the annotation. Annotations can have elements (members) that resemble methods but don't actually define behavior.
```java
public @interface MyAnnotation {
    // Define elements (optional)
}
```

### 2. Define Annotation Elements
Annotations can have elements, which are methods that return types like primitives, String, Class, enums, arrays, or other annotations. These methods can also have default values.

```java
public @interface MyAnnotation {
    String name();
    int value() default 0;
}
```
```name()```: This defines an element that returns a String.

```value()```: This element returns an int and has a default value of 0. If an annotation element has a default value, it can be omitted when using the annotation. Only non-default elements need to be specified.

### 3. Specifying Annotation Targets
You can restrict where the annotation can be applied (e.g., to methods, classes, fields) using the ```@Target``` meta-annotation. This takes one or more ElementType values.
```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface MyAnnotation {
    String name();
    int value() default 0;
}
```
```ElementType.METHOD```: Restricts this annotation to methods.

For all supported targets, see [Javadoc: ElementType](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/annotation/ElementType.html).

Sample of mulitple targets
```java
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface MyAnnotation {
    String name();
    int value() default 0;
}
```

### 4. Define Retention Policy
The ```@Retention``` meta-annotation controls how long the annotation is retained. There are three retention policies:
* ```RetentionPolicy.SOURCE```: Discarded after compilation.
* ```RetentionPolicy.CLASS```: Available in the class file but not at runtime.
* ```RetentionPolicy.RUNTIME```: Available at runtime, enabling reflection.

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    String name();
    int value() default 0;
}
```

```RetentionPolicy.RUNTIME```: Annotations are to be recorded in the class file by the compiler and retained by the VM at run time, so they may be read reflectively.

For all supported retention policies, see [Javadoc: RetentionPolicy](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/annotation/RetentionPolicy.html).
****
## Get Annotations at Runtime
To get the annotations of a class, method, or field at runtime in Java, you can use **Java Reflection API**. Reflection allows you to inspect and manipulate the structure of classes, methods, and fields at runtime, including annotations. In gernal, you first need to get annotated target (class, method, or field), then get the annotation from the target.

Here's how you can retrieve annotations:
### 1. Getting Annotations on a Class
To get the annotations of a class, use the ```getAnnotations()``` method provided by the Class class. This method returns an array of annotations that are present on the class.
```java
import java.lang.annotation.Annotation;

public class AnnotationExample {
    public static void main(String[] args) {
        Class<MyClass> obj = MyClass.class;

        // Get all annotations on the class
        Annotation[] annotations = obj.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}

@MyCustomAnnotation(name = "ExampleClass", value = 5)
class MyClass {
    // Class body
}
```

```getAnnotations()```: This method retrieves all annotations applied to the class.

```MyClass.class```: Obtains the Class object associated with the class MyClass.

### 2. Getting Annotations on a Method
To retrieve annotations on a method, you first get the Method object using reflection, and then call ```getAnnotations()``` on that method.
```java
Copy code
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationExample {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<MyClass> obj = MyClass.class;
        
        // Get a specific method by name
        Method method = obj.getMethod("myMethod");

        // Get all annotations on the method
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}

class MyClass {

    @CustomAnnotation(description = "Sample method", version = 1)
    public void myMethod() {
        // Method body
    }
}
```

```obj.getMethod("myMethod")```: Retrieves the Method object for the specified method.

```method.getAnnotations()```: Returns the annotations on the method.

### 3. Getting Annotations on a Field
To get the annotations of a field, retrieve the Field object from the class using reflection, and then call ```getAnnotations()``` on the field.
```java
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationExample {
    public static void main(String[] args) throws NoSuchFieldException {
        Class<MyClass> obj = MyClass.class;

        // Get the specific field by name
        Field field = obj.getField("myField");

        // Get all annotations on the field
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}

class MyClass {

    @CustomAnnotation(description = "Sample field", version = 2)
    public String myField;
}
```

```obj.getField("myField")```: Retrieves the Field object for the specified field.

```field.getAnnotations()```: Returns the annotations on the field.

### 4. Getting a Specific Annotation
If you're looking for a specific annotation type, you can use the ```getAnnotation(Class<? extends Annotation> annotationClass)``` method. This retrieves the annotation of the given type if it exists.

For example, if you want to get the CustomAnnotation from a method:
```java
CustomAnnotation annotation = method.getAnnotation(CustomAnnotation.class);
if (annotation != null) {
    System.out.println("Description: " + annotation.description());
    System.out.println("Version: " + annotation.version());
}
```

## Reference
* [Oracle: Java Annotation Tutorial](https://docs.oracle.com/javase/tutorial/java/annotations/)

