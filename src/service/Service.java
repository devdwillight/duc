package service;

public interface Service<T> {
    T findById(String id);

    void add(T entity);

    void save();
}
