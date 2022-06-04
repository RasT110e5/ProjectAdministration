package org.up.roque.project.employee.ui;

import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.MainFrame;

public class EditEmployeeForm extends EmployeeForm {
  private final Employee employeeToEdit;

  public EditEmployeeForm(MainFrame frame, EmployeeService employeeService, Employee toEdit) {
    super("Edit employee", frame, employeeService);
    this.employeeToEdit = toEdit;
    super.setNameContent(toEdit.getName());
    super.setCostPerHourContent(toEdit.getCostPerHour());
    saveButton.addActionListener(e -> update());
  }

  private void update() {
    Integer costPerHour = super.getCostPerHourContent();
    if (costPerHour != null) {
      employeeToEdit.setName(super.getNameContent());
      employeeToEdit.setCostPerHour(costPerHour);
      submit(employeeToEdit);
    }
  }
}
