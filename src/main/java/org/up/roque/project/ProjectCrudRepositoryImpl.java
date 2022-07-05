package org.up.roque.project;

import lombok.RequiredArgsConstructor;
import org.up.roque.db.DBTemplate;
import org.up.roque.db.DataAccessException;
import org.up.roque.db.SqlParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ProjectCrudRepositoryImpl implements ProjectCrudRepository {
  private static final String NAME_COLUMN = "NAME";
  private static final String ID_COLUMN = "ID";
  private static final String TABLE = "PROJECT";

  private static final String DELETE = "DELETE FROM %s WHERE ID=?".formatted(TABLE);
  private static final String SELECT = ("SELECT %s,%s FROM %s")
      .formatted(ID_COLUMN, NAME_COLUMN, TABLE);
  private static final String UPDATE = ("UPDATE %s SET %s=? WHERE %s=?")
      .formatted(TABLE, NAME_COLUMN, ID_COLUMN);
  private static final String INSERT = ("INSERT INTO %s (%s) VALUES (?)")
      .formatted(TABLE, NAME_COLUMN);

  private final DBTemplate template;

  private List<SqlParam> getPropertiesAsParam(Project project) {
    return List.of(new SqlParam(project.getName()));
  }

  private List<SqlParam> getIdAsParam(Integer id) {
    return List.of(new SqlParam(id));
  }

  private List<SqlParam> getAllPropertiesAsParam(Project project) {
    return Stream.of(getPropertiesAsParam(project), getIdAsParam(project.getId()))
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  @Override
  public Project save(Project project) {
    if (project.getId() == null)
      insert(project);
    else
      update(project);
    return project;
  }

  private void update(Project project) {
    template.save(UPDATE, getAllPropertiesAsParam(project));
  }

  private void insert(Project project) {
    Integer id = template.save(INSERT, Integer.class, getPropertiesAsParam(project));
    project.setId(id);
  }

  @Override
  public void delete(Integer id) {
    template.delete(DELETE, getIdAsParam(id));
  }

  @Override
  public Set<Project> findAll() {
    return template.query(SELECT, rs ->
        getBaseBuilderParser(rs)
            .id(rs.getInt(ID_COLUMN))
            .build());
  }

  @Override
  public Project getOne(Integer id) {
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

  private Project.ProjectBuilder getBaseBuilderParser(ResultSet rs) throws SQLException {
    return Project.builder()
        .name(rs.getString(NAME_COLUMN));
  }
}
