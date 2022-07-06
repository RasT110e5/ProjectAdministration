package org.up.roque.project.ui;

import org.up.roque.project.ProcessingException;
import org.up.roque.project.Project;
import org.up.roque.project.ProjectService;
import org.up.roque.ui.DialogUtils;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.TableColumn;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProjectTableModel extends AbstractTableModel {
  private final List<TableColumn<?, Project>> columns = new ArrayList<>();
  private final JFrame parentFrame;
  private final List<Project> projects;
  private final ProjectService service;

  public ProjectTableModel(MainFrame frame, ProjectService service) {
    this.parentFrame = frame.getJFrame();
    this.service = service;
    this.projects = new ArrayList<>(service.findAll());
    columns.add(new TableColumn<>("Project Id", Integer.class, Project::getId));
    columns.add(new TableColumn<>("Name", String.class, Project::getName));
    columns.add(new TableColumn<>("Employee Quantity", Integer.class, p -> p.getEmployees().size()));
  }

  @Override
  public int getRowCount() {
    return projects.size();
  }

  @Override
  public int getColumnCount() {
    return columns.size();
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    TableColumn<?, Project> column = columns.get(columnIndex);
    Project project = projects.get(rowIndex);
    return column.getValue(project);
  }

  @Override
  public String getColumnName(int column) {
    return columns.get(column).getName();
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return columns.get(columnIndex).getType();
  }

  public Project getRow(int row) {
    if (isInvalidRow(row))
      return null;
    else
      return projects.get(row);
  }

  public void delete(int row) {
    if (isInvalidRow(row)) return;
    Project project = projects.get(row);
    int response = DialogUtils.confirmation(parentFrame,
        "Are you sure you want to delete project '%s'?".formatted(project.getName()), "Delete?");
    if (response == JOptionPane.YES_OPTION) {
      try {
        service.delete(project);
      } catch (ProcessingException e) {
        DialogUtils.error(parentFrame, e.getMessage());
        return;
      }
      projects.remove(row);
      fireTableDataChanged();
    }
  }

  private boolean isInvalidRow(int row) {
    if (row < 0) {
      DialogUtils.error(parentFrame, "No row is selected");
      return true;
    }
    return false;
  }
}
