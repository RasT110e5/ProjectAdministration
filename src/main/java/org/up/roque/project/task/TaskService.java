package org.up.roque.project.task;

import org.up.roque.project.Project;
import org.up.roque.project.util.Service;

import java.util.Set;

public interface TaskService extends Service<Task, Integer> {
  Set<Task> findAllByProject(Project project);
}
