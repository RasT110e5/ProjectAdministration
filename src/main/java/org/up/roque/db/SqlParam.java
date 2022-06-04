package org.up.roque.db;

import lombok.Value;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Value
public class SqlParam {
  ParameterType type;
  Object value;

  public SqlParam(String val) {
    this.type = ParameterType.STRING;
    this.value = val;
  }

  public SqlParam(Integer val) {
    this.type = ParameterType.INTEGER;
    this.value = val;
  }

  public void setIn(PreparedStatement statement, int index) throws SQLException {
    this.type.setInPreparedStatement(statement, index, this.value);
  }

  public enum ParameterType {
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
}
