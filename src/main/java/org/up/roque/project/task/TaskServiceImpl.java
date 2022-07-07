package org.up.roque.project.task;

import org.up.roque.project.Project;
import org.up.roque.project.util.ServiceTemplate;

import java.util.Set;

public class TaskServiceImpl extends ServiceTemplate<Task, Integer> implements TaskService {
  private final TaskCrudRepository repository;
  public TaskServiceImpl(TaskCrudRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public Set<Task> findAllByProject(Project project) {
    return this.repository.findAllByProject(project);
  }
}
