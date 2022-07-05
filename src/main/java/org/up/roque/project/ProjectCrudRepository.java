package org.up.roque.project;

import org.up.roque.db.CrudRepository;

import java.util.Set;

public interface ProjectCrudRepository extends CrudRepository<Project, Integer> {
  Set<Integer> getEmployeeIds(Project entity);
  Set<Integer> getTasksIds(Project entity);
}
