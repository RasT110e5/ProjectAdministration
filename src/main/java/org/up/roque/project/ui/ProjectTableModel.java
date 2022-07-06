package org.up.roque.project.ui;

import org.up.roque.project.Project;
import org.up.roque.project.ProjectService;
import org.up.roque.ui.CustomTableModel;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.TableColumn;

import java.util.ArrayList;
import java.util.List;

public class ProjectTableModel extends CustomTableModel<Project, Integer> {
  public ProjectTableModel(MainFrame frame, ProjectService service) {
    super(frame, service);
    List<TableColumn<?, Project>> columns = new ArrayList<>();
    columns.add(new TableColumn<>("Project Id", Integer.class, Project::getId));
    columns.add(new TableColumn<>("Name", String.class, Project::getName));
    columns.add(new TableColumn<>("Employee Quantity", Integer.class, p -> p.getEmployees().size()));
    super.setColumns(columns);
  }
}
