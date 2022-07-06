package org.up.roque.project;

import org.up.roque.db.util.CrudRepositoryTemplate;
import org.up.roque.db.util.DBTemplate;
import org.up.roque.db.util.SqlParam;
import org.up.roque.project.employee.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ProjectCrudRepositoryImpl extends CrudRepositoryTemplate<Project, Integer>
    implements ProjectCrudRepository {
  private static final String NAME_COLUMN = "NAME";
  private static final String ID_COLUMN = "ID";
  private static final String TABLE = "PROJECT";

  private final DBTemplate template;

  public ProjectCrudRepositoryImpl(DBTemplate dbTemplate) {
    super(dbTemplate, TABLE, Integer.class, ID_COLUMN, NAME_COLUMN);
    this.template = dbTemplate;
  }

  @Override
  protected List<SqlParam> getPropertiesAsParam(Project entity) {
    return List.of(new SqlParam(entity.getName()));
  }

  @Override
  protected List<SqlParam> getIdAsParam(Integer id) {
    return List.of(new SqlParam(id));
  }

  @Override
  protected DBTemplate.ResultSetParser<Project> getResultSetParser() {
    return rs -> getBaseBuilderParser(rs)
        .id(rs.getInt(ID_COLUMN))
        .build();
  }

  @Override
  protected DBTemplate.ResultSetParser<Project> getResultSetWithId(Integer id) {
    return rs -> getBaseBuilderParser(rs)
        .id(id)
        .build();
  }

  @Override
  protected void refreshRelationalTables(Project entity) {
    template.delete("DELETE FROM EMPLOYEE_PROJECT WHERE PROJECT=?", getIdAsParam(entity.getId()));
    for (Employee employee : entity.getEmployees()) {
      template.save("INSERT INTO EMPLOYEE_PROJECT (PROJECT, EMPLOYEE) VALUES (?,?)",
          List.of(new SqlParam(entity.getId()), new SqlParam(employee.getId())));
    }
  }

  @Override
  protected void deleteFromRelationalTables(Integer id) {
    template.delete("DELETE FROM EMPLOYEE_PROJECT WHERE PROJECT=?", getIdAsParam(id));
  }

  private Project.ProjectBuilder getBaseBuilderParser(ResultSet rs) throws SQLException {
    return Project.builder()
        .name(rs.getString(NAME_COLUMN));
  }

  @Override
  public Set<Integer> getEmployeeIds(Project entity) {
    return template.query(
        "SELECT EMPLOYEE FROM EMPLOYEE_PROJECT WHERE PROJECT = ?",
        getIdAsParam(entity.getId()),
        rs -> rs.getInt("EMPLOYEE")
    );
  }

  @Override
  public Set<Integer> getTasksIds(Project entity) {
    return Collections.emptySet();
  }
}
