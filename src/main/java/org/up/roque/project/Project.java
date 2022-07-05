package org.up.roque.project;

import lombok.*;
import org.up.roque.db.Entity;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.task.Task;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Project implements Entity<Integer> {
  @EqualsAndHashCode.Include
  private Integer id;
  private String name;
  @ToString.Exclude
  private final List<Task> tasks;
  @ToString.Exclude
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
