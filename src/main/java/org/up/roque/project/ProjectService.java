package org.up.roque.project;

import java.util.Set;

public interface ProjectService {
  Set<Project> findAll();

  void delete(Project project);

  void save(Project project);
}
