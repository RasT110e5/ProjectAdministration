package org.up.roque.ui.util;

import lombok.experimental.UtilityClass;

import javax.swing.*;
import java.awt.*;

@UtilityClass
public class UIUtil {

  public JPanel centerFlowPanelWithAlignment(JComponent component) {
    return wrapInPanelWithAlignment(component);
  }

  public JPanel centerFlowPanelWithAlignment(Component... component) {
    JPanel panel = wrapInPanelWithAlignment(component[0]);
    for (int i = 1; i < component.length; i++) panel.add(component[i]);
    return panel;
  }

  private JPanel wrapInPanelWithAlignment(Component component) {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.add(component);
    return panel;
  }
}
