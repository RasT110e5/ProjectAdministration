package org.up.roque.project.task;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode
public class Comment {
  @Getter
  private String content;
  @Getter
  @Setter
  private Task task;

  public Comment(String content) {
    this.content = content;
  }

  public void edit(String newContent) {
    this.content = newContent;
  }
}
