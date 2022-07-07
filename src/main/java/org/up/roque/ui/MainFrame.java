package org.up.roque.ui;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.up.roque.Application;
import org.up.roque.project.Project;
import org.up.roque.project.ProjectService;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.project.employee.ui.EmployeeTablePanel;
import org.up.roque.project.employee.ui.form.CreateEmployeeForm;
import org.up.roque.project.employee.ui.form.EditEmployeeForm;
import org.up.roque.project.task.TaskService;
import org.up.roque.project.ui.ProjectStatusPanel;
import org.up.roque.project.ui.ProjectTablePanel;
import org.up.roque.project.ui.form.CreateProjectForm;
import org.up.roque.project.ui.form.EditProjectForm;
import org.up.roque.ui.util.CustomPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Slf4j
public class MainFrame extends WindowAdapter {
  @Getter
  private final JFrame jFrame = new JFrame("Project Administration");
  private final JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
  private final HeaderPanel headerPanel = new HeaderPanel();

  private CustomPanel previousPanel;
  private HomePanel homePanel;
  private EmployeeService employeeService;
  private ProjectService projectService;
  private TaskService taskService;
  private boolean running;

  public void init(Application application) {
    this.employeeService = application.getEmployeeService();
    this.projectService = application.getProjectService();
    this.taskService = application.getTaskService();
  }

  public void show() {
    homePanel = new HomePanel(this, projectService);
    setUpFrame();
    styleFrame();
    setHeader();

    previousPanel = homePanel;
    mainPanel.add(homePanel);
    jFrame.setVisible(true);
    this.running = true;
  }

  private void setHeader() {
    headerPanel.setTitle(homePanel.getTitle());
    headerPanel.setReturnActionListener(e -> showReturn());
    headerPanel.setEmployeeActionListener(e -> showEmployeeView());
    headerPanel.setProjectActionListener(e -> showProjectView());
    headerPanel.setHomeActionListener(e -> showHome());
    mainPanel.add(headerPanel);
  }

  private void showReturn() {
    navigate(previousPanel);
  }

  public void showProjectView() {
    navigate(new ProjectTablePanel(this, projectService));
  }

  public void showProjectCreateForm() {
    navigate(new CreateProjectForm(this, projectService, employeeService));
  }

  public void showProjectEditForm(Project project) {
    navigate(new EditProjectForm(this, projectService, employeeService, project));
  }

  public void showHome() {
    navigate(new HomePanel(this, projectService));
  }

  public void showEmployeeView() {
    navigate(new EmployeeTablePanel(this, employeeService));
  }

  public void showEmployeeCreateForm() {
    navigate(new CreateEmployeeForm(this, employeeService));
  }

  public void showEmployeeEditForm(Employee employee) {
    navigate(new EditEmployeeForm(this, employeeService, employee));
  }

  public void showProjectStatusView(Project project) {
    navigate(new ProjectStatusPanel(this, taskService, project));
  }

  private void navigate(CustomPanel navigateTo) {
    previousPanel = (CustomPanel) mainPanel.getComponent(1);
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
