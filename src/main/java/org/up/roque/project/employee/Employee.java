package org.up.roque.project.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.up.roque.project.PartOfProject;
import org.up.roque.project.Project;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class Employee implements PartOfProject {
  private Integer id;
  private String name;
  private Integer costPerHour;
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
