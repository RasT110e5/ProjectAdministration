package org.up.roque.project.employee.ui;

import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.CRUDButtonComponent;
import org.up.roque.ui.ScrollableJTable;
import org.up.roque.ui.CustomPanel;
import org.up.roque.ui.MainFrame;

import javax.swing.*;

public class EmployeeTablePanel extends CustomPanel {
  private final EmployeeTableModel model;
  private final ScrollableJTable<Employee> table;
  private final CRUDButtonComponent buttonsLayout = new CRUDButtonComponent();

  public EmployeeTablePanel(MainFrame frame, EmployeeService employeeService) {
    super("Employee Panel", frame);
    this.model = new EmployeeTableModel(frame, employeeService);
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
