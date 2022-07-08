package org.up.roque.ui;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.project.Project;
import org.up.roque.project.ProjectService;
import org.up.roque.ui.util.CustomPanel;
import org.up.roque.ui.util.OnClickActionListener;
import org.up.roque.ui.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Set;


@Slf4j
public class HomePanel extends CustomPanel {
  private final ProjectService projectService;

  public HomePanel(MainFrame frame, ProjectService projectService) {
    super("Project Administration", frame);
    this.projectService = projectService;

    addProjectLinks();
    addNewProjectButton();

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  private void addProjectLinks() {
    Set<Project> projects = projectService.findAll();
    for (Project project : projects) addProjectLink(project);
  }

  private void addProjectLink(Project project) {
    JLabel label = new JLabel("<HTML><U>%s</U></HTML>".formatted(project.getName()), SwingConstants.CENTER);
    label.addMouseListener(new OnClickActionListener(() -> this.frame.showProjectStatusView(project)));
    styleLabel(label);
    addLinkLabel(label);
  }

  private void addLinkLabel(JLabel label) {
    JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    labelPanel.add(label);
    this.add(labelPanel);
  }

  private void styleLabel(JLabel label) {
    label.setForeground(Color.BLUE);
    label.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 24));
  }

  private void addNewProjectButton() {
    JButton newProjectButton = new JButton("New Project");
    newProjectButton.addActionListener(e -> frame.showProjectCreateForm());
    this.add(Box.createVerticalStrut(20));
    this.add(UIUtil.centerFlowPanelWithAlignment(newProjectButton));
  }

}
