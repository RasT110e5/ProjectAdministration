package org.up.roque.project.task;

import org.up.roque.project.Project;
import org.up.roque.project.util.RelatedTo;
import org.up.roque.project.util.Service;

public interface TaskService extends Service<Task, Integer>, RelatedTo<Task, Project> {
}
