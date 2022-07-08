package org.up.roque.project.task.ui.form;

import org.up.roque.project.Project;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.task.Task;
import org.up.roque.project.task.TaskService;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.custom.NamedSliderPanel;
import org.up.roque.ui.custom.NamedTextFieldPanel;
import org.up.roque.ui.util.CustomFormPanel;
import org.up.roque.ui.util.UIUtil;

import javax.swing.*;
import java.awt.*;

public abstract class TaskForm extends CustomFormPanel<Task> {
  private final NamedTextFieldPanel name = new NamedTextFieldPanel("Name");
  private final NamedTextFieldPanel description = new NamedTextFieldPanel("Description");
  private final NamedSliderPanel estimationSlider = new NamedSliderPanel("Estimation", 3);
  private final JComboBox<Employee> assignedEmployee;

  protected TaskForm(String title, MainFrame frame, TaskService service, Project project) {
    super(title, frame, service);
    this.assignedEmployee = new JComboBox<>(project.getEmployees().toArray(new Employee[0]));
    this.assignedEmployee.setRenderer(TaskForm::getEmployeeRenderer);

    init(name, description,
        estimationSlider,
        UIUtil.centerFlowPanelWithAlignment(new JLabel("Assigned Employee:")),
        assignedEmployee);
    saveButton.addActionListener(e -> frame.showProjectStatusView(project));
  }

  private static Component getEmployeeRenderer(JList<? extends Employee> list,
                                               Employee value, int index, boolean isSelected, boolean cellHasFocus) {
    return new JLabel(value.getFormatedNameAndCost());
  }

  protected String getNameContent() {
    return this.name.getContent();
  }

  protected String getDescriptionContent() {
    return this.description.getContent();
  }

  protected Integer getEstimationHours() {
    return this.estimationSlider.getSelectedValue();
  }

  protected Employee getSelectedEmployee() {
    return assignedEmployee.getItemAt(assignedEmployee.getSelectedIndex());
  }

  protected void setNameContent(String name) {
    this.name.setContent(name);
  }

  protected void setDescriptionContent(String description) {
    this.description.setContent(description);
  }

  protected void setEstimatedHours(Integer hours) {
    this.estimationSlider.setSelectedValue(hours);
  }

  protected void setSelectedEmployee(Employee employee) {
    assignedEmployee.setSelectedItem(employee);
  }
}
