package org.up.roque.project.task.ui.form;

import org.up.roque.project.Project;
import org.up.roque.project.task.Task;
import org.up.roque.project.task.TaskService;
import org.up.roque.project.task.TaskStatus;
import org.up.roque.ui.MainFrame;
import org.up.roque.ui.custom.EnumButtonGroup;
import org.up.roque.ui.custom.NamedSliderPanel;

public class EditTaskForm extends TaskForm {
  private final NamedSliderPanel actualHours;
  private final EnumButtonGroup<TaskStatus> status = new EnumButtonGroup<>(TaskStatus.class);
  private final Task toEdit;

  public EditTaskForm(MainFrame frame, TaskService service, Project project, Task task) {
    super("Edit task", frame, service, project);
    this.toEdit = task;
    super.setNameContent(task.getName());
    super.setDescriptionContent(task.getDescription());
    super.setEstimatedHours(task.getEstimatedHours());
    super.setSelectedEmployee(task.getAssignedEmployee());
    actualHours = new NamedSliderPanel("Hours worked so far", task.getActualDuration());
    super.addExtraComponents(actualHours);
    status.setSelected(task.getStatus());
    super.addExtraComponents(status);
    saveButton.addActionListener(e -> update());
  }

  private void update() {
    toEdit.setName(super.getNameContent());
    toEdit.setDescription(super.getDescriptionContent());
    toEdit.setEstimatedHours(super.getEstimationHours());
    toEdit.setAssignedEmployee(super.getSelectedEmployee());
    toEdit.setActualDuration(this.actualHours.getSelectedValue());
    toEdit.setStatus(status.getSelectedValue());
    submit(toEdit);
  }
}
