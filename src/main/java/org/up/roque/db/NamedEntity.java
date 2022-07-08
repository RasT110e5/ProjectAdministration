package org.up.roque.db;

public interface NamedEntity<T> extends Entity<T> {
  String getName();
}
