package org.up.roque.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.task.Task;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

  private Project project;
  private Task task;
  private Employee employee;

  @BeforeEach
  void setUp() {
    project = new Project();
    task = new Task();
    employee = Employee.builder().build();
  }

  @Test
  @DisplayName("project should be able to be assigned tasks")
  void projectTest_() {
    project.assign(task);
    assertTrue(project.hasTask(task));
  }

  @Test
  @DisplayName("when assigning a task the project should be added to the task")
  void projectTest_1() {
    project.assign(task);
    assertTrue(task.isPartOfProject(project));
  }

  @Test
  @DisplayName("project should be able to be assigned employees")
  void projectTest_2() {
    project.assign(employee);
    assertTrue(project.hasEmployee(employee));
  }

  @Test
  @DisplayName("when assigning employee to the project the project should be added to the employee's projects")
  void projectTest_3() {
    project.assign(employee);
    assertTrue(employee.isPartOfProject(project));
  }

  @Test
  @DisplayName("employees should be removable from a project")
  void projectTest_4() {
    project.assign(employee);
    project.remove(employee);
    assertFalse(project.hasEmployee(employee));
  }

  @Test
  @DisplayName("when employees are removed from a project the project should be removed from the employee's project list")
  void projectTest_5() {
    project.assign(employee);
    project.remove(employee);
    assertFalse(employee.isPartOfProject(project));
  }

}