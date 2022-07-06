package org.up.roque.ui;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Getter
public class HeaderPanel extends JPanel {
  private final JButton returnButton = new JButton("Return");
  private final JLabel selectedViewTitle = new JLabel("", SwingConstants.CENTER);
  private final JButton projectButton = new JButton("Projects");
  private final JButton employeeButton = new JButton("Employees");

  public HeaderPanel() {
    this.add(returnButton);
    this.add(selectedViewTitle);
    this.add(projectButton);
    this.add(employeeButton);
    styleComponents();
  }

  public void setTitle(String title) {
    this.selectedViewTitle.setText(title);
  }

  public void setEmployeeActionListener(ActionListener listener) {
    this.employeeButton.addActionListener(listener);
  }

  public void setProjectActionListener(ActionListener listener) {
    this.projectButton.addActionListener(listener);
  }

  public void setReturnActionListener(ActionListener listener) {
    this.returnButton.addActionListener(listener);
  }

  private void styleComponents() {
    styleLayout();
    selectedViewTitle.setPreferredSize(new Dimension(500, 50));
  }

  private void styleLayout() {
    this.setLayout(new FlowLayout(FlowLayout.RIGHT));
    this.setPreferredSize(new Dimension(790, 60));
    this.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
  }
}
