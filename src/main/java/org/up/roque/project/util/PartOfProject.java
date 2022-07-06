package org.up.roque.project.util;

import org.up.roque.project.Project;

public interface PartOfProject {
  boolean isPartOfProject(Project project);

  void assignToProject(Project project);

}
