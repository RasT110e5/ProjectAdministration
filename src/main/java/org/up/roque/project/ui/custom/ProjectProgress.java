package org.up.roque.project.ui.custom;

import org.up.roque.project.task.Task;
import org.up.roque.ui.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ProjectProgress extends JPanel {
  private double estimatedCost;
  private double actualCost;
  private int estimatedHours;
  private int actualHours;

  public ProjectProgress(Set<Task> tasks) {
    calculateProgress(tasks);
    ProgressDisplay estimationDisplay = new ProgressDisplay("Estimation", estimatedCost, estimatedHours);
    ProgressDisplay actualDisplay = new ProgressDisplay("Actual", actualCost, actualHours);

    addProgressLabel();
    this.add(estimationDisplay);
    this.add(actualDisplay);

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
  }

  private void calculateProgress(Set<Task> tasks) {
    for (Task task : tasks) calculateProgressIn(task);
  }

  private void calculateProgressIn(Task task) {
    Integer employeeCost = task.getAssignedEmployee().getCostPerHour();
    Integer estimatedTime = task.getEstimatedHours();
    Integer actualTime = task.getActualDuration();
    estimatedHours += estimatedTime;
    estimatedCost += employeeCost * estimatedTime;
    actualHours += actualTime;
    actualCost += employeeCost * actualTime;
  }


  private void addProgressLabel() {
    JLabel progressLabel = new JLabel("PROGRESS", SwingConstants.CENTER);
    progressLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
    this.add(UIUtil.centerFlowPanelWithAlignment(progressLabel));
  }

}
