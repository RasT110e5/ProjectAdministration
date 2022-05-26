package org.up.roque.db;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.up.roque.util.TestDBManager;

import static org.junit.jupiter.api.Assertions.*;

class DBTemplateTest {
  TestDBManager manager = new TestDBManager();

  @Test
  @DisplayName("should create schema correctly")
  void employeeCrudRepositoryImplTest_() {
    try {
      manager.initSchema();
      manager.isHealthy();
      manager.teardown();
    } catch (Exception e){
      fail("Schema initiation shouldn't throw any exceptions while testing", e);
    }
  }

}