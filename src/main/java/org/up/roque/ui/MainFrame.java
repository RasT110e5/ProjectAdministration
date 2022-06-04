package org.up.roque.ui;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.up.roque.Application;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.project.employee.ui.CreateEmployeeForm;
import org.up.roque.project.employee.ui.EmployeeTablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Slf4j
public class MainFrame extends WindowAdapter {
  @Getter
  private final JFrame jFrame = new JFrame("Project Administration");
  public final JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
  private final HomePanelPlaceholder homePanel = new HomePanelPlaceholder(this);
  private final HeaderPanel headerPanel = new HeaderPanel();

  private EmployeeService employeeService;
  private boolean running;

  public void init(Application application) {
    this.employeeService = application.getEmployeeService();
  }

  public void show() {
    setUpFrame();
    styleFrame();
    setHeader();

    mainPanel.add(homePanel);
    jFrame.setVisible(true);
    this.running = true;
  }

  private void setHeader() {
    headerPanel.setTitle(homePanel.getTitle());
    headerPanel.setReturnActionListener(e -> showHome());
    headerPanel.setEmployeeActionListener(e -> showEmployeeGrid());
    mainPanel.add(headerPanel);
  }

  public void showHome() {
    navigate(homePanel);
  }

  public void showEmployeeGrid() {
    navigate(new EmployeeTablePanel(this, employeeService));
  }

  public void showEmployeeCreateForm() {
    navigate(new CreateEmployeeForm(this, employeeService));
  }

  private void navigate(CustomPanel navigateTo) {
    mainPanel.remove(1);
    mainPanel.add(navigateTo);
    headerPanel.setTitle(navigateTo.getTitle());
    mainPanel.validate();
    mainPanel.repaint();
  }

  private void styleFrame() {
    jFrame.setBounds(250, 250, 800, 600);
    stylePanel();
    jFrame.add(mainPanel);
  }

  private void stylePanel() {
    mainPanel.setBounds(0, 0, 800, 100);
  }

  private void setUpFrame() {
    jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    jFrame.addWindowListener(this);
  }

  public synchronized boolean isRunning() {
    return running;
  }

  @Override
  public void windowClosing(WindowEvent e) {
    log.info("Closing");
    jFrame.setVisible(false);
    this.running = false;
  }
}
