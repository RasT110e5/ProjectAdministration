package org.up.roque.project.employee.ui;

import lombok.Getter;

import javax.swing.*;
import javax.swing.table.TableModel;

public class EmployeeScrollableTable extends JTable {
  @Getter
  private final JScrollPane scrollPane;

  public EmployeeScrollableTable(TableModel model) {
    super(model);
    this.scrollPane = new JScrollPane(this);
  }

  public int getSelectedRow() {
    return super.getSelectedRow();
  }
}
