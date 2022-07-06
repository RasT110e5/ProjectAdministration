package org.up.roque.ui;

import org.up.roque.ui.util.CustomPanel;

import javax.swing.*;

public class HomePanelPlaceholder extends CustomPanel {
  public HomePanelPlaceholder(MainFrame frame) {
    super("Home Page", frame);
    this.add(new JLabel("Home page placeholder..."));
  }
}
