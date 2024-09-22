package myjava.reflection.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import myjava.reflection.annotation.Column;
import myjava.reflection.annotation.PrimaryKey;

public class MetaModel<T> {

    private Class<T> clss;

    public MetaModel(Class<T> clss) {
        this.clss = clss;
    }

    public static <T> MetaModel<T> of(Class<T> clss) {
        return new MetaModel<>(clss);
    }

    public PrimaryKeyField getPrimaryKey() {
        return Arrays.stream(clss.getDeclaredFields()).filter(field -> field.getAnnotation(PrimaryKey.class) != null)
                .findAny().map(field -> new PrimaryKeyField(field))
                .orElseThrow(() -> new IllegalArgumentException("No primary key found in class " + clss.getName()));
    }

    public List<ColumnField> getColumns() {
        return Arrays.stream(clss.getDeclaredFields()).filter(field -> field.getAnnotation(Column.class) != null)
                .map(field -> new ColumnField(field)).collect(Collectors.toList());
    }

}
