package myjava.reflection.util;

import java.lang.reflect.Field;
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

    public String buildInsertSql() {
        String pk = getPrimaryKey().getColumnName();
        List<String> cols = getColumns().stream().map(f -> f.getColumnName()).collect(Collectors.toList());
        cols.add(0, pk);
        String sqlCols = String.join(", ", cols);
        String sqlVals = cols.stream().map(col -> "?").collect(Collectors.joining(", "));

        return String.format("INSERT INTO %s (%s) values (%s)", clss.getSimpleName(), sqlCols, sqlVals);
    }

    public String buildSelectSql() {
        String pk = getPrimaryKey().getColumnName();
        List<String> cols = getColumns().stream().map(col -> col.getColumnName()).collect(Collectors.toList());
        cols.add(0, pk);
        String sqlCols = String.join(", ", cols);
        return String.format("SELECT %s FROM %s WHERE %s = ?", sqlCols, clss.getSimpleName(), pk);
    }

    public List<Field> allFields() {
        List<Field> fields = getColumns().stream().map(ColumnField::getField).collect(Collectors.toList());
        fields.add(0, getPrimaryKey().getField());
        return fields;
    }

}
