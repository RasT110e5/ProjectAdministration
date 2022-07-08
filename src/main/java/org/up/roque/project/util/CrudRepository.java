package org.up.roque.project.util;

import java.util.Set;

public interface CrudRepository<T, ID> {
  T save(T entity);
  void delete(ID id);
  Set<T> findAll();
  T getOne(ID id);
}
