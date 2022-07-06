package org.up.roque.project.ui;

import org.up.roque.project.Project;
import org.up.roque.project.util.UIUtil;

import javax.swing.*;
import java.awt.*;

public class ProjectProgress extends JPanel {
  public ProjectProgress(Project project) {
    ProgressDisplay estimationDisplay = new ProgressDisplay("Estimation", 850.0, 3);
    ProgressDisplay actualDisplay = new ProgressDisplay("Actual", 500.0, 10);

    addProgressLabel();
    this.add(estimationDisplay);
    this.add(actualDisplay);

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
  }

  private void addProgressLabel() {
    JLabel progressLabel = new JLabel("Progress:", SwingConstants.LEFT);
    progressLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
    this.add(UIUtil.leftFlowPanelWithAlignment(progressLabel));
  }

}
