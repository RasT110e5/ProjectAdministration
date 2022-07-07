package org.up.roque.project.employee.ui.form;

import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.custom.NamedTextFieldPanel;
import org.up.roque.ui.util.CustomFormPanel;
import org.up.roque.ui.util.DialogUtils;

public abstract class EmployeeForm extends CustomFormPanel<Employee> {
  private final NamedTextFieldPanel name = new NamedTextFieldPanel("Name");
  private final NamedTextFieldPanel costPerHour = new NamedTextFieldPanel("Cost per hour");

  protected EmployeeForm(String title, MainFrame frame, EmployeeService service) {
    super(title, frame, service);
    init(name, costPerHour);
    saveButton.addActionListener(e -> frame.showEmployeeView());
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
