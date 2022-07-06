package org.up.roque.project.ui;

import org.up.roque.project.Project;
import org.up.roque.project.ProjectService;
import org.up.roque.ui.CRUDButtonComponent;
import org.up.roque.ui.CustomPanel;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.ScrollableJTable;

import javax.swing.*;

public class ProjectTablePanel extends CustomPanel {
  private final ProjectTableModel model;
  private final ScrollableJTable<Project> table;
  private final CRUDButtonComponent buttonsLayout = new CRUDButtonComponent();

  public ProjectTablePanel(MainFrame frame, ProjectService projectService) {
    super("Project Panel", frame);
    this.model = new ProjectTableModel(frame, projectService);
    this.table = new ScrollableJTable<>(model);

    addActionListeners(frame);

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(table.getScrollPane());
    this.add(buttonsLayout);
  }

  private void addActionListeners(MainFrame frame) {
    buttonsLayout.addActionListenerToDelete(e -> table.deleteSelectedItem());
    buttonsLayout.addActionListenerToAdd(e -> frame.showProjectCreateForm());
    buttonsLayout.addActionListenerToEdit(e -> showProjectEditForm());
  }

  private void showProjectEditForm() {
    Project project = table.getSelectedItem();
    if (project != null)
      frame.showProjectEditForm(project);
  }
}
