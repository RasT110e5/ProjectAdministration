package org.up.roque.project;

import java.util.Set;

public interface Service<T> {

  void delete(T entity);

  Set<T> findAll();

}
