package myjava.reflection.util;

import java.lang.reflect.Field;

import myjava.reflection.annotation.PrimaryKey;

public class PrimaryKeyField {

    private Field field;
    private PrimaryKey pk;

    public PrimaryKeyField(Field field) {
        this.field = field;
        pk = field.getAnnotation(PrimaryKey.class);
    }

    public Field getField() {
        return field;
    }

    @Override
    public String toString() {
        return "PrimaryKeyField [field=" + field + "]";
    }

    public String getColumnName() {
        return pk.name().equals("unknown") ? field.getName() : pk.name();
    }

}
