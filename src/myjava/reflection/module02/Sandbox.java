package myjava.reflection.module02;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

import myjava.reflection.model.Person;

public class Sandbox {

    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchFieldException, SecurityException, NoSuchMethodException {
        // == Get a Class ==
        Class<?> clazz1 = "Hello".getClass();
        Class<?> clazz2 = "World".getClass();
        System.out.printf("clazz1 == clazz2: %s\n", clazz1 == clazz2); // instances of same Class returns same Class.

        // == Get superclass ==
        Class<?> clazz3 = String.class;
        Class<?> clazz4 = Class.forName("java.lang.String");
        System.out.printf("clazz3 == clazz4: %s\n", clazz3 == clazz4);

        Class<?> superclass = clazz1.getSuperclass();
        System.out.printf("Supper class of clazz1: %s\n", superclass);

        // == Get implemented interfaces ==
        Class<?>[] interfaces = clazz1.getInterfaces();
        System.out.println(
                Arrays.stream(interfaces).map(Class::getName).collect(Collectors.joining(", ", "Interfaces:[", "]")));

        // == Get fields ==
        Class<?> pclazz = Person.class;
        // Get public and inherited fields
        System.out.println("Fields: " + Arrays.toString(pclazz.getFields()));
        // Get fields decared in the class.
        System.out.println("Declared Fields: " + Arrays.toString(pclazz.getDeclaredFields()));
        // Get a field by name
        System.out.printf("Field age: %s", pclazz.getDeclaredField("age"));

        // == Get methods ==
        // Get public and inherited methods
        System.out.println("Methods: " + Arrays.toString(pclazz.getMethods()));
        // Get methods declared in the class
        System.out.println("Declared Methods: " + Arrays.toString(pclazz.getDeclaredMethods()));
        // Get a certain method
        System.out.println("Method getAge: " + pclazz.getMethod("getAge"));
        // Where the input parameter is primive type.
        System.out.println("Method setAge: " + pclazz.getMethod("setAge", int.class));
        System.out.println("Method setName: " + pclazz.getMethod("setName", String.class));
        System.out.println("Method of: " + pclazz.getMethod("of", String.class, int.class));

        // == Get constructors ==
        System.out.println("Constructors: " + Arrays.toString(pclazz.getConstructors()));
        System.out.println("Declared constructors: " + Arrays.toString(pclazz.getDeclaredConstructors()));
        System.out.println("Constructor: " + pclazz.getConstructor(String.class, int.class));

        // == Get field modifier
        Field age = pclazz.getDeclaredField("age");
        int mod = age.getModifiers();
        System.out.printf("Field %s: [final=%s, private=%s, static=%s, type=%s]\n", age.getName(),
                Modifier.isFinal(mod),
                Modifier.isPrivate(mod), Modifier.isStatic(mod), age.getType());

        // == Get method modifier ==
        Method of = pclazz.getDeclaredMethod("of", String.class, int.class);
        mod = of.getModifiers();
        System.out.printf("Method %s: [final=%s, public=%s, static=%s, returnType=%s, parameter=%s, abstract=%s]\n",
                of.getName(), Modifier.isFinal(mod), Modifier.isPublic(mod), Modifier.isStatic(mod), of.getReturnType(),
                Arrays.toString(of.getParameterTypes()), Modifier.isAbstract(mod));

        // == Get constuctor modifier ==
        Constructor<?> constructor = pclazz.getConstructor(String.class, int.class);
        mod = constructor.getModifiers();
        System.out.printf("Contructor %s: [final=%s, public=%s, parameter=%s]\n", constructor.getName(),
                Modifier.isFinal(mod), Modifier.isPublic(mod), Arrays.toString(constructor.getParameterTypes()));
    }

}
