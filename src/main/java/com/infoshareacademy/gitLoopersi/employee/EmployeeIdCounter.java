package com.infoshareacademy.gitLoopersi.employee;

import java.io.*;

class EmployeeIdCounter {

  private static final String fileName = "id.txt";

  void incrementIdCounter() {

    String newCounterValue = String.valueOf(getIdCounter() + 1L);

    try (
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw)) {

      bw.write(newCounterValue);

    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    }
  }

  long getIdCounter() {

    StringBuilder sb = new StringBuilder();
    File yourFile = new File(fileName);
    try {
      yourFile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try (
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr)) {

      String counterValue = br.readLine();

      if (counterValue == null || counterValue.equals("")) {
        sb.append(1L);
      } else {
        sb.append(counterValue);
      }

    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    }
    return Long.parseLong(sb.toString());
  }
}