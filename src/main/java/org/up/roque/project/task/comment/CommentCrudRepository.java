package org.up.roque.project.task.comment;

import org.up.roque.db.CrudRepository;
import org.up.roque.project.task.Task;
import org.up.roque.project.util.RelatedTo;

import java.util.Set;

public interface CommentCrudRepository extends CrudRepository<Comment, Integer> {
  Set<Comment> findAllByTask(Task task);
}
