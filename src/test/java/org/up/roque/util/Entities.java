package org.up.roque.util;

import org.apache.commons.lang3.RandomUtils;
import org.up.roque.project.Project;
import org.up.roque.project.employee.Employee;
import org.up.roque.project.task.Task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public abstract class Entities {
  private Entities() {
  }

  public static Employee randomEmployee() {
    return Employee.builder()
        .name(randomAlphabetic(5, 10))
        .costPerHour(nextInt(15, 60))
        .build();
  }

  public static Project randomProject() {
    return Project.builder()
        .name(randomAlphanumeric(5, 25))
        .build();
  }

  public static Task randomTask() {
    int estimatedHours = nextInt(3, 10);
    return Task.builder()
        .name(randomAlphanumeric(5, 25))
        .description(randomAlphanumeric(20, 100))
        .estimatedHours(estimatedHours)
        .createdDate(LocalDateTime.now().minus(nextInt(0, 73), ChronoUnit.HOURS))
        .actualDuration(estimatedHours + nextInt(0, 10))
        .project(randomProject())
        .assignedEmployee(randomEmployee())
        .build();
  }
}
