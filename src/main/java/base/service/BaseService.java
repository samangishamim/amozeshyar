package base.service;

import base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseService <T extends BaseEntity<ID>, ID extends Serializable>{
    T saveOrUpdate(T entity);

    T findById(ID id);

    void delete(T t);

   List<T> findAll ();
}
