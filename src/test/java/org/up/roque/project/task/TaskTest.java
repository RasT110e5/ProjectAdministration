package org.up.roque.project.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.task.comment.Comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {

  private Task createTaskWithComment(String content) {
    Task task = Task.builder().build();
    task.comment(new Comment(content));
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
  @DisplayName("task should have an assigned employee")
  void taskTest_3() {
    Employee employee = Employee.builder().build();
    Task task = Task.builder().build();
    task.assignEmployee(employee);
    assertTrue(task.isAssigned());
    assertEquals(employee, task.getAssignedEmployee());
  }
}