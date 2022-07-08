package org.up.roque.project.task.comment.ui.form;

import org.up.roque.project.task.Task;
import org.up.roque.project.task.comment.Comment;
import org.up.roque.project.task.comment.CommentService;
import org.up.roque.project.task.comment.ui.custom.CommentsDisplay;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.custom.NamedTextFieldPanel;
import org.up.roque.ui.util.CustomFormPanel;

public class CommentCreatePanel extends CustomFormPanel<Comment> {
  private final NamedTextFieldPanel content = new NamedTextFieldPanel("Content");
  private final CommentsDisplay display;

  public CommentCreatePanel(MainFrame frame, CommentService service, Task task,
                            CommentsDisplay commentsDisplay) {
    super("", frame, service);
    this.display = commentsDisplay;
    init(content);
    saveButton.addActionListener(e -> save(task));
  }

  private void save(Task task) {
    String commentText = this.content.getContent();
    if (contentIsNotEmpty(commentText)) {
      Comment comment = Comment.builder()
          .content(commentText)
          .task(task)
          .build();
      submit(comment);
      display.addComment(comment);
      clearContent();
    }
  }

  private void clearContent() {
    this.content.setContent("");
  }

  private boolean contentIsNotEmpty(String content) {
    return content != null && !content.isBlank();
  }
}
