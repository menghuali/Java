package myjava.reflection.util;

import java.lang.reflect.Field;

import myjava.reflection.annotation.Column;

public class ColumnField {

    private Field field;
    private Column column;

    public Field getField() {
        return field;
    }

    public ColumnField(Field field) {
        this.field = field;
        this.column = field.getAnnotation(Column.class);
    }

    @Override
    public String toString() {
        return "ColumnField [field=" + field + "]";
    }

    public String getColumnName() {
        return column.name().equals("unknown") ? field.getName() : column.name();
    }

}
