package org.up.roque.project.util;

import java.util.Set;

public interface Service<T, ID> {

  void delete(T entity);

  Set<T> findAll();

  void save(T entity);

  T getById(ID id);

}
