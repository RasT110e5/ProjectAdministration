package org.up.roque.db;

public interface Entity<T> {
  T getId();

  void setId(T id);
}
