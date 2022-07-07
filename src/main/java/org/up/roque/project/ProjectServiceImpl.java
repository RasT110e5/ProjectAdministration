package org.up.roque.project;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.DataAccessException;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.project.task.TaskService;
import org.up.roque.project.util.ProcessingException;
import org.up.roque.project.util.ServiceTemplate;

import java.util.Set;

@Slf4j
public class ProjectServiceImpl extends ServiceTemplate<Project, Integer> implements ProjectService {
  private final EmployeeService employeeService;
  private final TaskService taskService;

  public ProjectServiceImpl(ProjectCrudRepository repository, EmployeeService employeeService, TaskService taskService) {
    super(repository);
    this.employeeService = employeeService;
    this.taskService = taskService;
  }

  @Override
  public Set<Project> findAll() {
    log.info("Fetching all projects");
    try {
      Set<Project> projects = repository.findAll();
      getEmployeesForProjects(projects);
      getTasksForProjects(projects);
      return projects;
    } catch (DataAccessException e) {
      log.info("Error while fetching all Projects and its tasks and employees, exception: {}", e.toString());
      throw new ProcessingException("Projects couldn't be found");
    }
  }

  private void getTasksForProjects(Set<Project> projects) {
    for (Project project : projects)
      assignToProject(taskService.findAllBy(project), project);
  }

  private void getEmployeesForProjects(Set<Project> projects) {
    for (Project project : projects)
      assignToProject(employeeService.findAllBy(project), project);
  }

  private void assignToProject(Set<? extends PartOfProject> itemsToAssign, Project project) {
    itemsToAssign.forEach(project::assign);
  }
}
