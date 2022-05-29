package org.up.roque.db;

import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class DBTemplate {
  protected DataSource dataSource;

  public abstract void initSchema();

  public abstract void teardown();

  public <T> T save(String sql, Class<T> idType, LinkedHashMap<StatementParameter, Object> params) {
    try (Transaction transaction = new Transaction(dataSource)) {
      ResultSet rs = transaction.save(sql, params);
      return getGeneratedId(idType, rs);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private <T> T getGeneratedId(Class<T> idType, ResultSet rs) throws SQLException {
    if (rs.next())
      return rs.getObject(1, idType);
    else
      throw new RuntimeException();
  }

  public void update(String sql) {
    try (Transaction transaction = new Transaction(dataSource)) {
      transaction.update(sql);
    }
  }

  protected boolean isHealthy() {
    try (Transaction transaction = new Transaction(dataSource)) {
      ResultSet rs = transaction.query("SELECT 1");
      return rs.next();
    } catch (RuntimeException | SQLException e) {
      return false;
    }
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

    @SneakyThrows
    public ResultSet save(String sql, LinkedHashMap<StatementParameter, Object> params) {
      try {
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int index = 1;
        for (Map.Entry<StatementParameter, Object> param : params.entrySet())
          param.getKey().setInPreparedStatement(statement, index++, param.getValue());
        statement.executeUpdate();
        return statement.getGeneratedKeys();
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

    public ResultSet query(String sql) {
      try (Statement statement = connection.createStatement()) {
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

