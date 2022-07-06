package org.up.roque.ui.util;

import lombok.Value;

@Value
public class TableColumn<T, ENTITY> {
  String name;
  Class<T> type;
  ValueGetter<T, ENTITY> valueGetter;

  public T getValue(ENTITY e) {
    return this.valueGetter.get(e);
  }

  public interface ValueGetter<R, E> {
    R get(E entity);
  }
}
