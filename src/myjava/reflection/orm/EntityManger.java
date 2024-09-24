package myjava.reflection.orm;

public interface EntityManger<T> {

    static <T> EntityManger<T> of(Class<T> clss) {
        return new EntityMangerImpl<T>(clss);
    }

    void persist(T entity);

    T find(Object pk);

}
