package org.up.roque.project.employee;

import lombok.RequiredArgsConstructor;
import org.up.roque.db.DBTemplate;
import org.up.roque.db.DataAccessException;
import org.up.roque.db.SqlParam;

import javax.print.attribute.standard.MediaSize;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RequiredArgsConstructor
public class EmployeeCrudRepositoryImpl implements EmployeeCrudRepository {
  private static final String NAME_COLUMN = "NAME";
  private static final String COST_COLUMN = "COST_PER_HOUR";
  private static final String ID_COLUMN = "ID";
  private static final String TABLE = "EMPLOYEE";

  private static final String DELETE = "DELETE FROM %s WHERE ID=?".formatted(TABLE);
  private static final String SELECT = ("SELECT %s,%s,%s FROM %s")
      .formatted(ID_COLUMN, NAME_COLUMN, COST_COLUMN, TABLE);
  private static final String UPDATE = ("UPDATE %s SET %s=?, %s=? WHERE %s=?")
      .formatted(TABLE, NAME_COLUMN, COST_COLUMN, ID_COLUMN);
  private static final String INSERT = ("INSERT INTO %s (%s, %s) VALUES (?,?)")
      .formatted(TABLE, NAME_COLUMN, COST_COLUMN);

  private final DBTemplate template;

  private List<SqlParam> getPropertiesAsParam(Employee employee) {
    return List.of(new SqlParam(employee.getName()),
        new SqlParam(employee.getCostPerHour()));
  }

  private List<SqlParam> getIdAsParam(Integer id) {
    return List.of(new SqlParam(id));
  }

  private List<SqlParam> getAllPropertiesAsParam(Employee employee) {
    return Stream.of(getIdAsParam(employee.getId()), getPropertiesAsParam(employee))
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  @Override
  public Employee save(Employee employee) {
    if (employee.getId() == null)
      insert(employee);
    else
      update(employee);
    return employee;
  }

  private void update(Employee employee) {
    template.save(UPDATE, getAllPropertiesAsParam(employee));
  }

  private void insert(Employee employee) {
    Integer id = template.save(INSERT, Integer.class, getPropertiesAsParam(employee));
    employee.setId(id);
  }

  @Override
  public void delete(Integer id) {
    template.delete(DELETE, getIdAsParam(id));
  }

  @Override
  public Set<Employee> findAll() {
    return template.query(SELECT, rs ->
        getBaseBuilderParser(rs)
            .id(rs.getInt(ID_COLUMN))
            .build());
  }

  @Override
  public Employee getOne(Integer id) {
    try {
      return template.query(SELECT + " WHERE ID=?", getIdAsParam(id), rs ->
          getBaseBuilderParser(rs)
              .id(id)
              .build()
      ).iterator().next();
    } catch (NoSuchElementException e) {
      throw new DataAccessException("No employee found for ID=%s".formatted(id));
    }
  }

  private Employee.EmployeeBuilder getBaseBuilderParser(ResultSet rs) throws SQLException {
    return Employee.builder()
        .name(rs.getString(NAME_COLUMN))
        .costPerHour(rs.getInt(COST_COLUMN));
  }
}