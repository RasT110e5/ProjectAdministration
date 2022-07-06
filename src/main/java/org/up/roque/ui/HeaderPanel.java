package org.up.roque.ui;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Locale;

import static java.awt.FlowLayout.*;

@Getter
public class HeaderPanel extends JPanel {
  private final JButton returnButton = new JButton("Return");
  private final JButton homeButton = new JButton("Home");
  private final JLabel selectedViewTitle = new JLabel("", SwingConstants.CENTER);
  private final JButton projectButton = new JButton("Projects");
  private final JButton employeeButton = new JButton("Employees");

  public HeaderPanel() {
    JPanel leftPanel = new JPanel(new FlowLayout(LEFT));
    leftPanel.add(returnButton);
    leftPanel.add(homeButton);
    JPanel centerPanel = new JPanel(new FlowLayout(CENTER));
    centerPanel.add(selectedViewTitle);
    JPanel rightPanel = new JPanel(new FlowLayout(RIGHT));
    rightPanel.add(projectButton);
    rightPanel.add(employeeButton);
    this.add(leftPanel);
    this.add(centerPanel);
    this.add(rightPanel);
    styleComponents();
  }

  public void setTitle(String title) {
    this.selectedViewTitle.setText(title.toUpperCase(Locale.ROOT));
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

  public void setHomeActionListener(ActionListener listener) {
    this.homeButton.addActionListener(listener);
  }

  private void styleComponents() {
    styleLayout();
    selectedViewTitle.setPreferredSize(new Dimension(390, 30));
  }

  private void styleLayout() {
    this.setLayout(new FlowLayout(CENTER));
    this.setPreferredSize(new Dimension(790, 60));
    this.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
  }
}
