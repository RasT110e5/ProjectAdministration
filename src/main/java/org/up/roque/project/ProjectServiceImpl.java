package org.up.roque.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.up.roque.project.employee.EmployeeService;

import java.util.Set;

@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {
  private final ProjectCrudRepository repository;
  private final EmployeeService employeeService;

  @Override
  public Set<Project> findAll() {
    log.info("Fetching all projects");
    Set<Project> projects = repository.findAll();
    getEmployeeForProjects(projects);
    return projects;
  }

  private void getEmployeeForProjects(Set<Project> projects) {
    for (Project project : projects) addEmployeesToProject(project, repository.getEmployeeIds(project));
  }

  private void addEmployeesToProject(Project project, Set<Integer> employeeIds) {
    for (Integer employeeId : employeeIds) project.assign(employeeService.getOne(employeeId));
  }

  @Override
  public void delete(Project project) {
    log.info("Deleting {}", project);
    repository.delete(project.getId());
  }

  @Override
  public void save(Project project) {
    log.info("Saving {}", project);
    repository.save(project);
  }
}
