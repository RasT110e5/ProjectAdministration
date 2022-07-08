package org.up.roque.project.task.comment.ui;

import org.up.roque.project.task.Task;
import org.up.roque.project.task.comment.CommentService;
import org.up.roque.project.task.comment.ui.custom.CommentsDisplay;
import org.up.roque.project.task.comment.ui.form.CommentCreatePanel;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.util.UIUtil;

import javax.swing.*;
import java.util.ArrayList;

public class CommentsPanel extends JPanel {

  public CommentsPanel(MainFrame frame, CommentService service, Task task) {
    JLabel title = new JLabel("COMMENTS", SwingConstants.CENTER);
    this.add(UIUtil.centerFlowPanelWithAlignment(title));
    CommentsDisplay commentsDisplay = new CommentsDisplay(frame, new ArrayList<>(service.findAllBy(task)));
    this.add(new CommentCreatePanel(frame, service, task, commentsDisplay));
    this.add(UIUtil.centerFlowPanelWithAlignment(commentsDisplay));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

}
