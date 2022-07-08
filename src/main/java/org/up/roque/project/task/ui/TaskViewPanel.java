package org.up.roque.project.task.ui;

import org.up.roque.project.task.Task;
import org.up.roque.project.task.comment.CommentService;
import org.up.roque.project.task.comment.ui.CommentsPanel;
import org.up.roque.project.ui.custom.ProjectProgress;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.util.CustomPanel;
import org.up.roque.ui.util.UIUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Collections;

public class TaskViewPanel extends CustomPanel {

  public TaskViewPanel(MainFrame frame, CommentService commentService, Task task) {
    super(task.getName(), frame);
    this.add(new TaskDetailsDisplay(frame, commentService, task));
    this.add(new ProjectProgress(Collections.singleton(task)));
    this.setLayout(new FlowLayout(FlowLayout.CENTER));
  }

  private static class TaskDetailsDisplay extends JPanel {
    public TaskDetailsDisplay(MainFrame frame, CommentService service, Task task) {
      addFormattedDisplayLabel("Name: ", task.getName());
      addDescription(task.getDescription());
      addFormattedDisplayLabel("Status:", task.getStatus().name());
      addFormattedDisplayLabel("Project:", task.getProject().getName());
      addFormattedDisplayLabel("Assigned Employee:", task.getAssignedEmployee().getFormatedNameAndCost());
      addFormattedDisplayLabel("Created Date:", task.getCreatedDate().toString());
      this.add(UIUtil.centerFlowPanelWithAlignment(new CommentsPanel(frame, service, task)));
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      frame.refresh();
    }

    private void addFormattedDisplayLabel(String name, String value) {
      JLabel nameLabel = new JLabel("<html><u>%s</u></html>".formatted(name));
      nameLabel.setForeground(Color.GRAY);
      nameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 12));
      JLabel toDisplay = new JLabel(value, SwingConstants.CENTER);
      toDisplay.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
      this.add(UIUtil.centerFlowPanelWithAlignment(nameLabel, toDisplay));
    }

    private void addDescription(String description) {
      JLabel nameLabel = new JLabel("<html><u>Description:</u></html>");
      nameLabel.setForeground(Color.GRAY);
      nameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 12));
      JTextArea toDisplay = new JTextArea(description);
      toDisplay.setEditable(false);
      toDisplay.setCursor(null);
      toDisplay.setOpaque(false);
      toDisplay.setFocusable(false);
      toDisplay.setWrapStyleWord(true);
      toDisplay.setLineWrap(true);
      toDisplay.setBorder(new EmptyBorder(5, 5, 5, 5));
      toDisplay.setAlignmentY(Component.CENTER_ALIGNMENT);
      toDisplay.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
      this.add(nameLabel);
      this.add(toDisplay);
    }

  }
}
