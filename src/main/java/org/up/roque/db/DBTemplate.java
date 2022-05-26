package org.up.roque.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBTemplate {
  protected DataSource dataSource;

  public abstract void initSchema();

  public abstract void teardown();

  public void update(String sql) {
    try (Transaction transaction = new Transaction(dataSource)) {
      transaction.update(sql);
    }
  }

  protected boolean isHealthy() {
    try (Transaction transaction = new Transaction(dataSource)) {
      transaction.query("SELECT 1");
      return true;
    } catch (RuntimeException e) {
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

    public void update(String sql) {
      try (Statement statement = connection.createStatement()) {
        statement.executeUpdate(sql);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public void query(String sql) {
      try (Statement statement = connection.createStatement()) {
        statement.execute(sql);
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

