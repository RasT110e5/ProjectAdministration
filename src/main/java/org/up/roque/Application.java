package org.up.roque;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.DBTemplateImpl;
import org.up.roque.db.util.DBTemplate;
import org.up.roque.project.ProjectCrudRepository;
import org.up.roque.project.ProjectCrudRepositoryImpl;
import org.up.roque.project.ProjectService;
import org.up.roque.project.ProjectServiceImpl;
import org.up.roque.project.employee.EmployeeCrudRepository;
import org.up.roque.project.employee.EmployeeCrudRepositoryImpl;
import org.up.roque.project.employee.EmployeeService;
import org.up.roque.project.employee.EmployeeServiceImpl;
import org.up.roque.project.task.TaskCrudRepositoryImpl;
import org.up.roque.project.task.TaskService;
import org.up.roque.project.task.TaskServiceImpl;
import org.up.roque.project.task.comment.CommentCrudRepository;
import org.up.roque.project.task.comment.CommentCrudRepositoryImpl;
import org.up.roque.project.task.comment.CommentService;
import org.up.roque.project.task.comment.CommentServiceImpl;
import org.up.roque.ui.MainFrame;

@Slf4j
public class Application {
  private final MainFrame frame;
  private final DBTemplate dbTemplate;

  @Getter
  private EmployeeService employeeService;
  @Getter
  private ProjectService projectService;
  @Getter
  private TaskService taskService;
  @Getter
  private CommentService commentService;

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
    EmployeeCrudRepository employeeRepo = new EmployeeCrudRepositoryImpl(dbTemplate);
    employeeService = new EmployeeServiceImpl(employeeRepo);
    ProjectCrudRepository projectRepo = new ProjectCrudRepositoryImpl(dbTemplate);
    TaskCrudRepositoryImpl taskRepo = new TaskCrudRepositoryImpl(dbTemplate, projectRepo, employeeRepo);
    CommentCrudRepository commentRepo = new CommentCrudRepositoryImpl(dbTemplate, taskRepo);
    commentService = new CommentServiceImpl(commentRepo);
    taskService = new TaskServiceImpl(taskRepo, commentService);
    projectService = new ProjectServiceImpl(projectRepo, employeeService, taskService);
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
    //No global action needed to take when idling
  }

  private void stop() {
    log.info("Stopping application");
    dbTemplate.teardown();
    System.exit(0);
  }
}
