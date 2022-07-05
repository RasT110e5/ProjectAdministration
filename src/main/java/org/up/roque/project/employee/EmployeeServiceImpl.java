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
  public Employee getOne(Integer id) {
    return repository.getOne(id);
  }

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
      throw new ProcessingException("Employee couldn't be deleted");
    }
  }

  @Override
  public void save(Employee employee) {
    try {
      repository.save(employee);
    } catch (DataAccessException e) {
      log.warn("Exception while saving {}, exception: {}", employee, e.toString());
      throw new ProcessingException("Employee couldn't be saved");
    }
  }
}
