package org.up.roque.project.ui;

import org.up.roque.project.Project;
import org.up.roque.project.ProjectService;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.MainFrame;

public class EditProjectForm extends ProjectForm {
  private final Project employeeToEdit;

  public EditProjectForm(MainFrame frame, ProjectService service, EmployeeService employeeService, Project toEdit) {
    super("Edit Project", frame, service, employeeService);
    this.employeeToEdit = toEdit;
    super.setNameContent(toEdit.getName());
    saveButton.addActionListener(e -> update());
  }

  private void update() {
    employeeToEdit.setName(super.getNameContent());
    submit(employeeToEdit);
  }
}
