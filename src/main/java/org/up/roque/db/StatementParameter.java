package org.up.roque.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public enum StatementParameter {
  STRING {
    @Override
    public void setInPreparedStatement(PreparedStatement s, int index, Object val) throws SQLException {
      s.setString(index, (String) val);
    }
  },
  INTEGER {
    @Override
    public void setInPreparedStatement(PreparedStatement s, int index, Object val) throws SQLException {
      s.setInt(index, (Integer) val);
    }
  };

  public abstract void setInPreparedStatement(PreparedStatement s, int index, Object val) throws SQLException;
}
