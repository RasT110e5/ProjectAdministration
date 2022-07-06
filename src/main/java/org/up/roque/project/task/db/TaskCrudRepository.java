package org.up.roque.project.task.db;

import org.up.roque.db.CrudRepository;
import org.up.roque.project.task.Task;

public interface TaskCrudRepository extends CrudRepository<Task, Integer> {
}
