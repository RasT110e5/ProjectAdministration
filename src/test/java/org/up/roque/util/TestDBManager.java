package org.up.roque.util;

import lombok.SneakyThrows;
import org.h2.jdbcx.JdbcDataSource;
import org.up.roque.db.DBTemplate;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDBManager extends DBTemplate {
  public static final File SCHEMA_FILE = new File("src/main/resources/schema.sql");
  private static final File DB_FILE = new File("h2/test.mv.db");
  private static final String USER = "sa";

  public TestDBManager() {
    JdbcDataSource h2Datasource = new JdbcDataSource();
    h2Datasource.setUrl(getJdbcUrl());
    h2Datasource.setUser(USER);
    this.dataSource = h2Datasource;
  }

  private String getJdbcUrl() {
    return "jdbc:h2:%s".formatted(getDbPathWithoutExtension());
  }

  private String getDbPathWithoutExtension() {
    return DB_FILE.getAbsolutePath().replace(".mv.db", "");
  }

  private String readSchemaScript() throws IOException {
    return String.join("", Files.readAllLines(SCHEMA_FILE.getAbsoluteFile().toPath()));
  }

  @Override
  @SneakyThrows
  public void initSchema() {
    teardown();
    String schemaScript = readSchemaScript();
    update(schemaScript);
  }

  @Override
  public void teardown() {
    if (DB_FILE.exists() && !DB_FILE.delete())
      //TODO: replace this exception with a custom one
      throw new RuntimeException();
  }

  public boolean isHealthy() {
    if (!DB_FILE.exists())
      //TODO: replace this exception with a custom one
      throw new RuntimeException();
    return super.isHealthy();
  }
}
