package org.up.roque;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.DBTemplateImpl;
import org.up.roque.db.util.DBTemplate;
import org.up.roque.project.ProjectCrudRepositoryImpl;
import org.up.roque.project.ProjectService;
import org.up.roque.project.ProjectServiceImpl;
import org.up.roque.project.employee.EmployeeCrudRepositoryImpl;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.project.employee.EmployeeServiceImpl;
import org.up.roque.ui.MainFrame;

@Slf4j
public class Application {
  private final MainFrame frame;
  private final DBTemplate dbTemplate;

  @Getter
  private EmployeeService employeeService;
  @Getter
  private ProjectService projectService;

  public Application() {
    this.frame = new MainFrame();
    this.dbTemplate = new DBTemplateImpl();
  }

  public void run() {
    start();
    while (frame.isRunning()) idle();
    stop();
  }

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
    employeeService = new EmployeeServiceImpl(new EmployeeCrudRepositoryImpl(dbTemplate));
    projectService = new ProjectServiceImpl(new ProjectCrudRepositoryImpl(dbTemplate), employeeService);
  }

  private void startUI() {
    this.frame.init(this);
    frame.show();
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
    dbTemplate.teardown();
    System.exit(0);
  }
}
