package org.up.roque.db;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RequiredArgsConstructor
public abstract class H2Manager {

  public static final String USERNAME = "sa";
  public static final String SECRET = "";
  public static final String H2_DIR = "h2/";
  public static final String DRIVER = "org.h2.Driver";

  public Connection connect(String dbName) {
    if (isDriverInClassPath()) {
      return createConnection(dbName);
    } else {
      throw new DataAccessException("H2 Driver is not loaded.");
    }
  }

  private Connection createConnection(String dbName) {
    try {
      String url = String.format("jdbc:h2:%s/%s", getCurrentH2Dir(), dbName);
      Connection connection = DriverManager.getConnection(url, USERNAME, SECRET);
      connection.setAutoCommit(false);
      return connection;
    } catch (SQLException e) {
      throw new DataAccessException("Error while opening connection with h2 embedded db.");
    }
  }

  public boolean isDriverInClassPath() {
    try {
      Class.forName(DRIVER);
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

  public String getCurrentH2Dir() {
    File file = new File(H2_DIR);
    return file.getAbsolutePath();
  }
}
