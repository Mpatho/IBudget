package com.mpathozulu.soft.ibudget2.database;

import java.util.Collection;

public interface PersistenceContext {

    boolean persist(Object entity);

    boolean remove(Object entity);

    boolean remove(Long id, Class<?> clazz);

    <T> T find(Class<T> clazz, Long id);

    <T> Collection<T> findAll(Class<T> clazz);
}
