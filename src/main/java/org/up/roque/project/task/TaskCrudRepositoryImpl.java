package org.up.roque.project.task;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.util.CrudRepositoryTemplate;
import org.up.roque.db.util.DBTemplate;
import org.up.roque.db.util.SqlParam;
import org.up.roque.project.Project;
import org.up.roque.project.ProjectCrudRepository;
import org.up.roque.project.employee.EmployeeCrudRepository;
import org.up.roque.project.task.comment.CommentCrudRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public class TaskCrudRepositoryImpl extends CrudRepositoryTemplate<Task, Integer>
    implements TaskCrudRepository {
  public static final String TASK = "TASK";
  public static final String ID_COLUMN = "ID";
  public static final String NAME_COLUMN = "NAME";
  public static final String DESCRIPTION_COLUMN = "DESCRIPTION";
  public static final String ESTIMATED_HOURS_COLUMN = "ESTIMATED_HOURS";
  public static final String CREATED_DATE_COLUMN = "CREATED_DATE";
  public static final String ACTUAL_DURATION_COLUMN = "ACTUAL_DURATION";
  public static final String STATUS = "STATUS";
  public static final String ASSIGNED_EMPLOYEE_COLUMN = "ASSIGNED_EMPLOYEE";
  public static final String PROJECT_COLUMN = "PROJECT";

  private final ProjectCrudRepository projectRepo;
  private final EmployeeCrudRepository employeeRepo;

  public TaskCrudRepositoryImpl(DBTemplate template, ProjectCrudRepository projectRepo,
                                EmployeeCrudRepository employeeRepo) {
    super(template, TASK, Integer.class, ID_COLUMN, NAME_COLUMN, DESCRIPTION_COLUMN,
        ESTIMATED_HOURS_COLUMN, CREATED_DATE_COLUMN, ACTUAL_DURATION_COLUMN, STATUS,
        PROJECT_COLUMN, ASSIGNED_EMPLOYEE_COLUMN);
    this.projectRepo = projectRepo;
    this.employeeRepo = employeeRepo;
  }

  @Override
  protected List<SqlParam> getPropertiesAsParam(Task entity) {
    return List.of(
        new SqlParam(entity.getName()),
        new SqlParam(entity.getDescription()),
        new SqlParam(entity.getEstimatedHours()),
        new SqlParam(entity.getCreatedDate()),
        new SqlParam(entity.getActualDuration()),
        new SqlParam(String.valueOf(entity.getStatus())),
        new SqlParam(entity.getProject().getId()),
        new SqlParam(entity.getAssignedEmployee().getId())
    );
  }

  @Override
  protected List<SqlParam> getIdAsParam(Integer integer) {
    return List.of(new SqlParam(integer));
  }

  @Override
  protected DBTemplate.ResultSetParser<Task> getResultSetParser() {
    return rs -> getBaseBuilderParser(rs)
        .id(rs.getInt(ID_COLUMN))
        .build();
  }

  @Override
  protected DBTemplate.ResultSetParser<Task> getResultSetWithId(Integer id) {
    return rs -> getBaseBuilderParser(rs)
        .id(id)
        .build();
  }

  private Task.TaskBuilder getBaseBuilderParser(ResultSet rs) throws SQLException {
    return Task.builder()
        .name(rs.getString(NAME_COLUMN))
        .description(rs.getString(DESCRIPTION_COLUMN))
        .estimatedHours(rs.getInt(ESTIMATED_HOURS_COLUMN))
        .createdDate(rs.getTimestamp(CREATED_DATE_COLUMN).toLocalDateTime())
        .actualDuration(rs.getInt(ACTUAL_DURATION_COLUMN))
        .status(TaskStatus.valueOf(rs.getString(STATUS)))
        .project(projectRepo.getOne(rs.getInt(PROJECT_COLUMN)))
        .assignedEmployee(employeeRepo.getOne(rs.getInt(ASSIGNED_EMPLOYEE_COLUMN)));
  }

  @Override
  protected void refreshRelationalTables(Task entity) {
    log.info("No need to update relations yet");
  }

  @Override
  protected void deleteFromRelationalTables(Integer integer) {
    log.info("No need to update relations yet");
  }

  @Override
  public Set<Task> findAllByProject(Project project) {
    return template.query(select + " WHERE PROJECT=?",
        getIdAsParam(project.getId()), getResultSetParser());
  }
}
