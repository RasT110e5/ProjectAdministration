package org.up.roque.project.ui.form;

import org.up.roque.project.Project;
import org.up.roque.project.ProjectService;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.MainFrame;

public class CreateProjectForm extends ProjectForm {
  public CreateProjectForm(MainFrame frame, ProjectService projectService, EmployeeService employeeService) {
    super("Create new project", frame, projectService, employeeService);
    saveButton.addActionListener(e -> save());
  }

  private void save() {
    submit(Project.builder()
        .name(super.getNameContent())
        .employees(super.getEmployeeTable().getSelectedItems())
        .build());
  }

}
