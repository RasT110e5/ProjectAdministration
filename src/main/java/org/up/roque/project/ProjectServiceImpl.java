package org.up.roque.project;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.DataAccessException;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.project.task.Task;
import org.up.roque.project.task.TaskService;
import org.up.roque.project.util.ProcessingException;
import org.up.roque.project.util.ServiceTemplate;

import java.util.Set;

@Slf4j
public class ProjectServiceImpl extends ServiceTemplate<Project, Integer> implements ProjectService {
  private final ProjectCrudRepository projectRepository;
  private final EmployeeService employeeService;
  private final TaskService taskService;

  public ProjectServiceImpl(ProjectCrudRepository repository, EmployeeService employeeService, TaskService taskService) {
    super(repository);
    this.projectRepository = repository;
    this.employeeService = employeeService;
    this.taskService = taskService;
  }

  @Override
  public Set<Project> findAll() {
    log.info("Fetching all projects");
    try {
      Set<Project> projects = projectRepository.findAll();
      getEmployeesForProjects(projects);
      getTasksForProjects(projects);
      return projects;
    } catch (DataAccessException e) {
      log.info("Error while fetching all Projects and its tasks and employees, exception: {}", e.toString());
      throw new ProcessingException("Projects couldn't be found");
    }
  }

  private void getTasksForProjects(Set<Project> projects) {
    for (Project project : projects) addTasksToProject(project);
  }

  private void addTasksToProject(Project project) {
    Set<Task> tasks = taskService.findAllByProject(project);
    for (Task task : tasks) project.assign(task);
  }

  private void getEmployeesForProjects(Set<Project> projects) {
    for (Project project : projects) addEmployeesToProject(project, projectRepository.getEmployeeIds(project));
  }

  private void addEmployeesToProject(Project project, Set<Integer> employeeIds) {
    for (Integer employeeId : employeeIds) project.assign(employeeService.getById(employeeId));
  }

  @Override
  public Set<Project> findAllProjectsWithoutRelatedEntities() {
    return repository.findAll();
  }
}
