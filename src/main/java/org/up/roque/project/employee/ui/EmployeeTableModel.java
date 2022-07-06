package org.up.roque.project.employee.ui;

import org.up.roque.project.Service;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.CustomTableModel;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.TableColumn;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTableModel extends CustomTableModel<Employee> {
  public EmployeeTableModel(MainFrame frame, Service<Employee> service) {
    super(frame, service);
    List<TableColumn<?, Employee>> columns = new ArrayList<>();
    columns.add(new TableColumn<>("Employee Id", Integer.class, Employee::getId));
    columns.add(new TableColumn<>("Name", String.class, Employee::getName));
    columns.add(new TableColumn<>("Cost Per Hour", Integer.class, Employee::getCostPerHour));
    super.setColumns(columns);
  }
}
