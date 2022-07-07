package org.up.roque.project.task.ui.form;

import org.up.roque.project.Project;
import org.up.roque.project.task.Task;
import org.up.roque.project.task.TaskService;
import org.up.roque.project.task.TaskStatus;
import org.up.roque.ui.MainFrame;

public class CreateTaskForm extends TaskForm {
  public CreateTaskForm(MainFrame frame, TaskService service, Project project) {
    super("Create task", frame, service, project);
    saveButton.addActionListener(e -> save(project));
  }

  private void save(Project project) {
    submit(
        Task.builder()
            .name(super.getNameContent())
            .description(super.getDescriptionContent())
            .estimatedHours(super.getEstimationHours())
            .actualDuration(0)
            .status(TaskStatus.OPEN)
            .project(project)
            .assignedEmployee(super.getSelectedEmployee())
            .build()
    );
  }
}
