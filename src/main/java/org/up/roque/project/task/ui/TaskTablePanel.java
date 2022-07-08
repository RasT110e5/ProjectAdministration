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
  private final JButton viewButton = new JButton("View");

  public TaskTablePanel(MainFrame frame, TaskService service, Set<Task> tasks, Project project) {
    TaskTableModel model = new TaskTableModel(frame, service, tasks);
    this.frame = frame;
    this.project = project;
    this.table = new ScrollableJTable<>(model);

    addActionListeners();

    this.add(table.getScrollPane());
    buttonsLayout.addNewButton(viewButton);
    this.add(buttonsLayout);

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  private void addActionListeners() {
    buttonsLayout.addActionListenerToDelete(e -> deleteTask());
    buttonsLayout.addActionListenerToAdd(e -> frame.showTaskCreateForm(project));
    buttonsLayout.addActionListenerToEdit(e -> showTaskToEdit());
    viewButton.addActionListener(e -> showTaskToView());
  }

  private void deleteTask() {
    table.deleteSelectedItem();
    frame.showProjectStatusView(project);
  }

  private void showTaskToEdit() {
    Task entity = table.getSelectedItem();
    if (entity != null)
      frame.showTaskEditForm(entity, project);
  }

  private void showTaskToView() {
    Task entity = table.getSelectedItem();
    if (entity != null)
      frame.showTaskViewPanel(entity);
  }
}
