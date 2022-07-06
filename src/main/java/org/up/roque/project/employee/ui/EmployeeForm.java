package org.up.roque.project.employee.ui;

import org.up.roque.project.ProcessingException;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.CustomPanel;
import org.up.roque.ui.DialogUtils;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.NamedTextFieldPanel;

import javax.swing.*;
import java.awt.*;

public class EmployeeForm extends CustomPanel {
  private final EmployeeService service;
  private final NamedTextFieldPanel name = new NamedTextFieldPanel("Name");
  private final NamedTextFieldPanel costPerHour = new NamedTextFieldPanel("Cost per hour");
  protected final JButton saveButton = new JButton("Save");

  public EmployeeForm(String title, MainFrame frame, EmployeeService service) {
    super(title, frame);
    this.service = service;
    this.add(name);
    this.add(costPerHour);
    JPanel saveButtonLayout = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    saveButtonLayout.add(saveButton);
    this.add(saveButtonLayout);
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  protected void submit(Employee employee) {
    try {
      service.save(employee);
    } catch (ProcessingException e) {
      DialogUtils.error(frame.getJFrame(), e.getMessage());
      return;
    }
    frame.showEmployeeView();
  }

  protected void setNameContent(String name) {
    this.name.setContent(name);
  }

  protected void setCostPerHourContent(Integer costPerHour) {
    this.costPerHour.setContent(String.valueOf(costPerHour));
  }

  protected String getNameContent() {
    return name.getContent();
  }

  protected Integer getCostPerHourContent() {
    try {
      return Integer.valueOf(costPerHour.getContent());
    } catch (IllegalArgumentException e) {
      DialogUtils.error(frame.getJFrame(),
          "Value '%s' in 'Cost per hour' is not a number".formatted(costPerHour.getContent()));
      return null;
    }
  }

}
