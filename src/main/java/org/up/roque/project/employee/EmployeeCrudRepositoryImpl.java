package org.up.roque.project.employee;

import lombok.RequiredArgsConstructor;
import org.up.roque.db.DBTemplate;

import java.util.Set;

@RequiredArgsConstructor
public class EmployeeCrudRepositoryImpl implements EmployeeCrudRepository {
  private final DBTemplate manager;

  @Override
  public Employee save(Employee employee) {
    return null;
  }

  @Override
  public void delete(Employee employee) {

  }

  @Override
  public Set<Employee> findAll() {
    return null;
  }

  @Override
  public Employee getOne() {
    return null;
  }
}
