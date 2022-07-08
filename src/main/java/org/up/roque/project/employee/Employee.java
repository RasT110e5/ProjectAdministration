package org.up.roque.project.employee;

import lombok.*;
import org.up.roque.db.NamedEntity;
import org.up.roque.project.PartOfProject;
import org.up.roque.project.Project;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee implements PartOfProject, NamedEntity<Integer> {
  @EqualsAndHashCode.Include
  private Integer id;
  private String name;
  private Integer costPerHour;
  @ToString.Exclude
  @Builder.Default
  private final List<Project> projects = new ArrayList<>();

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

  public String getFormatedNameAndCost() {
    return "Name: %s - Cost per hour: $%d".formatted(this.name, this.costPerHour);
  }
}
