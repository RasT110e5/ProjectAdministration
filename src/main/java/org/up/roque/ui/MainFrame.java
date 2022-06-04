package org.up.roque.ui;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.project.employee.ui.EmployeeGridPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Slf4j
public class MainFrame extends WindowAdapter {
  public static final JPanel MAIN_PANEL = new JPanel(new FlowLayout(FlowLayout.CENTER));
  private static final JFrame J_FRAME = new JFrame("Project Administration");
  private boolean running;

  private final HomePanelPlaceholder homePanel = new HomePanelPlaceholder();
  private final EmployeeGridPanel employeeGridPanel = new EmployeeGridPanel();
  private final HeaderPanel headerPanel = new HeaderPanel();

  public void show() {
    this.running = true;
    setUpFrame();
    styleFrame();
    setHeader();
    MAIN_PANEL.add(homePanel);
    J_FRAME.setVisible(true);
  }

  private void setHeader() {
    headerPanel.setTitle(homePanel.getTitle());
    headerPanel.setReturnActionListener(e -> showHome());
    headerPanel.setEmployeeActionListener(e -> showEmployeeGrid());
    MAIN_PANEL.add(headerPanel);
  }

  public void showHome() {
    navigate(homePanel);
  }

  public void showEmployeeGrid() {
    navigate(employeeGridPanel);
  }

  private void navigate(CustomPanel navigateTo) {
    MAIN_PANEL.remove(1);
    MAIN_PANEL.add(navigateTo);
    headerPanel.setTitle(navigateTo.getTitle());
    MAIN_PANEL.validate();
    MAIN_PANEL.repaint();
  }

  private void styleFrame() {
    J_FRAME.setBounds(250, 250, 800, 600);
    stylePanel();
    J_FRAME.add(MAIN_PANEL);
  }

  private void stylePanel() {
    MAIN_PANEL.setBounds(0, 0, 800, 100);
  }

  private void setUpFrame() {
    J_FRAME.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    J_FRAME.addWindowListener(this);
  }

  public synchronized boolean isRunning() {
    return running;
  }

  @Override
  public void windowClosing(WindowEvent e) {
    log.info("Closing");
    J_FRAME.setVisible(false);
    this.running = false;
  }
}
