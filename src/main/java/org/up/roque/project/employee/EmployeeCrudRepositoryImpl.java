package org.up.roque.project.employee;

import lombok.RequiredArgsConstructor;
import org.up.roque.db.DBTemplate;
import org.up.roque.db.StatementParameter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.up.roque.db.StatementParameter.INTEGER;
import static org.up.roque.db.StatementParameter.STRING;

@RequiredArgsConstructor
public class EmployeeCrudRepositoryImpl implements EmployeeCrudRepository {
  private final DBTemplate template;

  @Override
  public Employee save(Employee employee) {
    String sql = "INSERT INTO EMPLOYEE (NAME, COST_PER_HOUR) VALUES (?,?)";
    LinkedHashMap<StatementParameter, Object> params = new LinkedHashMap<>();
    params.put(STRING, employee.getName());
    params.put(INTEGER, employee.getCostPerHour());
    Integer id = template.save(sql, Integer.class, params);
    employee.setId(id);
    return employee;
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
