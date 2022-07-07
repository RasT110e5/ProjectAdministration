package org.up.roque.project.ui;

import org.up.roque.project.Project;
import org.up.roque.project.task.Task;
import org.up.roque.project.task.TaskService;
import org.up.roque.project.task.ui.TaskTablePanel;
import org.up.roque.project.ui.custom.ProjectProgress;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.util.CustomPanel;
import org.up.roque.ui.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ProjectStatusPanel extends CustomPanel {
  private final Project project;
  private final ProjectProgress projectProgress;
  private final TaskTablePanel taskTablePanel;

  public ProjectStatusPanel(MainFrame frame, TaskService service, Project project) {
    super(project.getName(), frame);
    this.project = project;
    Set<Task> tasks = service.findAllByProject(project);
    taskTablePanel = new TaskTablePanel(frame, service, tasks, project);
    this.add(taskTablePanel);
    this.add(Box.createHorizontalStrut(25));
    projectProgress = new ProjectProgress(tasks);
    this.add(UIUtil.centerFlowPanelWithAlignment(projectProgress));
    this.setLayout(new FlowLayout(FlowLayout.CENTER));
  }
}
