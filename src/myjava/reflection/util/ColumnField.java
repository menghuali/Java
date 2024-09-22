package myjava.reflection.util;

import java.lang.reflect.Field;

public class ColumnField {

    private Field field;

    public Field getField() {
        return field;
    }

    public ColumnField(Field field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "ColumnField [field=" + field + "]";
    }

}
