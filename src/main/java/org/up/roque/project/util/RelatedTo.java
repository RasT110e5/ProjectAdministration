package org.up.roque.project.util;

import java.util.Set;

public interface RelatedTo<T, Z> {
  Set<T> findAllBy(Z relatedEntity);
}
