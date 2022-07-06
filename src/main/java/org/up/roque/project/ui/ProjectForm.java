package org.up.roque.project.ui;

import lombok.Getter;
import org.up.roque.project.ProcessingException;
import org.up.roque.project.Project;
import org.up.roque.project.ProjectService;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.project.employee.ui.EmployeeTableModel;
import org.up.roque.ui.*;

import javax.swing.*;
import java.awt.*;

public class ProjectForm extends CustomPanel {
  private final ProjectService service;
  private final NamedTextFieldPanel name = new NamedTextFieldPanel("Name");
  @Getter
  private final ScrollableJTable<Employee> employeeTable;
  protected final JButton saveButton = new JButton("Save");

  public ProjectForm(String title, MainFrame frame, ProjectService service, EmployeeService employeeService) {
    super(title, frame);
    this.service = service;
    this.add(name);
    this.employeeTable = new ScrollableJTable<>(new EmployeeTableModel(frame, employeeService));
    this.add(employeeTable.getScrollPane());
    JPanel saveButtonLayout = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    saveButtonLayout.add(saveButton);
    this.add(saveButtonLayout);
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  protected void submit(Project employee) {
    try {
      service.save(employee);
    } catch (ProcessingException e) {
      DialogUtils.error(frame.getJFrame(), e.getMessage());
      return;
    }
    frame.showProjectView();
  }

  protected void setNameContent(String name) {
    this.name.setContent(name);
  }

  protected String getNameContent() {
    return name.getContent();
  }
}
