package org.up.roque.project.task.comment;

import org.up.roque.project.task.Task;
import org.up.roque.project.util.RelatedTo;
import org.up.roque.project.util.Service;

public interface CommentService extends Service<Comment, Integer>, RelatedTo<Comment, Task> {
}
