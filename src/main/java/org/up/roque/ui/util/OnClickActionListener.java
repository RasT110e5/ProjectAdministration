package org.up.roque.ui.util;

import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@RequiredArgsConstructor
public class OnClickActionListener implements MouseListener {
  private final Runnable action;

  @Override
  public void mouseClicked(MouseEvent e) {
    changeForegroundColorTo(e, Color.BLUE);
    action.run();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // No action necessary for this event
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // No action necessary for this event
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    changeForegroundColorTo(e, Color.GRAY);
  }

  @Override
  public void mouseExited(MouseEvent e) {
    changeForegroundColorTo(e, Color.BLUE);
  }

  private void changeForegroundColorTo(MouseEvent e, Color blue) {
    Component component = e.getComponent();
    if (component instanceof JLabel) {
      JLabel label = (JLabel) component;
      label.setForeground(blue);
    }
  }
}
