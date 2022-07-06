package org.up.roque.project.task;

import org.up.roque.db.CrudRepository;
import org.up.roque.project.util.ServiceTemplate;

public class TaskServiceImpl extends ServiceTemplate<Task, Integer> implements TaskService {
  public TaskServiceImpl(TaskCrudRepository repository) {
    super(repository);
  }
}
