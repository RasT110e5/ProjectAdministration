package org.up.roque.ui;

import lombok.Getter;

import javax.swing.*;
import javax.swing.table.TableModel;

public class ScrollableJTable extends JTable {
  @Getter
  private final JScrollPane scrollPane;

  public ScrollableJTable(TableModel model) {
    super(model);
    this.scrollPane = new JScrollPane(this);
  }

  public int getSelectedRow() {
    return super.getSelectedRow();
  }
}
