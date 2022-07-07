package org.up.roque.project.task.ui;

import org.up.roque.project.Project;
import org.up.roque.project.task.Task;
import org.up.roque.project.task.TaskService;
import org.up.roque.project.task.ui.util.TaskTableModel;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.custom.CRUDButtonComponent;
import org.up.roque.ui.custom.ScrollableJTable;

import javax.swing.*;
import java.util.Set;

public class TaskTablePanel extends JPanel {
  private final ScrollableJTable<Task> table;
  private final CRUDButtonComponent buttonsLayout = new CRUDButtonComponent();
  private final MainFrame frame;
  private final Project project;


  public TaskTablePanel(MainFrame frame, TaskService service, Set<Task> tasks, Project project) {
    TaskTableModel model = new TaskTableModel(frame, service, tasks);
    this.frame = frame;
    this.project = project;
    this.table = new ScrollableJTable<>(model);

    addActionListeners(frame);

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(table.getScrollPane());
    this.add(buttonsLayout);
  }

  private void addActionListeners(MainFrame frame) {
    buttonsLayout.addActionListenerToDelete(e -> deleteTask());
    buttonsLayout.addActionListenerToAdd(e -> frame.showHome());
    buttonsLayout.addActionListenerToEdit(e -> showProjectEditForm());
  }

  private void deleteTask() {
    table.deleteSelectedItem();
    frame.showProjectStatusView(project);
  }

  private void showProjectEditForm() {
//    Project project = table.getSelectedItem();
//    if (project != null)
//      frame.showProjectEditForm(project);
  }
}
