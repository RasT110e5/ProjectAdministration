package org.up.roque.ui;

import lombok.NoArgsConstructor;

import javax.swing.*;

@NoArgsConstructor
public abstract class DialogUtils {
  public static void error(JFrame frame, String message) {
    JOptionPane.showMessageDialog(frame, message, "Processing Error", JOptionPane.ERROR_MESSAGE);
  }

  public static int confirmation(JFrame frame, String message, String title) {
    return JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.YES_NO_OPTION);
  }

}
