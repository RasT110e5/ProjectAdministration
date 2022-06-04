package org.up.roque.project.employee.ui;

import org.up.roque.project.ProcessingException;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.TableColumn;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {
  private final List<TableColumn<?, Employee>> columns = new ArrayList<>();
  private final JFrame frame;
  private final List<Employee> employees;
  private final EmployeeService service;

  public EmployeeTableModel(JFrame frame, EmployeeService service) {
    this.frame = frame;
    this.service = service;
    this.employees = new ArrayList<>(service.findAll());
    columns.add(new TableColumn<>("Id", Integer.class, Employee::getId));
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

  public void delete(int row) {
    Employee employee = employees.get(row);
    try {
      service.delete(employee);
    } catch (ProcessingException e) {
      JOptionPane.showMessageDialog(frame, e.getMessage(), "Processing Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    employees.remove(row);
    fireTableDataChanged();
  }
}
