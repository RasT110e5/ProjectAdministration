package org.up.roque.ui.util;

import org.up.roque.db.Entity;
import org.up.roque.project.util.ProcessingException;
import org.up.roque.project.util.Service;
import org.up.roque.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public abstract class CustomFormPanel<T extends Entity<?>> extends CustomPanel {
  private final Service<T, ?> service;
  protected final JButton saveButton = new JButton("Save");

  protected CustomFormPanel(String title, MainFrame frame, Service<T, ?> service) {
    super(title, frame);
    this.service = service;
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  protected void init(JComponent... components) {
    for (JComponent component : components) this.add(component);
    JPanel saveButtonLayout = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    saveButtonLayout.add(saveButton);
    this.add(saveButtonLayout);
  }

  protected void submit(T entity) {
    try {
      service.save(entity);
    } catch (ProcessingException e) {
      DialogUtils.error(frame.getJFrame(), e.getMessage());
    }
  }

}
