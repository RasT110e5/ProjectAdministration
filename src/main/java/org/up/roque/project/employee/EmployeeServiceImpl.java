package org.up.roque.project.employee;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.DataAccessException;
import org.up.roque.project.Project;
import org.up.roque.project.util.ProcessingException;
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
    try {
      return this.employeeRepository.findAllByProject(project);
    } catch (DataAccessException e) {
      log.warn("There was an exception while finding all employess for project: {}, exception: {}", project, e.toString());
      throw new ProcessingException(e.getMessage());
    }
  }
}
