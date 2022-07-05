package org.up.roque.project.employee;

import org.up.roque.db.CrudRepositoryTemplate;
import org.up.roque.db.DBTemplate;
import org.up.roque.db.SqlParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeCrudRepositoryImpl extends CrudRepositoryTemplate<Employee, Integer>
    implements EmployeeCrudRepository {
  private static final String NAME_COLUMN = "NAME";
  private static final String COST_COLUMN = "COST_PER_HOUR";
  private static final String ID_COLUMN = "ID";
  private static final String TABLE = "EMPLOYEE";

  public EmployeeCrudRepositoryImpl(DBTemplate template) {
    super(template, TABLE, Integer.class, ID_COLUMN, NAME_COLUMN, COST_COLUMN);
  }

  @Override
  protected List<SqlParam> getPropertiesAsParam(Employee employee) {
    return List.of(new SqlParam(employee.getName()),
        new SqlParam(employee.getCostPerHour()));
  }

  @Override
  protected List<SqlParam> getIdAsParam(Integer id) {
    return List.of(new SqlParam(id));
  }

  @Override
  protected DBTemplate.ResultSetParser<Employee> getResultSetParser() {
    return rs -> getBaseBuilderParser(rs)
        .id(rs.getInt(ID_COLUMN))
        .build();
  }

  @Override
  protected DBTemplate.ResultSetParser<Employee> getResultSetWithId(Integer id) {
    return rs -> getBaseBuilderParser(rs)
        .id(id)
        .build();
  }

  private Employee.EmployeeBuilder getBaseBuilderParser(ResultSet rs) throws SQLException {
    return Employee.builder()
        .name(rs.getString(NAME_COLUMN))
        .costPerHour(rs.getInt(COST_COLUMN));
  }
}
