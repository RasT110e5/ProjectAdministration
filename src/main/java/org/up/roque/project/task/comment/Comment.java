package org.up.roque.project.task.comment;

import lombok.*;
import org.up.roque.db.Entity;
import org.up.roque.db.NamedEntity;
import org.up.roque.project.task.Task;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comment implements Entity<Integer> {
  @EqualsAndHashCode.Include
  private Integer id;
  private String content;
  @ToString.Exclude
  private Task task;

  public Comment(String content) {
    this.content = content;
  }

  public void edit(String newContent) {
    this.content = newContent;
  }
}
