package org.up.roque.project.employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.DataAccessException;
import org.up.roque.project.ProcessingException;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeCrudRepository repository;

  @Override
  public Set<Employee> findAll() {
    return repository.findAll();
  }

  @Override
  public void delete(Employee employee) {
    try {
      repository.delete(employee.getId());
    } catch (DataAccessException e) {
      log.warn("Exception while trying to delete {}, exception: {}", employee, e.toString());
      throw new ProcessingException("Entity couldn't be deleted");
    }
  }
}
