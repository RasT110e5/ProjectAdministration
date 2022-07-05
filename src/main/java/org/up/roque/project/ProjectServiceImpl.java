package org.up.roque.project;

import lombok.RequiredArgsConstructor;
import org.up.roque.project.employee.EmployeeService;

import java.util.Set;

@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
  private final ProjectCrudRepository repository;
  private final EmployeeService employeeService;

  @Override
  public Set<Project> findAll() {
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

  }

  @Override
  public void save(Project project) {

  }
}
