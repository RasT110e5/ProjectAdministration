package org.up.roque.project;

import org.up.roque.db.CrudRepositoryTemplate;
import org.up.roque.db.DBTemplate;
import org.up.roque.db.SqlParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProjectCrudRepositoryImpl extends CrudRepositoryTemplate<Project, Integer> implements ProjectCrudRepository {
  private static final String NAME_COLUMN = "NAME";
  private static final String ID_COLUMN = "ID";
  private static final String TABLE = "PROJECT";

  public ProjectCrudRepositoryImpl(DBTemplate dbTemplate) {
    super(dbTemplate, TABLE, Integer.class, ID_COLUMN, NAME_COLUMN);
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

  private Project.ProjectBuilder getBaseBuilderParser(ResultSet rs) throws SQLException {
    return Project.builder()
        .name(rs.getString(NAME_COLUMN));
  }
}
