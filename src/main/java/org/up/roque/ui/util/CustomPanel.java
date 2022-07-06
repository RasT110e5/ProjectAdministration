package org.up.roque.ui.util;

import lombok.Getter;
import org.up.roque.ui.MainFrame;

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
