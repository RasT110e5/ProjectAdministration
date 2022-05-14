package org.up.roque.project.employee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.up.roque.util.TestDBManager;

import java.io.IOException;

class EmployeeCrudRepositoryImplTest {
  private final TestDBManager manager = new TestDBManager();

  @Test
  @DisplayName("should create schema correctly")
  void employeeCrudRepositoryImplTest_() throws IOException {
    manager.initSchema();
  }

}