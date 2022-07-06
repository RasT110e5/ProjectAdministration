package org.up.roque.project.ui;

import org.up.roque.project.Project;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.util.CustomPanel;

public class ProjectStatusPanel extends CustomPanel {
  private final Project project;
  private final ProjectProgress projectProgress;

  public ProjectStatusPanel(MainFrame frame, Project project) {
    super(project.getName(), frame);
    this.project = project;
    projectProgress = new ProjectProgress(project);
    this.add(projectProgress);
  }
}
