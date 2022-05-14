package org.up.roque.util;

import lombok.RequiredArgsConstructor;
import org.up.roque.db.H2Manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@RequiredArgsConstructor
public class TestDBManager extends H2Manager {

  public void initSchema() {
    File schema = new File("src/test/resources/schema.sql");
    try (Connection connection = connect("test")){
      String schemaScript = String.join("", Files.readAllLines(schema.getAbsoluteFile().toPath()));
      Statement statement = connection.createStatement();
      statement.executeUpdate(schemaScript);
      connection.commit();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
