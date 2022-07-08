package org.up.roque.project;

import org.up.roque.project.util.CrudRepository;

import java.util.Set;

public interface ProjectCrudRepository extends CrudRepository<Project, Integer> {
  Set<Integer> getEmployeeIds(Project entity);
}
