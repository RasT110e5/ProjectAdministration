package org.up.roque.project.employee.ui;

import org.up.roque.project.ProcessingException;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.DialogUtils;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.TableColumn;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {
  private final List<TableColumn<?, Employee>> columns = new ArrayList<>();
  private final JFrame parentFrame;
  private final List<Employee> employees;
  private final EmployeeService service;

  public EmployeeTableModel(MainFrame frame, EmployeeService service) {
    this.parentFrame = frame.getJFrame();
    this.service = service;
    this.employees = new ArrayList<>(service.findAll());
    columns.add(new TableColumn<>("Employee Id", Integer.class, Employee::getId));
    columns.add(new TableColumn<>("Name", String.class, Employee::getName));
    columns.add(new TableColumn<>("Cost Per Hour", Integer.class, Employee::getCostPerHour));
  }

  @Override
  public int getRowCount() {
    return employees.size();
  }

  @Override
  public int getColumnCount() {
    return columns.size();
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    TableColumn<?, Employee> column = columns.get(columnIndex);
    Employee employee = employees.get(rowIndex);
    return column.getValue(employee);
  }

  @Override
  public String getColumnName(int column) {
    return columns.get(column).getName();
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return columns.get(columnIndex).getType();
  }

  public Employee getRow(int row){
    if (isInvalidRow(row))
      return null;
    else
      return employees.get(row);
  }

  public void delete(int row) {
    if (isInvalidRow(row)) return;
    Employee employee = employees.get(row);
    int response = DialogUtils.confirmation(parentFrame,
        "Are you sure you want to delete employee '%s'?".formatted(employee.getName()), "Delete?");
    if (response == JOptionPane.YES_OPTION) {
      try {
        service.delete(employee);
      } catch (ProcessingException e) {
        DialogUtils.error(parentFrame, e.getMessage());
        return;
      }
      employees.remove(row);
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
