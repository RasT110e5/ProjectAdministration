package org.up.roque.project.ui.form;

import lombok.Getter;
import org.up.roque.project.Project;
import org.up.roque.project.ProjectService;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.project.employee.ui.util.EmployeeTableModel;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.custom.NamedTextFieldPanel;
import org.up.roque.ui.custom.ScrollableJTable;
import org.up.roque.ui.util.CustomFormPanel;

public class ProjectForm extends CustomFormPanel<Project> {
  private final NamedTextFieldPanel name = new NamedTextFieldPanel("Name");
  @Getter
  private final ScrollableJTable<Employee, Integer> employeeTable;

  public ProjectForm(String title, MainFrame frame, ProjectService service, EmployeeService employeeService) {
    super(title, frame, service);
    this.employeeTable = new ScrollableJTable<>(new EmployeeTableModel(frame, employeeService));
    init(name, employeeTable.getScrollPane());
    saveButton.addActionListener(e -> frame.showProjectView());
  }

  protected void setNameContent(String name) {
    this.name.setContent(name);
  }

  protected String getNameContent() {
    return name.getContent();
  }
}
