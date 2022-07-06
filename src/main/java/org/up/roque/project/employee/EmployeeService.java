package org.up.roque.project.employee;

import org.up.roque.project.Project;
import org.up.roque.project.Service;

import java.util.Set;

public interface EmployeeService extends Service<Employee> {
  Employee getOne(Integer id);

  void save(Employee employee);
}
