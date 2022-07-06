package org.up.roque.project.task.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.up.roque.db.DataAccessException;
import org.up.roque.project.task.Task;
import org.up.roque.util.Entities;
import org.up.roque.util.TestDBTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskCrudRepositoryImplTest {
  TestDBTemplate testDBTemplate = new TestDBTemplate();
  TaskCrudRepository repository = new TaskCrudRepositoryImpl(testDBTemplate);

  @BeforeEach
  public void setup() {
    testDBTemplate.initSchema();
  }

  @AfterEach
  public void teardown() {
    testDBTemplate.teardown();
  }

  private Task saveNewTask() {
    Task entity = Entities.randomTask();
    return repository.save(entity);
  }

  @Test
  @DisplayName("should be able to save new Employee")
  void entityCrudRepositoryImplTest_() {
    Task entity = saveNewTask();
    assertThat(entity.getId()).isNotNull().isEqualTo(1);
  }

  @Test
  @DisplayName("should update entity when calling saving entity with id")
  void entityCrudRepositoryImplTest_1() {
    Task entity = saveNewTask();
    Task anotherEntity = Entities.randomTask();
    anotherEntity.setId(entity.getId());
    String newName = "different name";
    anotherEntity.setName(newName);
    repository.save(anotherEntity);
    assertThat(repository.getOne(entity.getId())).isEqualTo(entity)
        .extracting(Task::getName).isEqualTo(newName);
  }

  @Test
  @DisplayName("should return 1 entity if given id")
  void entityCrudRepositoryImplTest_3() {
    Task persistedEntity = saveNewTask();
    assertThat(repository.getOne(1)).isEqualTo(persistedEntity);
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
    saveNewTask();
    repository.delete(1);
    assertThrows(RuntimeException.class,
        () -> repository.getOne(1));
  }

  @Test
  @DisplayName("should be able to select all from table")
  void entityCrudRepositoryImplTest_4() {
    saveNewTask();
    saveNewTask();
    saveNewTask();
    assertThat(repository.findAll()).isNotEmpty().hasSize(3);
  }
}
