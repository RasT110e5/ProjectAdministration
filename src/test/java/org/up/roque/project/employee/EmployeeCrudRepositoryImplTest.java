package org.up.roque.project.employee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.up.roque.util.Entities;
import org.up.roque.util.TestDBManager;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeCrudRepositoryImplTest {
  TestDBManager testDBManager = new TestDBManager();
  EmployeeCrudRepositoryImpl repository = new EmployeeCrudRepositoryImpl(testDBManager);

  @BeforeEach
  public void setup() {
    testDBManager.initSchema();
  }

  @AfterEach
  public void teardown() {
    testDBManager.teardown();
  }

  @Test
  @DisplayName("should be able to save new Employee")
  void employeeCrudRepositoryImplTest_() {
    Employee employee = Entities.randomEmployee();
    repository.save(employee);
    assertThat(employee.getId()).isNotNull().isEqualTo(1);
  }
}