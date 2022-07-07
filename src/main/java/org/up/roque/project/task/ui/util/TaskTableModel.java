package org.up.roque.project.task.ui.util;

import org.up.roque.project.task.Task;
import org.up.roque.project.task.TaskService;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.util.CustomTableModel;
import org.up.roque.ui.util.TableColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TaskTableModel extends CustomTableModel<Task> {
  public TaskTableModel(MainFrame frame, TaskService service, Set<Task> tasks) {
    super(frame, service, tasks);
    List<TableColumn<?, Task>> columns = new ArrayList<>();
    columns.add(new TableColumn<>("Name", String.class, Task::getName));
    columns.add(new TableColumn<>("Estimated Hs", Integer.class, Task::getEstimatedHours));
    columns.add(new TableColumn<>("Actual Hs", Integer.class, Task::getActualDuration));
    columns.add(new TableColumn<>("Assigned to", String.class, t -> t.getAssignedEmployee().getName()));
    columns.add(new TableColumn<>("Status", String.class, t -> t.getStatus().name()));
    super.setColumns(columns);
  }
}
