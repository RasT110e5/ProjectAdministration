package org.up.roque.ui;

import lombok.Getter;

import javax.swing.*;

public abstract class CustomPanel extends JPanel {
  @Getter
  private final String title;

  public CustomPanel(String title) {
    this.title = title;
  }
}
