package org.up.roque.ui;

import lombok.Getter;
import org.up.roque.ui.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Locale;

import static java.awt.FlowLayout.CENTER;

@Getter
public class HeaderPanel extends JPanel {
  private final JButton returnButton = new JButton("Return");
  private final JButton homeButton = new JButton("Home");
  private final JLabel selectedViewTitle = new JLabel("", SwingConstants.CENTER);
  private final JButton projectButton = new JButton("Projects");
  private final JButton employeeButton = new JButton("Employees");

  public HeaderPanel() {
    this.add(UIUtil.leftFlowPanelWithAlignment(returnButton, homeButton));
    this.add(UIUtil.centerFlowPanelWithAlignment(selectedViewTitle));
    this.add(UIUtil.rightFlowPanelWithAlignment(projectButton, employeeButton));
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
