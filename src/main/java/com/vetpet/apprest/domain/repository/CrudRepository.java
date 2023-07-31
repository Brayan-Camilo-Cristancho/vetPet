package com.vetpet.apprest.domain.repository;


import java.util.List;

public abstract class CrudRepository<T> {
    public abstract List<T> getAll();

    public abstract Long save(T entity,String role);

    public abstract void update(T entity);

    public abstract void delete(String v);
}
