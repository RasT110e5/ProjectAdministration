package org.up.roque.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.up.roque.project.employee.Employee;

public abstract class Entities {
  private Entities() {
  }

  public static Employee randomEmployee() {
    return Employee.builder()
        .name(RandomStringUtils.randomAlphabetic(5, 10))
        .costPerHour(RandomUtils.nextInt(15, 60))
        .build();
  }
}
