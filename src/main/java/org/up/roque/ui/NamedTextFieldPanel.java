package org.up.roque.ui;

import javax.swing.*;
import java.awt.*;

public class NamedTextFieldPanel extends JPanel {
  private final JTextField textField = new JTextField(45);

  public NamedTextFieldPanel(String name) {
    this.setLayout(new FlowLayout(FlowLayout.CENTER));
    this.add(new JLabel("%s: ".formatted(name)));
    this.add(textField);
  }

  public String getContent() {
    return this.textField.getText();
  }

  public void setContent(String content) {
    this.textField.setText(content);
  }
}
