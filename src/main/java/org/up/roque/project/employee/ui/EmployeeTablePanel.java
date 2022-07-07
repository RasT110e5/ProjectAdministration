package org.up.roque.project.employee.ui;

import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.project.employee.ui.util.EmployeeTableModel;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.custom.CRUDButtonComponent;
import org.up.roque.ui.custom.ScrollableJTable;
import org.up.roque.ui.util.CustomPanel;

import javax.swing.*;

public class EmployeeTablePanel extends CustomPanel {
  private final ScrollableJTable<Employee> table;
  private final CRUDButtonComponent buttonsLayout = new CRUDButtonComponent();

  public EmployeeTablePanel(MainFrame frame, EmployeeService employeeService) {
    super("Employee Panel", frame);
    EmployeeTableModel model = new EmployeeTableModel(frame, employeeService);
    this.table = new ScrollableJTable<>(model);

    addActionListeners(frame);

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(table.getScrollPane());
    this.add(buttonsLayout);
  }

  private void addActionListeners(MainFrame frame) {
    buttonsLayout.addActionListenerToDelete(e -> table.deleteSelectedItem());
    buttonsLayout.addActionListenerToAdd(e -> frame.showEmployeeCreateForm());
    buttonsLayout.addActionListenerToEdit(e -> showEmployeeEditForm());
  }

  private void showEmployeeEditForm() {
    Employee employee = table.getSelectedItem();
    if (employee != null)
      frame.showEmployeeEditForm(employee);
  }
}
