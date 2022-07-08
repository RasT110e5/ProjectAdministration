package org.up.roque.project.task.comment;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.util.CrudRepositoryTemplate;
import org.up.roque.db.util.DBTemplate;
import org.up.roque.db.util.SqlParam;
import org.up.roque.project.Project;
import org.up.roque.project.task.Task;
import org.up.roque.project.task.TaskCrudRepository;
import org.up.roque.project.task.TaskStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Slf4j
public class CommentCrudRepositoryImpl extends CrudRepositoryTemplate<Comment, Integer>
    implements CommentCrudRepository {

  public static final String TABLE = "COMMENT";
  public static final String ID_COLUMN = "ID";
  public static final String CONTENT_COLUMN = "CONTENT";
  public static final String TASK_COLUMN = "TASK";

  private final TaskCrudRepository taskRepo;

  public CommentCrudRepositoryImpl(DBTemplate template, TaskCrudRepository taskCrudRepository) {
    super(template, TABLE, Integer.class, ID_COLUMN, CONTENT_COLUMN, TASK_COLUMN);
    this.taskRepo = taskCrudRepository;
  }

  @Override
  protected List<SqlParam> getPropertiesAsParam(Comment entity) {
    return List.of(new SqlParam(entity.getContent()), new SqlParam(entity.getTask().getId()));
  }

  @Override
  protected List<SqlParam> getIdAsParam(Integer integer) {
    return List.of(new SqlParam(integer));
  }

  @Override
  protected DBTemplate.ResultSetParser<Comment> getResultSetParser() {
    return rs -> getBaseBuilderParser(rs).id(rs.getInt(ID_COLUMN)).build();
  }

  private Comment.CommentBuilder getBaseBuilderParser(ResultSet rs) throws SQLException {
    return Comment.builder().content(rs.getString(CONTENT_COLUMN)).task(taskRepo.getOne(rs.getInt(TASK_COLUMN)));
  }

  @Override
  protected DBTemplate.ResultSetParser<Comment> getResultSetWithId(Integer id) {
    return rs -> getBaseBuilderParser(rs).id(rs.getInt(id)).build();
  }

  @Override
  protected void refreshRelationalTables(Comment entity) {
    log.info("No need to update relations yet");
  }

  @Override
  protected void deleteFromRelationalTables(Integer integer) {
    log.info("No need to update relations yet");
  }

  @Override
  public Set<Comment> findAllByTask(Task task) {
    return template.query(select + " WHERE TASK=?",
        getIdAsParam(task.getId()), getResultSetParser());
  }
}
