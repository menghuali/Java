package myjava.reflection.util;

import java.lang.reflect.Field;

public class PrimaryKeyField {

    private Field field;

    public PrimaryKeyField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    @Override
    public String toString() {
        return "PrimaryKeyField [field=" + field + "]";
    }

}
