package org.up.roque.project.task.comment.ui.custom;

import org.up.roque.project.task.comment.Comment;
import org.up.roque.ui.MainFrame;

import javax.swing.*;
import java.util.List;

public class CommentsDisplay extends JPanel {
  private final MainFrame frame;

  public CommentsDisplay(MainFrame frame, List<Comment> comments) {
    this.frame = frame;
    comments.forEach(this::addComment);
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  public void addComment(Comment c) {
    JLabel label = new JLabel(c.getContent(), SwingConstants.CENTER);
    this.add(label);
    this.add(Box.createVerticalStrut(5));
    frame.refresh();
  }

}
