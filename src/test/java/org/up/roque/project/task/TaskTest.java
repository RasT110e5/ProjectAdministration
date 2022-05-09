package org.up.roque.project.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
  @Test
  @DisplayName("tasks should be commentable")
  void taskTest_() {
    Task task = new Task();
    task.comment("Analysis completed, task can move forward.");
    assertThat(task.getComments()).isNotEmpty();
  }

  @Test
  @DisplayName("comments should have their task as a property")
  void taskTest_1() {
    Task task = new Task();
    task.comment("Work is done.");
    assertThat(task.getComments()).isNotEmpty()
        .allSatisfy(c -> assertEquals(c.getTask(), task));
  }

}