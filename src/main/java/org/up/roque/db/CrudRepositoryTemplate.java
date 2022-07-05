package org.up.roque.db;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class CrudRepositoryTemplate<T extends Entity<ID>, ID> implements CrudRepository<T, ID> {
  public static final String DELETE_STATEMENT = "DELETE FROM %s WHERE ID=?";
  public static final String SELECT_STATEMENT = "SELECT %s FROM %s";
  public static final String UPDATE_STATEMENT = "UPDATE %s SET %s=? WHERE %s=?";
  public static final String INSERT_STATEMENT = "INSERT INTO %s (%s) VALUES (%s)";
//  private static final String NAME_COLUMN = "NAME";
//  private static final String ID_COLUMN = "ID";

  private final String select;
  private final String update;
  private final String insert;
  private final String delete;
  private final Class<ID> idClass;

  private final DBTemplate template;

  protected CrudRepositoryTemplate(DBTemplate template, String table, Class<ID> idClass, String idColumn, String... columns) {
    this.template = template;
    this.idClass = idClass;
    this.delete = DELETE_STATEMENT.formatted(table);
    this.select = SELECT_STATEMENT.formatted(idColumn + "," + String.join(",", columns), table);
    this.update = UPDATE_STATEMENT.formatted(table,
        formatColumnsForUpdate(columns)
        , idColumn);
    this.insert = INSERT_STATEMENT.formatted(table,
        String.join(",", columns),
        Arrays.stream(columns).map(c -> "?").collect(Collectors.joining(","))
    );
  }

  private String formatColumnsForUpdate(String[] columns) {
    return Arrays.stream(columns).map(c -> c + "=?").collect(Collectors.joining(","));
  }

  protected abstract List<SqlParam> getPropertiesAsParam(T entity);

  protected abstract List<SqlParam> getIdAsParam(ID id);

  protected abstract List<SqlParam> getAllPropertiesAsParam(T entity);

  protected abstract DBTemplate.ResultSetParser<T> getResultSetParser();

  protected abstract DBTemplate.ResultSetParser<T> getResultSetWithId(ID id);

  @Override
  public T save(T entity) {
    if (entity.getId() == null)
      insert(entity);
    else
      update(entity);
    return entity;
  }

  private void update(T entity) {
    template.save(update, getAllPropertiesAsParam(entity));
  }

  private void insert(T entity) {
    ID id = template.save(insert, idClass, getPropertiesAsParam(entity));
    entity.setId(id);
  }

  @Override
  public void delete(ID id) {
    template.delete(delete, getIdAsParam(id));
  }

  @Override
  public Set<T> findAll() {
    return template.query(select, getResultSetParser());
  }

  @Override
  public T getOne(ID id) {
    try {
      return template.query(select + " WHERE ID=?", getIdAsParam(id), getResultSetWithId(id)).iterator().next();
    } catch (NoSuchElementException e) {
      throw new DataAccessException("No employee found for ID=%s".formatted(id));
    }
  }
}
