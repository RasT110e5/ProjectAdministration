package org.up.roque.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.up.roque.db.DataAccessException;
import org.up.roque.util.Entities;
import org.up.roque.util.TestDBTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectCrudRepositoryImplTest {
  TestDBTemplate testDBTemplate = new TestDBTemplate();
  ProjectCrudRepositoryImpl repository = new ProjectCrudRepositoryImpl(testDBTemplate);

  @BeforeEach
  public void setup() {
    testDBTemplate.initSchema();
  }

  @AfterEach
  public void teardown() {
    testDBTemplate.teardown();
  }

  private Project saveNewProject() {
    Project project = Entities.randomProject();
    return repository.save(project);
  }

  @Test
  @DisplayName("should be able to save new Employee")
  void employeeCrudRepositoryImplTest_() {
    Project project = saveNewProject();
    assertThat(project.getId()).isNotNull().isEqualTo(1);
  }

  @Test
  @DisplayName("should update entity when calling saving entity with id")
  void employeeCrudRepositoryImplTest_1() {
    Project project = saveNewProject();
    Project anotherProject = Entities.randomProject();
    anotherProject.setId(project.getId());
    String newName = "different name";
    anotherProject.setName(newName);
    repository.save(anotherProject);
    assertThat(repository.getOne(project.getId())).isEqualTo(project)
        .extracting(Project::getName).isEqualTo(newName);
  }

  @Test
  @DisplayName("should return 1 employee if given id")
  void employeeCrudRepositoryImplTest_3() {
    Project persistedEmployee = saveNewProject();
    assertThat(repository.getOne(1)).isEqualTo(persistedEmployee);
  }

  @Test
  @DisplayName("should throw an exception if get doesn't find an entity")
  void employeeCrudRepositoryImplTest_5() {
    assertThrows(DataAccessException.class,
        () -> repository.getOne(123));
  }

  @Test
  @DisplayName("should delete an entity correctly")
  void employeeCrudRepositoryImplTest_2() {
    saveNewProject();
    repository.delete(1);
    assertThrows(RuntimeException.class,
        () -> repository.getOne(1));
  }

  @Test
  @DisplayName("should be able to select all from table")
  void employeeCrudRepositoryImplTest_4() {
    saveNewProject();
    saveNewProject();
    saveNewProject();
    assertThat(repository.findAll()).isNotEmpty().hasSize(3);
  }

}