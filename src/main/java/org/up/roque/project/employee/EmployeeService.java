package org.up.roque.project.employee;

import java.util.Set;

public interface EmployeeService {
  Employee getOne(Integer id);
  Set<Employee> findAll();

  void delete(Employee employee);

  void save(Employee employee);
}
