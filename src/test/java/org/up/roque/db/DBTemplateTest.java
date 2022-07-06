package org.up.roque.db;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.up.roque.util.TestDBTemplate;

import static org.junit.jupiter.api.Assertions.*;

class DBTemplateTest {
  TestDBTemplate manager = new TestDBTemplate();

  @Test
  @DisplayName("should create schema correctly")
  void dbTemplate_() {
    try {
      manager.initSchema();
      manager.healthCheck();
      manager.teardown();
    } catch (Exception e) {
      fail("Schema initiation shouldn't throw any exceptions while testing", e);
    }
  }

}