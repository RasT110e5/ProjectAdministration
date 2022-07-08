package org.up.roque.ui.util;

import lombok.experimental.UtilityClass;

import javax.swing.*;

@UtilityClass
public class DialogUtils {
  public void error(JFrame frame, String message) {
    JOptionPane.showMessageDialog(frame, message, "Processing Error", JOptionPane.ERROR_MESSAGE);
  }

  public int confirmation(JFrame frame, String message, String title) {
    return JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.YES_NO_OPTION);
  }

}
