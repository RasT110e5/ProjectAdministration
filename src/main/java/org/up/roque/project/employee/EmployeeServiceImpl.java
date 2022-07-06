package org.up.roque.project.employee;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.CrudRepository;
import org.up.roque.project.util.ServiceTemplate;

@Slf4j
public class EmployeeServiceImpl extends ServiceTemplate<Employee, Integer> implements EmployeeService {
  public EmployeeServiceImpl(CrudRepository<Employee, Integer> repository) {
    super(repository);
  }
}
