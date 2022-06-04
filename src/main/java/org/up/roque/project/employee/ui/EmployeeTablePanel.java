package org.up.roque.project.employee.ui;

import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.CustomPanel;
import org.up.roque.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class EmployeeTablePanel extends CustomPanel {
  private final EmployeeTableModel model;
  private final JTable table;
  private final JButton add = new JButton("Add");
  private final JButton delete = new JButton("Delete");
  private final JButton edit = new JButton("Edit");

  public EmployeeTablePanel(MainFrame frame, EmployeeService employeeService) {
    super("Employee Grid", frame);
    this.model = new EmployeeTableModel(frame, employeeService);

    table = new JTable(model);
    JScrollPane scrollBar = new JScrollPane(table);
    JPanel buttonsLayout = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonsLayout.add(add);
    buttonsLayout.add(delete);
    buttonsLayout.add(edit);

    delete.addActionListener(e -> model.delete(table.getSelectedRow()));
    add.addActionListener(e -> frame.showEmployeeCreateForm());

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(scrollBar);
    this.add(buttonsLayout);
  }


}
