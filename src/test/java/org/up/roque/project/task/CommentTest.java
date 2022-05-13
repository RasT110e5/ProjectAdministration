package org.up.roque.project.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CommentTest {
  @Test
  @DisplayName("comments should be editable")
  void commentTest_() {
    Comment comment = new Comment("This is task depends on asdfadsf");
    String newContent = "This task depends on this other task";
    comment.edit(newContent);
    assertEquals(comment.getContent(), newContent);
  }
}