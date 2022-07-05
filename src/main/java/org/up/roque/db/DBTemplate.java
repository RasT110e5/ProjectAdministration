package org.up.roque.db;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public abstract class DBTemplate {
  protected DataSource dataSource;

  public abstract void initSchema();

  public abstract void teardown();

  public abstract boolean healthCheck();

  public void save(String sql, List<SqlParam> params) {
    log.info("Saving existing entity with {}", sql);
    try (Transaction transaction = new Transaction(dataSource)) {
      transaction.save(sql, params);
    }
  }

  public <T> T save(String sql, Class<T> idType, List<SqlParam> params) {
    log.info("Saving new entity with {}", sql);
    try (Transaction transaction = new Transaction(dataSource)) {
      ResultSet rs = transaction.save(sql, params);
      return getGeneratedId(idType, rs);
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  private <T> T getGeneratedId(Class<T> idType, ResultSet rs) throws SQLException {
    if (rs.next())
      return rs.getObject(1, idType);
    else
      throw new DataAccessException("No row was returned on insert, Id should've been returned");
  }

  public void update(String sql) {
    log.info("Updating DB with {}", sql);
    try (Transaction transaction = new Transaction(dataSource)) {
      transaction.update(sql);
    }
  }

  protected boolean isHealthy() {
    log.info("Checking health of the DB");
    try (Transaction transaction = new Transaction(dataSource)) {
      ResultSet rs = transaction.query("SELECT 1");
      return rs.next();
    } catch (SQLException e) {
      log.error("DB is not healthy!!");
      return false;
    }
  }

  public void delete(String sql, List<SqlParam> params) {
    log.info("Deleting from DB with: {}", sql);
    try (Transaction transaction = new Transaction(dataSource)) {
      transaction.update(sql, params);
    }
  }

  public <T> Set<T> query(String sql, ResultSetParser<T> parser) {
    try (Transaction transaction = new Transaction(dataSource)) {
      ResultSet rs = transaction.query(sql);
      return parseQueryResultSet(parser, rs);
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  public <T> Set<T> query(String sql, List<SqlParam> params, ResultSetParser<T> parser) {
    try (Transaction transaction = new Transaction(dataSource)) {
      ResultSet rs = transaction.query(sql, params);
      return parseQueryResultSet(parser, rs);
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  private <T> Set<T> parseQueryResultSet(ResultSetParser<T> parser, ResultSet rs) throws SQLException {
    Set<T> entities = new HashSet<>();
    while (rs.next())
      entities.add(parser.parseRows(rs));
    return entities;
  }


  public interface ResultSetParser<T> {
    T parseRows(ResultSet rs) throws SQLException;
  }

  private static class Transaction implements AutoCloseable {
    private final Connection connection;

    private Transaction(DataSource dataSource) {
      try {
        Connection conn = dataSource.getConnection();
        conn.setAutoCommit(false);
        this.connection = conn;
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public ResultSet save(String sql, List<SqlParam> params) {
      try {
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        setParameters(statement, params);
        statement.executeUpdate();
        return statement.getGeneratedKeys();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    private void setParameters(PreparedStatement statement, List<SqlParam> params) throws SQLException {
      for (int i = 0; i < params.size(); i++) params.get(i).setIn(statement, i + 1);
    }

    public void update(String sql, List<SqlParam> params) {
      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        setParameters(statement, params);
        statement.executeUpdate();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public void update(String sql) {
      try (Statement statement = connection.createStatement()) {
        statement.executeUpdate(sql);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public ResultSet query(String sql, List<SqlParam> params) {
      try {
        PreparedStatement statement = connection.prepareStatement(sql);
        setParameters(statement, params);
        return statement.executeQuery();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public ResultSet query(String sql) {
      try {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void close() {
      try {
        connection.commit();
        connection.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

  }
}

