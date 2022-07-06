package org.up.roque.project.task.db;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.util.CrudRepositoryTemplate;
import org.up.roque.db.util.DBTemplate;
import org.up.roque.db.util.SqlParam;
import org.up.roque.project.task.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

  public TaskCrudRepositoryImpl(DBTemplate template) {
    super(template, TASK, Integer.class, ID_COLUMN,
        NAME_COLUMN,
        DESCRIPTION_COLUMN,
        ESTIMATED_HOURS_COLUMN,
        CREATED_DATE_COLUMN,
        ACTUAL_DURATION_COLUMN);
  }

  @Override
  protected List<SqlParam> getPropertiesAsParam(Task entity) {
    return List.of(
        new SqlParam(entity.getName()),
        new SqlParam(entity.getDescription()),
        new SqlParam(entity.getEstimatedHours()),
        new SqlParam(entity.getCreatedDate()),
        new SqlParam(entity.getActualDuration())
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
        .actualDuration(rs.getInt(ACTUAL_DURATION_COLUMN));
  }

  @Override
  protected void refreshRelationalTables(Task entity) {
    log.info("No need to update relations yet");
  }

  @Override
  protected void deleteFromRelationalTables(Integer integer) {
    log.info("No need to update relations yet");
  }
}
