package org.up.roque.project.task;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.DataAccessException;
import org.up.roque.project.Project;
import org.up.roque.project.util.ProcessingException;
import org.up.roque.project.util.ServiceTemplate;

import java.util.Set;

@Slf4j
public class TaskServiceImpl extends ServiceTemplate<Task, Integer> implements TaskService {
  private final TaskCrudRepository taskRepository;

  public TaskServiceImpl(TaskCrudRepository repository) {
    super(repository);
    this.taskRepository = repository;
  }

  @Override
  public Set<Task> findAllBy(Project project) {
    try {
      return this.taskRepository.findAllByProject(project);
    } catch (DataAccessException e) {
      log.warn("Exception while trying to get tasks for project: '{}', exception: {}", project, e.toString());
      throw new ProcessingException("Tasks couldn't be found");
    }
  }
}
