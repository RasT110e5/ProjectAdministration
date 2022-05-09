package org.up.roque.project.task;

import lombok.Data;

@Data
public class Comment {
  private String content;
  private Task task;

  public Comment(String content) {
    this.content = content;
  }
}
