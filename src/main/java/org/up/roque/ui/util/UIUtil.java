package org.up.roque.ui.util;

import lombok.experimental.UtilityClass;

import javax.swing.*;
import java.awt.*;

@UtilityClass
public class UIUtil {

  public JPanel centerFlowPanelWithAlignment(JComponent component) {
    return wrapInPanelWithAlignment(FlowLayout.LEFT, component);
  }

  public JPanel centerFlowPanelWithAlignment(Component... components) {
    return wrapInPanelWithAlignment(FlowLayout.CENTER, components);
  }

  public static JPanel leftFlowPanelWithAlignment(Component... components) {
    return wrapInPanelWithAlignment(FlowLayout.LEFT, components);
  }

  public static JPanel rightFlowPanelWithAlignment(Component... components) {
    return wrapInPanelWithAlignment(FlowLayout.RIGHT, components);
  }

  private JPanel wrapInPanelWithAlignment(int alignment, Component component) {
    JPanel panel = new JPanel(new FlowLayout(alignment));
    panel.add(component);
    return panel;
  }

  private JPanel wrapInPanelWithAlignment(int alignment, Component[] components) {
    JPanel panel = wrapInPanelWithAlignment(alignment, components[0]);
    for (int i = 1; i < components.length; i++) panel.add(components[i]);
    return panel;
  }

}
