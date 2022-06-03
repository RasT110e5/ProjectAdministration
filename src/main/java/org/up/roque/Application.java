package org.up.roque;

public class Application {
  public static void start() {


  }

  public static boolean idle() {
    return false;
  }

  public static void stop() {

  }

  public static void run() {
    start();
    while (idle());
    stop();
  }
}
