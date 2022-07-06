package org.up.roque.project.ui;

import lombok.RequiredArgsConstructor;
import org.up.roque.db.Entity;
import org.up.roque.project.Project;
import org.up.roque.project.ProjectService;
import org.up.roque.project.Service;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.MainFrame;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

  @RequiredArgsConstructor
  private static class ProjectEmployeesService implements Service<Employee> {
    private final Set<Employee> employees;

    @Override
    public void delete(Employee entity) {
      employees.remove(entity);
    }

    @Override
    public Set<Employee> findAll() {
      return employees;
    }
  }
}
