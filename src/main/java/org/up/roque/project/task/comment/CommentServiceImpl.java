package org.up.roque.project.task.comment;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.DataAccessException;
import org.up.roque.project.task.Task;
import org.up.roque.project.util.ProcessingException;
import org.up.roque.project.util.ServiceTemplate;

import java.util.Set;

@Slf4j
public class CommentServiceImpl extends ServiceTemplate<Comment, Integer> implements CommentService {
  private final CommentCrudRepository commentRepository;

  public CommentServiceImpl(CommentCrudRepository repository) {
    super(repository);
    this.commentRepository = repository;
  }

  @Override
  public Set<Comment> findAllBy(Task task) {
    try {
      return this.commentRepository.findAllByTask(task);
    } catch (DataAccessException e) {
      log.warn("Exception while trying to get comments for task: '{}', exception: {}", task, e.toString());
      throw new ProcessingException("Tasks couldn't be found");
    }
  }
}
