package org.up.roque.db.util;

import lombok.Value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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

  public SqlParam(LocalDateTime val) {
    this.type = ParameterType.TIMESTAMP;
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
    },
    TIMESTAMP {
      @Override
      public void setInPreparedStatement(PreparedStatement s, int index, Object val) throws SQLException {
        s.setTimestamp(index, Timestamp.valueOf((LocalDateTime) val));
      }
    };

    public abstract void setInPreparedStatement(PreparedStatement s, int index, Object val) throws SQLException;
  }
}
