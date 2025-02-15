package com.homeworks.homework41.app.service;

import java.util.List;

public interface BaseService<T, ID> {

    T create (ID id);
    List<T> getAll();
    T getById(ID id);
    T updateById(ID id, T entity);
    boolean deleteById(ID id);
}
