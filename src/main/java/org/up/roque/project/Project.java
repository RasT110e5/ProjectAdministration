package org.up.roque.project;

import org.up.roque.project.employee.Employee;
import org.up.roque.project.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Project {
  private final List<Task> tasks;
  private final List<Employee> employees;

  public Project() {
    this.tasks = new ArrayList<>();
    this.employees = new ArrayList<>();
  }

  public boolean hasTask(Task task) {
    return this.tasks.contains(task);
  }

  public void assign(PartOfProject toBeAssigned){
    toBeAssigned.assignToProject(this);
    if (toBeAssigned instanceof Task)
      tasks.add((Task) toBeAssigned);
    else if (toBeAssigned instanceof Employee)
      employees.add((Employee) toBeAssigned);
  }

  public boolean hasEmployee(Employee employee) {
    return employees.contains(employee);
  }

  public void remove(Employee employee) {
    employee.removeFromProject(this);
    this.employees.remove(employee);
  }
}
