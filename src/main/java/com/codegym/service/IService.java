package com.codegym.service;

public interface IService<T> {
    Iterable<T> findAll();

    T getById(Long id);

    void save(T model);

    void update(T model);

    void remove(Long id);
}
