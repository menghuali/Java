package myjava.reflection.orm;

public interface EntityManger<T> {

    void persist(T entity);

    T find(Object pk);

}
