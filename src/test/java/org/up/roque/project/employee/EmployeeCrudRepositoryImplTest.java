package org.up.roque.project.employee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.up.roque.db.DataAccessException;
import org.up.roque.util.Entities;
import org.up.roque.util.TestDBTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeCrudRepositoryImplTest {
  TestDBTemplate testDBTemplate = new TestDBTemplate();
  EmployeeCrudRepositoryImpl repository = new EmployeeCrudRepositoryImpl(testDBTemplate);

  @BeforeEach
  public void setup() {
    testDBTemplate.initSchema();
  }

//  @AfterEach
  public void teardown() {
    testDBTemplate.teardown();
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
    repository.save(anotherEmployee);
    assertThat(repository.getOne(employee.getId())).isEqualTo(employee)
        .extracting(Employee::getCostPerHour).isEqualTo(costPerHour);
  }

  @Test
  @DisplayName("should return 1 employee if given id")
  void employeeCrudRepositoryImplTest_3() {
    Employee persistedEmployee = saveNewEmployee();
    assertThat(repository.getOne(1)).isEqualTo(persistedEmployee);
  }

  @Test
  @DisplayName("should throw an exception if get doesn't find an entity")
  void employeeCrudRepositoryImplTest_5() {
    assertThrows(DataAccessException.class,
        () -> repository.getOne(123));
  }

  @Test
  @DisplayName("should delete an entity correctly")
  void employeeCrudRepositoryImplTest_2() {
    saveNewEmployee();
    repository.delete(1);
    assertThrows(RuntimeException.class,
        () -> repository.getOne(1));
  }

  @Test
  @DisplayName("should be able to select all from table")
  void employeeCrudRepositoryImplTest_4() {
    saveNewEmployee();
    saveNewEmployee();
    saveNewEmployee();
    assertThat(repository.findAll()).isNotEmpty().hasSize(3);
  }

}