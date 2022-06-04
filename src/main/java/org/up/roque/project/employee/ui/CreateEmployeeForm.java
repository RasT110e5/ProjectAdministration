package org.up.roque.project.employee.ui;

import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.MainFrame;

public class CreateEmployeeForm extends EmployeeForm {
  public CreateEmployeeForm(MainFrame frame, EmployeeService employeeService) {
    super("Add new employee", frame, employeeService);
    saveButton.addActionListener(e -> save());
  }

  private void save() {
    Integer costPerHour = super.getCostPerHourContent();
    if (costPerHour != null) {
      submit(Employee.builder()
          .name(super.getNameContent())
          .costPerHour(costPerHour)
          .build());
    }
  }

}
