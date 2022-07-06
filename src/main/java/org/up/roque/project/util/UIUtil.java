package org.up.roque.project.util;

import lombok.experimental.UtilityClass;

import javax.swing.*;
import java.awt.*;

@UtilityClass
public class UIUtil {
  public JPanel leftFlowPanelWithAlignment(JComponent component) {
    return wrapInPanelWithAlignment(FlowLayout.LEFT, component);
  }

  public JPanel centerFlowPanelWithAlignment(JComponent component) {
    return wrapInPanelWithAlignment(FlowLayout.CENTER, component);
  }

  public JPanel centerFlowPanelWithAlignment(Component... component) {
    JPanel panel = wrapInPanelWithAlignment(FlowLayout.CENTER, component[0]);
    for (int i = 1; i < component.length; i++) panel.add(component[i]);
    return panel;
  }

  private JPanel wrapInPanelWithAlignment(int center, Component component) {
    JPanel panel = new JPanel(new FlowLayout(center));
    panel.add(component);
    return panel;
  }
}
