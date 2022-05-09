package org.up.roque.project.task;

import lombok.Getter;
import org.up.roque.project.PartOfProject;
import org.up.roque.project.Project;

public class Task implements PartOfProject {
  @Getter
  private Project project;

  @Override
  public boolean isPartOfProject(Project project) {
    return this.project.equals(project);
  }

  @Override
  public void assignToProject(Project project) {
    this.project = project;
  }
}
