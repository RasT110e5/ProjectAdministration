package org.up.roque.project.ui;

import org.up.roque.project.util.UIUtil;

import javax.swing.*;

import static javax.swing.SwingConstants.CENTER;

public class ProgressDisplay extends JPanel {
  private final JLabel title;
  private final JLabel cost;
  private final JLabel hours;

  public ProgressDisplay(String title, Double cost, Integer hours) {
    this.title = new JLabel("<html><u>%s</u></html>".formatted(title), CENTER);
    this.cost = new JLabel("$%.2f".formatted(cost), CENTER);
    this.hours = new JLabel("%dhs".formatted(hours), CENTER);

    this.add(UIUtil.centerFlowPanelWithAlignment(this.title));
    this.add(UIUtil.centerFlowPanelWithAlignment(this.cost, Box.createHorizontalStrut(10), this.hours));

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }
}
