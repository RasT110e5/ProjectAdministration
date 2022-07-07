package org.up.roque.ui.custom;

import lombok.Getter;
import org.up.roque.db.Entity;
import org.up.roque.ui.util.CustomTableModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

  public void selectRowsWithId(List<E> entities) {
    List<?> ids = entities.stream().map(Entity::getId).collect(Collectors.toList());
    for (int i = 0; i < super.getRowCount(); i++) {
      E entity = model.getRow(i);
      if (ids.contains(entity.getId()))
        super.addRowSelectionInterval(i, i);
    }
  }
}
