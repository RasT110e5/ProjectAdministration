package org.up.roque.project.employee;

import org.up.roque.db.CrudRepository;

import java.util.Set;

public interface EmployeeCrudRepository extends CrudRepository<Employee> {
  Employee save(Employee employee);
  void delete(Employee employee);
  Set<Employee> findAll();
  Employee getOne();
}
