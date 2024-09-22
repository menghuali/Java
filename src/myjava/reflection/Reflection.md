# Java Reflection
Use of Reflection API: read and modify the content of an object without knowing its class or structure.

All Java framework is using Reflection API, such as Spring and Hibernate.

## Concept
### Key Classes of Relection API
* Class
* Field
* Method
* Constructor
* Annotation

#### Class
You cannot create a Class instance.

##### How to get a Class instance?
1. You can get a Class instance from an object.
    ```java
    String hello = "Hello";
    Class<?> clazz = hello.getClass()
    ```

2. You can get a Class instance by its name known at compile time.
    ```java
    Class<?> clazz = String.class;
    ```

3. You can get a Class instance by its name known at runtime.
    ```java
    Class<?> clazz = String.class;
    ```

**Get super class and implemented interfaces**

Get supper class.
```java
// java.lang.Object returns null;
Class<?> superclass = String.class.getSuperclass();
```

Get implemented interfaces. It returns empt 
```java
// getInterfaces returns an empty array if the class doesn't implement any interface.
Class<?>[] interfaces = String.class.getInterfaces();
```

