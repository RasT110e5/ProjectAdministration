package org.up.roque.ui.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CRUDButtonComponent extends JPanel {
  private final JButton add = new JButton("Add");
  private final JButton delete = new JButton("Delete");
  private final JButton edit = new JButton("Edit");

  public CRUDButtonComponent() {
    super(new FlowLayout(FlowLayout.CENTER));
    this.add(add);
    this.add(delete);
    this.add(edit);
  }

  public void addActionListenerToAdd(ActionListener listener) {
    add.addActionListener(listener);
  }

  public void addActionListenerToDelete(ActionListener listener) {
    delete.addActionListener(listener);
  }

  public void addActionListenerToEdit(ActionListener listener) {
    edit.addActionListener(listener);
  }
}
