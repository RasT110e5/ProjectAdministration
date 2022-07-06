package org.up.roque.ui;

import lombok.Getter;
import org.up.roque.db.Entity;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ScrollableJTable<E extends Entity<?>> extends JTable {
  @Getter
  private final JScrollPane scrollPane;
  private final CustomTableModel<E> model;

  public ScrollableJTable(CustomTableModel<E> model) {
    super(model);
    this.model = model;
    this.scrollPane = new JScrollPane(this);
  }

  public E getSelectedItem() {
    return model.getRow(super.getSelectedRow());
  }

  public void deleteSelectedItem() {
    model.delete(super.getSelectedRow());
  }

  public List<E> getSelectedItems() {
    List<E> entities = new ArrayList<>();
    int[] rows = this.getSelectedRows();
    for (int row : rows) {
      entities.add(model.getRow(row));
    }
    return entities;
  }
}
