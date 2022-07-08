package org.up.roque.project.task.ui;

import org.up.roque.project.task.Task;
import org.up.roque.project.ui.custom.ProjectProgress;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.util.CustomPanel;
import org.up.roque.ui.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class TaskViewPanel extends CustomPanel {

  public TaskViewPanel(MainFrame frame, Task task) {
    super(task.getName(), frame);
    this.add(new TaskDetailsDisplay(task));
    this.add(new ProjectProgress(Collections.singleton(task)));
    this.setLayout(new FlowLayout(FlowLayout.CENTER));
  }

  private static class TaskDetailsDisplay extends JPanel {
    public TaskDetailsDisplay(Task task) {
      addFormattedDisplayLabel("Name: ", task.getName());
      addFormattedDisplayLabel("Description:", task.getDescription());
      addFormattedDisplayLabel("Status:", task.getStatus().name());
      addFormattedDisplayLabel("Project:", task.getProject().getName());
      addFormattedDisplayLabel("Assigned Employee:", task.getAssignedEmployee().getFormatedNameAndCost());
      addFormattedDisplayLabel("Created Date:", task.getCreatedDate().toString());
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void addFormattedDisplayLabel(String name, String value) {
      JLabel nameLabel = new JLabel("<html><u>%s</u></html>".formatted(name));
      nameLabel.setForeground(Color.GRAY);
      nameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 12));
      JLabel toDisplay = new JLabel(value, SwingConstants.CENTER);
      toDisplay.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
      this.add(UIUtil.centerFlowPanelWithAlignment(nameLabel, toDisplay));
    }

  }
}
