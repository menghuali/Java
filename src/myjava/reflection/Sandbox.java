package myjava.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import myjava.reflection.model.Person;

public class Sandbox {

    public static void main(String[] args)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        readField();
        noArgConstructor();
        constructorRequiresArgs();
        invokeNoArgMethod();
        invokeMethodRequireArgs();
        invokeStaticMethod();
        getAnnotations();
    }

    private static void getAnnotations() throws NoSuchFieldException {
        Class<?> clss = Person.class;
        Field field = clss.getDeclaredField("name");
        Annotation[] annotations = field.getAnnotations();
        System.out.println(Arrays.asList(annotations));
    }

    private static void invokeStaticMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> clss = Person.class;
        Method method = clss.getDeclaredMethod("of", String.class, int.class);
        Object result = method.invoke(null, "Peter", 23);
        System.out.println(result);
    }

    private static void invokeMethodRequireArgs() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> clss = Person.class;
        Method method = clss.getDeclaredMethod("setName", String.class);
        Person obj = new Person();
        method.invoke(obj, "Peter");
        System.out.println(obj);
    }

    private static void invokeNoArgMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> clss = Person.class;
        Method method = clss.getDeclaredMethod("toString");
        Person obj = new Person("Peter", 23);
        method.invoke(obj);
    }

    private static void constructorRequiresArgs()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> clss = Person.class;
        Constructor<?> constructor = clss.getDeclaredConstructor(String.class, int.class);
        Object obj = constructor.newInstance("Peter", 23);
        System.out.println(obj);
    }

    private static void noArgConstructor()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> clss = Person.class;
        Constructor<?> constructor = clss.getDeclaredConstructor();
        Object obj = constructor.newInstance();
        System.out.println(obj);
    }

    private static void readField() throws NoSuchFieldException, IllegalAccessException {
        Person obj = new Person();
        obj.setName("Peter");

        Class<?> clss = obj.getClass();
        Field field = clss.getDeclaredField("name");
        field.setAccessible(true);
        Object value = field.get(obj);
        System.out.println(value);
    }

}
