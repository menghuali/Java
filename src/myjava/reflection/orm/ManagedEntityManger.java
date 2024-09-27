package myjava.reflection.orm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import myjava.reflection.annotation.Inject;
import myjava.reflection.util.ColumnField;
import myjava.reflection.util.MetaModel;

public class ManagedEntityManger<T> implements EntityManger<T> {

    private Class<T> clss;

    private MetaModel<T> mm;

    private AtomicLong idGen;

    @Inject
    private Connection connection;

    public ManagedEntityManger() {
    }

    public ManagedEntityManger(Class<T> clss) {
        this.clss = clss;
        mm = MetaModel.of(clss);
        idGen = new AtomicLong();
    }

    public void setEntityClass(Class<T> clss) {
        this.clss = clss;
        mm = MetaModel.of(clss);
    }

    @Override
    public void persist(T entity) {
        String sql = mm.buildInsertSql();
        try (PreparedStatement statement = preparedStatementWith(sql).andParameter(entity)) {
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to persist the entity.", e);
        }
    }

    private PreparedStatementWrapper preparedStatementWith(String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        return new PreparedStatementWrapper(statement);
    }

    private class PreparedStatementWrapper {

        private PreparedStatement statement;

        public PreparedStatementWrapper(PreparedStatement statement) {
            this.statement = statement;
        }

        public PreparedStatement andParameter(T entity)
                throws SQLException, IllegalArgumentException, IllegalAccessException {
            Field pk = mm.getPrimaryKey().getField();
            Class<?> pkType = pk.getType();
            if (pkType == long.class) {
                long pkVal = idGen.incrementAndGet();
                statement.setLong(1, pkVal);
                pk.setAccessible(true);
                pk.set(entity, pkVal);
            } else {
                throw new IllegalArgumentException(String.format("The PK type %s is not supported", pkType));
            }
            for (int i = 0; i < mm.getColumns().size(); i++) {
                Field field = mm.getColumns().get(i).getField();
                field.setAccessible(true);
                Class<?> type = field.getType();
                Object value = field.get(entity);
                if (type == int.class) {
                    statement.setInt(i + 2, (int) value);
                } else if (type == String.class) {
                    statement.setString(i + 2, (String) value);
                } else {
                    throw new IllegalArgumentException(
                            String.format("The type %s of field %s is not supported", type, field.getName()));
                }
            }
            return statement;
        }

        public PreparedStatement andPrimaryKey(Object pk) throws SQLException {
            Class<?> pkType = mm.getPrimaryKey().getField().getType();
            if (pkType == long.class) {
                statement.setLong(1, (Long) pk);
            } else {
                throw new IllegalArgumentException("Primary key type is not supported: " + pkType.getName());
            }
            return statement;
        }

    }

    @Override
    public T find(Object pk) {
        String sql = mm.buildSelectSql();
        System.out.println("Run SQL: " + sql);
        try (PreparedStatement statement = preparedStatementWith(sql).andPrimaryKey(pk)) {
            ResultSet result = statement.executeQuery();
            return buildInstanceFrom(result);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find the entity " + pk, e);
        }
    }

    private T buildInstanceFrom(ResultSet result) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException,
            SQLException {
        if (!result.next())
            return null;

        T entity = clss.getConstructor().newInstance();
        setValue(entity, mm.getPrimaryKey().getField(), result, mm.getPrimaryKey().getColumnName());

        for (ColumnField colField : mm.getColumns())
            setValue(entity, colField.getField(), result, colField.getColumnName());
        return entity;
    }

    private void setValue(T entity, Field field, ResultSet result, String column)
            throws SQLException, IllegalArgumentException, IllegalAccessException {
        Class<?> fieldType = field.getType();
        field.setAccessible(true);
        if (fieldType == long.class) {
            long value = result.getLong(column);
            field.setLong(entity, value);
        } else if (fieldType == String.class) {
            field.set(entity, result.getString(column));
        } else if (fieldType == int.class) {
            int value = result.getInt(column);
            field.setInt(entity, value);
        } else {
            throw new IllegalArgumentException("Data type is not supported: " + fieldType.getName());
        }
    }

}
