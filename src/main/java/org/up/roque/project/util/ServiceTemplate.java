package org.up.roque.project.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.up.roque.db.CrudRepository;
import org.up.roque.db.DataAccessException;
import org.up.roque.db.Entity;

import java.util.Set;

@RequiredArgsConstructor
@Slf4j
public abstract class ServiceTemplate<E extends Entity<ID>, ID> implements Service<E, ID> {
  protected final CrudRepository<E, ID> repository;

  @Override
  public E getById(ID id) {
    try {
      return repository.getOne(id);
    } catch (DataAccessException e) {
      log.warn("Exception while trying to get entity with id:'{}', exception: {}", id, e.toString());
      throw new ProcessingException("Entity couldn't be found");
    }
  }

  @Override
  public Set<E> findAll() {
    try {
      return repository.findAll();
    } catch (DataAccessException e) {
      log.warn("Exception while trying to fetch all entities, exception: {}", e.toString());
      throw new ProcessingException("Entities couldn't be found");
    }
  }

  @Override
  public void delete(E entity) {
    try {
      repository.delete(entity.getId());
    } catch (DataAccessException e) {
      log.warn("Exception while trying to delete {}, exception: {}", entity, e.toString());
      throw new ProcessingException("Entity couldn't be deleted");
    }
  }

  @Override
  public void save(E entity) {
    try {
      repository.save(entity);
    } catch (DataAccessException e) {
      log.warn("Exception while saving {}, exception: {}", entity, e.toString());
      throw new ProcessingException("Entity couldn't be saved");
    }
  }

}
