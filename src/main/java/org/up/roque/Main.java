package org.up.roque;

import org.up.roque.ui.MainFrame;

public class Main {
  public static void main(String[] args) {
    Application app = new Application(new MainFrame());
    app.run();
  }
}