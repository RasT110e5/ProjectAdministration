package org.up.roque.project.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.up.roque.project.employee.Employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {

  private Task createTaskWithComment(String content) {
    Task task = new Task();
    task.comment(content);
    return task;
  }

  @Test
  @DisplayName("tasks should be commentable")
  void taskTest_() {
    Task task = createTaskWithComment("Analysis completed, task can move forward.");
    assertThat(task.getComments()).isNotEmpty();
  }

  @Test
  @DisplayName("comments should have their task as a property")
  void taskTest_1() {
    Task task = createTaskWithComment("Work is done.");
    assertThat(task.getComments()).isNotEmpty()
        .allSatisfy(c -> assertEquals(c.getTask(), task));
  }

  @Test
  @DisplayName("comments should be deletable from the task")
  void taskTest_2() {
    Task task = createTaskWithComment("Work is done.");
    Comment comment = task.getComments().get(0);
    task.deleteComment(comment);
    assertThat(task.getComments()).isEmpty();
  }

  @Test
  @DisplayName("task should have an assigned employee")
  void taskTest_3() {
    Employee employee = Employee.builder().build();
    Task task = new Task();
    task.assignEmployee(employee);
    assertTrue(task.isAssigned());
    assertEquals(employee, task.getAssignedEmployee());
  }
}