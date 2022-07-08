package org.up.roque.project.employee;

import org.up.roque.project.util.CrudRepository;
import org.up.roque.project.Project;

import java.util.Set;

public interface EmployeeCrudRepository extends CrudRepository<Employee, Integer> {
  Set<Employee> findAllByProject(Project project);
}
