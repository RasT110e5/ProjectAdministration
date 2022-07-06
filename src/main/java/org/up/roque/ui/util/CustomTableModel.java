package org.up.roque.ui.util;

import lombok.Setter;
import org.up.roque.db.Entity;
import org.up.roque.project.util.ProcessingException;
import org.up.roque.project.util.Service;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.util.DialogUtils;
import org.up.roque.ui.util.TableColumn;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public abstract class CustomTableModel<T extends Entity<ID>, ID> extends AbstractTableModel {
  private final JFrame parentFrame;
  private final List<T> items;
  private final Service<T, ID> service;

  @Setter
  private List<TableColumn<?, T>> columns = new ArrayList<>();

  public CustomTableModel(MainFrame frame, Service<T, ID> service) {
    this.parentFrame = frame.getJFrame();
    this.service = service;
    this.items = new ArrayList<>(service.findAll());
  }

  @Override
  public int getRowCount() {
    return items.size();
  }

  @Override
  public int getColumnCount() {
    return columns.size();
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    TableColumn<?, T> column = columns.get(columnIndex);
    T entity = items.get(rowIndex);
    return column.getValue(entity);
  }

  @Override
  public String getColumnName(int column) {
    return columns.get(column).getName();
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return columns.get(columnIndex).getType();
  }

  public T getRow(int row) {
    if (isInvalidRow(row))
      return null;
    else
      return items.get(row);
  }

  public void delete(int row) {
    if (isInvalidRow(row)) return;
    T entity = items.get(row);
    int response = DialogUtils.confirmation(parentFrame,
        "Are you sure you want to delete entity with name: '%s'?".formatted(entity.getName()),
        "Delete?");
    if (response == JOptionPane.YES_OPTION) {
      try {
        service.delete(entity);
      } catch (ProcessingException e) {
        DialogUtils.error(parentFrame, e.getMessage());
        return;
      }
      items.remove(row);
      fireTableDataChanged();
    }
  }

  private boolean isInvalidRow(int row) {
    if (row < 0) {
      DialogUtils.error(parentFrame, "No row is selected");
      return true;
    }
    return false;
  }
}