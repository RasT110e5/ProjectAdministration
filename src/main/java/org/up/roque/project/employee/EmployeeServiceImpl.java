package org.up.roque.project.employee;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.project.Project;
import org.up.roque.project.util.ServiceTemplate;

import java.util.Set;

@Slf4j
public class EmployeeServiceImpl extends ServiceTemplate<Employee, Integer> implements EmployeeService {
  private final EmployeeCrudRepository employeeRepository;

  public EmployeeServiceImpl(EmployeeCrudRepository repository) {
    super(repository);
    this.employeeRepository = repository;
  }

  @Override
  public Set<Employee> findAllBy(Project project) {
    return this.employeeRepository.findAllByProject(project);
  }
}
