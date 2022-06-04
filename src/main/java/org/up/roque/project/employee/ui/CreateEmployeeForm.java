package org.up.roque.project.employee.ui;

import org.up.roque.project.ProcessingException;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.CustomPanel;
import org.up.roque.ui.DialogUtils;
import org.up.roque.ui.MainFrame;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;

public class CreateEmployeeForm extends CustomPanel {
  private final EmployeeService service;
  private final NamedTextFieldPanel name = new NamedTextFieldPanel("Name");
  private final NamedTextFieldPanel costPerHour = new NamedTextFieldPanel("Cost per hour");

  public CreateEmployeeForm(MainFrame frame, EmployeeService employeeService) {
    super("Add new employee", frame);
    this.service = employeeService;

    this.add(name);
    this.add(costPerHour);
    JButton save = new JButton("Save");
    save.addActionListener(e -> save());
    JPanel saveButtonLayout = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    saveButtonLayout.add(save);
    this.add(saveButtonLayout);
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  private void save() {
    try {
      Employee employee = buildEmployee();
      service.save(employee);
    } catch (ProcessingException e) {
      DialogUtils.error(frame.getJFrame(), e.getMessage());
      return;
    } catch (IllegalArgumentException e) {
      DialogUtils.error(frame.getJFrame(),
          "Value '%s' in 'Cost per hour' is not a number".formatted(costPerHour.getContent()));
      return;
    }
    frame.showEmployeeGrid();
  }

  private Employee buildEmployee() {
    return Employee.builder()
        .name(name.getContent())
        .costPerHour(Integer.valueOf(costPerHour.getContent()))
        .build();
  }

  private static class NamedTextFieldPanel extends JPanel {
    private final JTextField textField = new JTextField(45);

    public NamedTextFieldPanel(String name) {
      this.setLayout(new FlowLayout(FlowLayout.CENTER));
      this.add(new JLabel("%s: ".formatted(name)));
      this.add(textField);
    }

    public String getContent() {
      return this.textField.getText();
    }
  }
}
