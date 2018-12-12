package com.omelchenkoaleks.core.dao.interfaces;

import java.util.List;

// описывает общие действия с БД для всех объектов
public interface CommonDAO<T> {

    List<T> getAll();
    T get(long id);

    // используем boolean, чтобы легко можно быдо удовстоверяться
    // - прошла ли операция успешно
    boolean update(T storage);
    boolean delete(T storage);
}
