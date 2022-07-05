package org.up.roque.project.employee.ui;

import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.project.ui.CRUDButtonComponent;
import org.up.roque.ui.CustomPanel;
import org.up.roque.ui.MainFrame;

import javax.swing.*;

public class EmployeeTablePanel extends CustomPanel {
  private final EmployeeTableModel model;
  private final JTable table;
  private final CRUDButtonComponent buttonsLayout = new CRUDButtonComponent();

  public EmployeeTablePanel(MainFrame frame, EmployeeService employeeService) {
    super("Employee Grid", frame);
    this.model = new EmployeeTableModel(frame, employeeService);

    table = new JTable(model);
    JScrollPane scrollBar = new JScrollPane(table);

    addActionListeners(frame);

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(scrollBar);
    this.add(buttonsLayout);
  }

  private void addActionListeners(MainFrame frame) {
    buttonsLayout.addActionListenerToDelete(e -> model.delete(table.getSelectedRow()));
    buttonsLayout.addActionListenerToAdd(e -> frame.showEmployeeCreateForm());
    buttonsLayout.addActionListenerToEdit(e -> showEmployeeEditForm());
  }

  private void showEmployeeEditForm() {
    Employee employee = model.getRow(table.getSelectedRow());
    if (employee != null)
      frame.showEmployeeEditForm(employee);
  }


}
