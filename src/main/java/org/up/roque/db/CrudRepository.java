package org.up.roque.db;

import java.util.Set;

public interface CrudRepository<T, ID> {
  T save(T entity);
  void delete(ID id);
  Set<T> findAll();
  T getOne(ID id);
}
