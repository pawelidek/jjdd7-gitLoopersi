package com.infoshareacademy.gitLoopersi.menu;

import java.io.IOException;

public class ConsoleCleaner {

  private static final String os = System.getProperty("os.name").toLowerCase();

  public static void cleanConsole() {

    try {

      if (os.contains("win")) {

        Runtime.getRuntime().exec("cls");

      } else if (os.contains("mac")) {

        Runtime.getRuntime().exec("clear");

      } else {

        System.out.print("\033[H\033[2J");
        System.out.flush();

      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}