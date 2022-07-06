package org.up.roque.db.util;

import org.up.roque.db.DataAccessException;
import org.up.roque.db.Entity;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class CrudRepositoryTemplate<T extends Entity<ID>, ID> {
  public static final String DELETE_STATEMENT = "DELETE FROM %s WHERE ID=?";
  public static final String SELECT_STATEMENT = "SELECT %s FROM %s";
  public static final String UPDATE_STATEMENT = "UPDATE %s SET %s WHERE %s=?";
  public static final String INSERT_STATEMENT = "INSERT INTO %s (%s) VALUES (%s)";

  private final String select;
  private final String update;
  private final String insert;
  private final String delete;
  private final Class<ID> idClass;

  protected final DBTemplate template;

  protected CrudRepositoryTemplate(DBTemplate template, String table, Class<ID> idClass,
                                   String idColumn, String... columns) {
    this.template = template;
    this.idClass = idClass;
    this.delete = DELETE_STATEMENT.formatted(table);
    this.select = SELECT_STATEMENT.formatted(formatColumnsForSelect(idColumn, columns), table);
    this.update = UPDATE_STATEMENT.formatted(table, formatColumnsForUpdate(columns), idColumn);
    this.insert = INSERT_STATEMENT.formatted(table, formatColumnsForInsert(columns),
        addNecessaryParametersPlaceholders(columns));
  }

  private String addNecessaryParametersPlaceholders(String[] columns) {
    return Arrays.stream(columns).map(c -> "?").collect(Collectors.joining(","));
  }

  private String formatColumnsForInsert(String[] columns) {
    return String.join(",", columns);
  }

  private String formatColumnsForSelect(String idColumn, String[] columns) {
    return idColumn + "," + String.join(",", columns);
  }

  private String formatColumnsForUpdate(String[] columns) {
    return Arrays.stream(columns).map(c -> c + "=?").collect(Collectors.joining(","));
  }

  protected abstract List<SqlParam> getPropertiesAsParam(T entity);

  protected abstract List<SqlParam> getIdAsParam(ID id);

  protected abstract DBTemplate.ResultSetParser<T> getResultSetParser();

  protected abstract DBTemplate.ResultSetParser<T> getResultSetWithId(ID id);

  protected abstract void refreshRelationalTables(T entity);

  protected abstract void deleteFromRelationalTables(ID id);

  public T save(T entity) {
    if (entity.getId() == null)
      insert(entity);
    else
      update(entity);
    refreshRelationalTables(entity);
    return entity;
  }

  private void update(T entity) {
    template.save(update, getAllPropertiesAsParam(entity));
  }

  private void insert(T entity) {
    ID id = template.save(insert, idClass, getPropertiesAsParam(entity));
    entity.setId(id);
  }

  private List<SqlParam> getAllPropertiesAsParam(T entity) {
    return Stream.of(getPropertiesAsParam(entity), getIdAsParam(entity.getId()))
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  public void delete(ID id) {
    template.delete(delete, getIdAsParam(id));
    deleteFromRelationalTables(id);
  }

  public Set<T> findAll() {
    return template.query(select, getResultSetParser());
  }

  public T getOne(ID id) {
    try {
      return template.query(select + " WHERE ID=?", getIdAsParam(id), getResultSetWithId(id)).iterator().next();
    } catch (NoSuchElementException e) {
      throw new DataAccessException("No entity found for ID=%s".formatted(id));
    }
  }
}
