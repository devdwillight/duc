package service;

import utils.CustomException;

public interface Service<T> {
    T findById(String id);

    void add(T entity) throws CustomException;

    void save();
}
