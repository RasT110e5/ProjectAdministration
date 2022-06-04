package org.up.roque.project.employee.ui;

import org.up.roque.project.employee.EmployeeService;
import org.up.roque.ui.CustomPanel;
import org.up.roque.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class EmployeeTablePanel extends CustomPanel {
  private final EmployeeTableModel model;
  private final JFrame frame;

  private JTable table;
  private JButton add = new JButton("Add");
  private JButton delete = new JButton("Delete");
  private JButton edit = new JButton("Edit");

  public EmployeeTablePanel(JFrame frame, EmployeeService employeeService) {
    super("Employee Grid");
    this.frame = frame;
    this.model = new EmployeeTableModel(frame, employeeService);

    table = new JTable(model);
    JScrollPane scrollBar = new JScrollPane(table);
    JPanel buttonsLayout = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonsLayout.add(add);
    buttonsLayout.add(delete);
    buttonsLayout.add(edit);

    delete.addActionListener(e -> model.delete(table.getSelectedRow()));

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(scrollBar);
    this.add(buttonsLayout);
  }


}
