package org.up.roque.project.task;

import lombok.Getter;
import org.up.roque.project.PartOfProject;
import org.up.roque.project.Project;

import java.util.ArrayList;
import java.util.List;

public class Task implements PartOfProject {
  private Project project;
  @Getter
  private final List<Comment> comments;

  public Task(){
    this.comments = new ArrayList<>();
  }

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
}
