package org.up.roque.ui;

import lombok.Getter;

import javax.swing.*;

public abstract class CustomPanel extends JPanel {
  protected final MainFrame frame;
  @Getter
  private final String title;

  public CustomPanel(String title, MainFrame frame) {
    this.title = title;
    this.frame = frame;
  }
}
