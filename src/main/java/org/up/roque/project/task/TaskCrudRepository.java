package org.up.roque.project.task;

import org.up.roque.project.util.CrudRepository;
import org.up.roque.project.Project;

import java.util.Set;

public interface TaskCrudRepository extends CrudRepository<Task, Integer> {
  Set<Task> findAllByProject(Project project);
}
