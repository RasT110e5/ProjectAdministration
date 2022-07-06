package org.up.roque.project.ui;

import org.up.roque.project.Project;
import org.up.roque.project.ProjectService;
import org.up.roque.project.Service;
import org.up.roque.project.employee.Employee;
import org.up.roque.ui.MainFrame;

public class EditProjectForm extends ProjectForm {
  private final Project projectToEdit;

  public EditProjectForm(MainFrame frame, ProjectService service, Service<Employee> employeeService, Project toEdit) {
    super("Edit Project", frame, service, employeeService);
    this.projectToEdit = toEdit;
    super.setNameContent(toEdit.getName());
    super.getEmployeeTable().selectRowsWithId(projectToEdit.getEmployees());
    saveButton.addActionListener(e -> update());
  }

  private void update() {
    projectToEdit.setName(super.getNameContent());
    projectToEdit.setEmployees(super.getEmployeeTable().getSelectedItems());
    submit(projectToEdit);
  }

}
