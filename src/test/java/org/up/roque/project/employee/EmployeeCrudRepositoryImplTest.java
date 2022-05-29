package org.up.roque.project.employee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.up.roque.util.Entities;
import org.up.roque.util.TestDBManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

  private Employee saveNewEmployee() {
    Employee employee = Entities.randomEmployee();
    repository.save(employee);
    return employee;
  }

  @Test
  @DisplayName("should be able to save new Employee")
  void employeeCrudRepositoryImplTest_() {
    Employee employee = saveNewEmployee();
    assertThat(employee.getId()).isNotNull().isEqualTo(1);
  }

  @Test
  @DisplayName("should update entity when calling saving entity with id")
  void employeeCrudRepositoryImplTest_1() {
    Employee employee = saveNewEmployee();
    int costPerHour = 100;
    Employee anotherEmployee = Entities.randomEmployee();
    anotherEmployee.setId(employee.getId());
    anotherEmployee.setCostPerHour(costPerHour);
    anotherEmployee.setName(employee.getName());
    Employee updatedEmployee = repository.save(anotherEmployee);
    assertThat(updatedEmployee).isEqualTo(employee)
        .extracting(Employee::getCostPerHour).isEqualTo(costPerHour);
  }

  @Test
  @DisplayName("should return 1 employee if given id")
  void employeeCrudRepositoryImplTest_3() {
    Employee persistedEmployee = saveNewEmployee();
    assertThat(repository.getOne(1)).isEqualTo(persistedEmployee);
  }

  @Test
  @DisplayName("should delete an entity correctly")
  void employeeCrudRepositoryImplTest_2() {
    saveNewEmployee();
    repository.delete(1);
    assertThrows(RuntimeException.class,
        () -> repository.getOne(1));
  }

}