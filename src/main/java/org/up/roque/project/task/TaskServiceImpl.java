package org.up.roque.project.task;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.DataAccessException;
import org.up.roque.project.Project;
import org.up.roque.project.task.comment.CommentService;
import org.up.roque.project.util.ProcessingException;
import org.up.roque.project.util.ServiceTemplate;

import java.util.Set;

@Slf4j
public class TaskServiceImpl extends ServiceTemplate<Task, Integer> implements TaskService {
  private final TaskCrudRepository taskRepository;
  private final CommentService commentService;

  public TaskServiceImpl(TaskCrudRepository repository, CommentService commentService) {
    super(repository);
    this.taskRepository = repository;
    this.commentService = commentService;
  }

  @Override
  public Set<Task> findAll() {
    try {
      Set<Task> tasks = this.taskRepository.findAll();
      addCommentsToTasks(tasks);
      return tasks;
    } catch (DataAccessException e) {
      log.warn("Exception while trying to fetch Tasks, exception: {}", e.toString());
      throw new ProcessingException("Tasks couldn't be found");
    }
  }

  private void addCommentsToTasks(Set<Task> tasks) {
    for (Task task : tasks) commentService.findAllBy(task).forEach(task::comment);
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
