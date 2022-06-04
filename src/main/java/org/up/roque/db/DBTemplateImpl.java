package org.up.roque.db;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
public class DBTemplateImpl extends DBTemplate {
  public static final File SCHEMA_FILE = new File("src/main/resources/schema.sql");
  public static final File DATA_FILE = new File("src/main/resources/data.sql");
  private static final File DB_FILE = new File("h2/dev.mv.db");
  private static final String USER = "sa";

  public DBTemplateImpl() {
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
    return readFile(SCHEMA_FILE);
  }

  private String readDataScript() throws IOException {
    return readFile(DATA_FILE);
  }

  private String readFile(File file) throws IOException {
    return String.join("", Files.readAllLines(file.getAbsoluteFile().toPath()));
  }

  @Override
  public void initSchema() {
    if (!DB_FILE.exists()) {
      try {
        log.info("Initiating schema for testing");
        String schemaScript = readSchemaScript();
        update(schemaScript);
        String dataScript = readDataScript();
        update(dataScript);
      } catch (IOException e) {
        throw new DataAccessException("Schema scripts couldn't be read");
      }
    }
  }

  @Override
  public void teardown() {
    log.info("Tearing down DB (deleting file)");
    if (DB_FILE.exists() && !DB_FILE.delete())
      throw new DataAccessException("Teardown failed, either due to non existent" +
          " DB or error on deletion from filesystem");
  }

  @Override
  public boolean healthCheck() {
    if (!DB_FILE.exists()) {
      log.warn("DB does not exist");
      throw new DataAccessException("Cannot perform health check on a non existent DB");
    }
    return super.isHealthy();
  }
}
