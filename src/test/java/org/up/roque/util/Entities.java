package org.up.roque.util;

import org.apache.commons.lang3.RandomUtils;
import org.up.roque.project.Project;
import org.up.roque.project.employee.Employee;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public abstract class Entities {
  private Entities() {
  }

  public static Employee randomEmployee() {
    return Employee.builder()
        .name(randomAlphabetic(5, 10))
        .costPerHour(RandomUtils.nextInt(15, 60))
        .build();
  }

  public static Project randomProject() {
    return Project.builder()
        .name(randomAlphanumeric(5, 25))
        .build();
  }
}
