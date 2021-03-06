package org.up.roque.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.up.roque.db.DataAccessException;
import org.up.roque.db.util.DBTemplate;
import org.up.roque.util.Entities;
import org.up.roque.util.TestDBTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectCrudRepositoryImplTest {
  DBTemplate testDBTemplate = new TestDBTemplate();
  ProjectCrudRepository repository = new ProjectCrudRepositoryImpl(testDBTemplate);

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
  @DisplayName("should be able to save new entity")
  void entityCrudRepositoryImplTest_() {
    Project project = saveNewProject();
    assertThat(project.getId()).isNotNull().isEqualTo(1);
  }

  @Test
  @DisplayName("should update entity when calling saving entity with id")
  void entityCrudRepositoryImplTest_1() {
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
  @DisplayName("should return 1 entity if given id")
  void entityCrudRepositoryImplTest_3() {
    Project persistedProject = saveNewProject();
    assertThat(repository.getOne(1)).isEqualTo(persistedProject);
  }

  @Test
  @DisplayName("should throw an exception if get doesn't find an entity")
  void entityCrudRepositoryImplTest_5() {
    assertThrows(DataAccessException.class,
        () -> repository.getOne(123));
  }

  @Test
  @DisplayName("should delete an entity correctly")
  void entityCrudRepositoryImplTest_2() {
    saveNewProject();
    repository.delete(1);
    assertThrows(RuntimeException.class,
        () -> repository.getOne(1));
  }

  @Test
  @DisplayName("should be able to select all from table")
  void entityCrudRepositoryImplTest_4() {
    saveNewProject();
    saveNewProject();
    saveNewProject();
    assertThat(repository.findAll()).isNotEmpty().hasSize(3);
  }

}