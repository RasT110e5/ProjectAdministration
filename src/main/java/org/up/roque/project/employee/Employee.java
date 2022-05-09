package org.up.roque.project.employee;

import org.up.roque.project.PartOfProject;
import org.up.roque.project.Project;

import java.util.ArrayList;
import java.util.List;

public class Employee implements PartOfProject {
  private final List<Project> projects;

  public Employee() {
    this.projects = new ArrayList<>();
  }

  @Override
  public boolean isPartOfProject(Project project) {
    return this.projects.contains(project);
  }

  @Override
  public void assignToProject(Project project) {
    this.projects.add(project);
  }

  public void removeFromProject(Project project) {
    this.projects.remove(project);
  }
}
