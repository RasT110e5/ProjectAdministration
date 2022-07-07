package org.up.roque.project.task.ui.form;

import org.up.roque.project.Project;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.task.Task;
import org.up.roque.project.task.TaskService;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.custom.NamedTextFieldPanel;
import org.up.roque.ui.util.CustomFormPanel;
import org.up.roque.ui.util.UIUtil;

import javax.swing.*;
import java.awt.*;

public abstract class TaskForm extends CustomFormPanel<Task> {
  public static final String HOURS_TEST = "Estimation: %d hs";
  private final NamedTextFieldPanel name = new NamedTextFieldPanel("Name");
  private final NamedTextFieldPanel description = new NamedTextFieldPanel("Description");
  private final JSlider hoursSlider = new JSlider(SwingConstants.HORIZONTAL, 1, 72, 3);
  private final JLabel hoursLabel = new JLabel(HOURS_TEST.formatted(3));
  private final JComboBox<Employee> assignedEmployee;

  protected TaskForm(String title, MainFrame frame, TaskService service, Project project) {
    super(title, frame, service);
    this.assignedEmployee = new JComboBox<>(project.getEmployees().toArray(new Employee[0]));
    this.assignedEmployee.setRenderer(TaskForm::getEmployeeRenderer);
    styleSlider();

    init(name, description,
        UIUtil.centerFlowPanelWithAlignment(hoursLabel),
        hoursSlider,
        UIUtil.centerFlowPanelWithAlignment(new JLabel("Assigned Employee:")),
        assignedEmployee);
    saveButton.addActionListener(e -> frame.showProjectStatusView(project));
  }

  private static Component getEmployeeRenderer(JList<? extends Employee> list,
                                               Employee value, int index, boolean isSelected, boolean cellHasFocus) {
    return new JLabel("Name: %s - Cost per hour: $%d".formatted(value.getName(), value.getCostPerHour()));
  }

  private void styleSlider() {
    hoursSlider.addChangeListener(e -> hoursLabel.setText(HOURS_TEST.formatted(((JSlider) e.getSource()).getValue())));
    hoursSlider.setMinorTickSpacing(1);
    hoursSlider.setMajorTickSpacing(10);
    hoursSlider.setPaintTicks(true);
    hoursSlider.setPaintLabels(true);
    hoursSlider.setLabelTable(hoursSlider.createStandardLabels(5));
  }

  protected String getNameContent() {
    return this.name.getContent();
  }

  protected String getDescriptionContent() {
    return this.description.getContent();
  }

  protected Integer getHours() {
    return hoursSlider.getValue();
  }

  protected Employee getSelectedEmployee() {
    return assignedEmployee.getItemAt(assignedEmployee.getSelectedIndex());
  }
}
