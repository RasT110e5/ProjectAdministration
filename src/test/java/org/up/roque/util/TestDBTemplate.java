package org.up.roque.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;
import org.up.roque.db.util.DBTemplate;
import org.up.roque.db.DataAccessException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
public class TestDBTemplate extends DBTemplate {
  public static final File SCHEMA_FILE = new File("src/main/resources/schema.sql");
  private static final File DB_FILE = new File("h2/test.mv.db");
  private static final String USER = "sa";

  public TestDBTemplate() {
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

  @Override
  public boolean healthCheck() {
    if (!DB_FILE.exists()){
      log.warn("DB does not exist");
      throw new DataAccessException("Cannot perform health check on a non existent DB");
    }
    return super.isHealthy();
  }
}
