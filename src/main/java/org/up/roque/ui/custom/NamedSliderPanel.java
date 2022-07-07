package org.up.roque.ui.custom;

import org.up.roque.ui.util.UIUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class NamedSliderPanel extends JPanel {
  private static final String BASE_LABEL = "%s : %d";
  private final JSlider hoursSlider;
  private final JLabel nameLabel;
  private final String name;

  public NamedSliderPanel(String name, int selectedValue) {
    this.name = name;
    nameLabel = new JLabel(BASE_LABEL.formatted(name, selectedValue));
    hoursSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 72, selectedValue);
    this.add(UIUtil.centerFlowPanelWithAlignment(nameLabel));
    this.add(hoursSlider);

    styleSlider();
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  public Integer getSelectedValue() {
    return hoursSlider.getValue();
  }

  public void setSelectedValue(Integer value) {
    hoursSlider.setValue(value);
    nameLabel.setText(BASE_LABEL.formatted(name, value));
  }

  private void styleSlider() {
    hoursSlider.addChangeListener(this::getChangeListener);
    hoursSlider.setMinorTickSpacing(1);
    hoursSlider.setMajorTickSpacing(10);
    hoursSlider.setPaintTicks(true);
    hoursSlider.setPaintLabels(true);
    hoursSlider.setLabelTable(hoursSlider.createStandardLabels(5));
  }

  private void getChangeListener(ChangeEvent e) {
    nameLabel.setText(BASE_LABEL.formatted(name, ((JSlider) e.getSource()).getValue()));
  }
}
