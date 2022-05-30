package org.up.roque.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;
import org.mockito.internal.invocation.finder.AllInvocationsFinder;
import org.up.roque.db.DBTemplate;
import org.up.roque.db.DataAccessException;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
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
    log.info("Initiating schema for testing");
    teardown();
    String schemaScript = readSchemaScript();
    update(schemaScript);
  }

  @Override
  public void teardown() {
    log.info("Tearing down DB (deleting file)");
    if (DB_FILE.exists() && !DB_FILE.delete()) {
      throw new DataAccessException("Teardown was failed, either due to non existent" +
          " DB or error on deletion from filesystem");
    }
  }

  public boolean isHealthy() {
    if (!DB_FILE.exists()){
      log.warn("DB does not exist");
      throw new DataAccessException("Cannot perform health check on a non existent DB");
    }
    return super.isHealthy();
  }
}
