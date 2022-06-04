package org.up.roque;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.DBTemplate;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.project.employee.EmployeeServiceImpl;
import org.up.roque.ui.MainFrame;

@Slf4j
@RequiredArgsConstructor
public class Application {
  private final MainFrame FRAME;
  private final DBTemplate dbTemplate;

  private EmployeeService employeeService;

  private void start() {
    try {
      log.info("Starting application");
      startDB();
      startServices();
      startUI();
    } catch (Error e) {
      log.error("Error while initializing the application", e);
      stop();
    }
  }

  private void startServices() {
    employeeService = new EmployeeServiceImpl();
  }

  private void startUI() {
    FRAME.show();
  }

  private void startDB() {
    dbTemplate.initSchema();
    if (!dbTemplate.healthCheck())
      stop();
  }

  private void idle() {
  }

  private void stop() {
    log.info("Stopping application");
    System.exit(0);
  }

  public void run() {
    start();
    while (FRAME.isRunning()) idle();
    stop();
  }
}
