package org.up.roque.project.employee;

import java.util.Set;

public interface EmployeeService {
  Set<Employee> findAll();

  void delete(Employee employee);
}
