package myjava.reflection.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.lang.invoke.MethodHandles.Lookup;

import myjava.reflection.model.Person;

public class MethodHandleSandbox {

    public static void main(String[] args) throws Throwable {
        Lookup lookup = MethodHandles.lookup();
        
        // Call constructors
        MethodType noArgConstructorMT = MethodType.methodType(void.class);
        MethodHandle noArgConstructor = lookup.findConstructor(Person.class, noArgConstructorMT);
        Person p = (Person) noArgConstructor.invoke();
        System.out.println(p);

        MethodType constructorMT = MethodType.methodType(void.class, String.class, int.class);
        MethodHandle constructor = lookup.findConstructor(Person.class, constructorMT);
        p = (Person) constructor.invoke("Jordan", 23);
        System.out.println(p);

        // Call methods
        MethodType nameGetterMT = MethodType.methodType(String.class);
        MethodHandle nameGetter = lookup.findVirtual(Person.class, "getName", nameGetterMT);
        String name = (String) nameGetter.invoke(p);
        System.out.println(name);

        MethodType ageSetterMT = MethodType.methodType(void.class, int.class);
        MethodHandle ageSetter = lookup.findVirtual(Person.class, "setAge", ageSetterMT);
        ageSetter.invoke(p, 45);
        System.out.println(p);

        // Access fields
        Lookup privateLookup = MethodHandles.privateLookupIn(Person.class, lookup);
        MethodHandle nameField = privateLookup.findGetter(Person.class, "name", String.class);
        name = (String) nameField.invoke(p);
        System.out.println(name);

        // Volatile
        VarHandle ageField = privateLookup.findVarHandle(Person.class, "age", int.class);
        System.out.println(ageField.get(p));
        System.out.println(ageField.getAndAdd(p, 1));
        System.out.println(p);
    }

}
