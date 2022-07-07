package org.up.roque.ui.custom;

import javax.swing.*;
import java.util.Enumeration;

public class EnumButtonGroup<T extends Enum<?>> extends JPanel {
  private final Class<T> enumClass;
  private final ButtonGroup group = new ButtonGroup();

  public EnumButtonGroup(Class<T> values) {
    this.enumClass = values;
    for (T enumVal : values.getEnumConstants()) {
      JRadioButton radio = new JRadioButton(enumVal.name());
      group.add(radio);
      this.add(radio);
    }
  }

  public void setSelected(T enumVal) {
    Enumeration<AbstractButton> elements = group.getElements();
    while (elements.hasMoreElements()) {
      AbstractButton button = elements.nextElement();
      if (isButtonThisValue(enumVal, button)) button.setSelected(true);
    }
  }

  private boolean isButtonThisValue(T enumVal, AbstractButton button) {
    return enumVal.name().equals(button.getActionCommand());
  }

  public T getSelectedValue() {
    Enumeration<AbstractButton> elements = group.getElements();
    while (elements.hasMoreElements()) {
      AbstractButton button = elements.nextElement();
      if (button.isSelected())
        return getFromEnum(button.getText());
    }
    return enumClass.getEnumConstants()[0];
  }

  private T getFromEnum(String text) {
    T[] values = enumClass.getEnumConstants();
    for (T val : values) {
      if(val.name().equals(text))
        return val;
    }
    return null;
  }
}