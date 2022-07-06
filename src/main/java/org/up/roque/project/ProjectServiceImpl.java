package org.up.roque.project;

import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.DataAccessException;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.project.util.ProcessingException;
import org.up.roque.project.util.ServiceTemplate;

import java.util.Set;

@Slf4j
public class ProjectServiceImpl extends ServiceTemplate<Project, Integer> implements ProjectService {
  private final ProjectCrudRepository projectRepository;
  private final EmployeeService employeeService;

  public ProjectServiceImpl(ProjectCrudRepository repository, EmployeeService employeeService) {
    super(repository);
    this.projectRepository = repository;
    this.employeeService = employeeService;
  }

  @Override
  public Set<Project> findAll() {
    log.info("Fetching all projects");
    try {
      Set<Project> projects = projectRepository.findAll();
      getEmployeeForProjects(projects);
      return projects;
    } catch (DataAccessException e) {
      log.info("Error while fetching all Projects and its tasks and employees, exception: {}", e.toString());
      throw new ProcessingException("Projects couldn't be found");
    }
  }

  private void getEmployeeForProjects(Set<Project> projects) {
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
