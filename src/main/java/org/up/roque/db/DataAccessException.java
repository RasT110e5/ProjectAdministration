package org.up.roque.db;

import javax.xml.crypto.Data;
import java.sql.SQLException;

public class DataAccessException extends RuntimeException {
  public DataAccessException(SQLException e) {
    super(e.toString());
  }

  public DataAccessException(String message) {
    super(message);
  }
}
