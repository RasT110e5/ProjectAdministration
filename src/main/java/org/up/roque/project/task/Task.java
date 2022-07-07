package org.up.roque.project.task;

import lombok.*;
import org.up.roque.db.Entity;
import org.up.roque.project.PartOfProject;
import org.up.roque.project.Project;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.task.comment.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task implements PartOfProject, Entity<Integer> {
  @EqualsAndHashCode.Include
  private Integer id;
  private String name;
  private String description;
  private Integer estimatedHours;
  private Integer actualDuration;
  private TaskStatus status;
  private Project project;
  private Employee assignedEmployee;
  @Builder.Default
  private LocalDateTime createdDate = LocalDateTime.now();
  @ToString.Exclude
  @Builder.Default
  private final List<Comment> comments = new ArrayList<>();

  @Override
  public boolean isPartOfProject(Project project) {
    return this.project.equals(project);
  }

  @Override
  public void assignToProject(Project project) {
    this.project = project;
  }

  public void comment(String content) {
    Comment comment = new Comment(content);
    comment.setTask(this);
    this.comments.add(comment);
  }

  public void deleteComment(Comment comment) {
    this.comments.remove(comment);
  }

  public void assignEmployee(Employee employee) {
    this.assignedEmployee = employee;
  }

  public boolean isAssigned() {
    return assignedEmployee != null;
  }
}
