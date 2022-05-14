package org.up.roque.db;

import org.up.roque.project.employee.Employee;

import java.util.Set;

public interface CrudRepository<T> {
  T save(T entity);
  void delete(T entity);
  Set<T> findAll();
  T getOne();
}
