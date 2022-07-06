package org.up.roque.project;

import org.up.roque.project.util.Service;

import java.io.Serializable;
import java.util.Set;

public interface ProjectService extends Service<Project, Integer> {
  Set<Project> findAllProjectsWithoutRelatedEntities();
}
