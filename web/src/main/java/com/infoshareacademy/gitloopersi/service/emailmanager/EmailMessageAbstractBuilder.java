package com.infoshareacademy.gitloopersi.service.emailmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class EmailMessageAbstractBuilder {

  String readStream(InputStream in) throws IOException {
    StringBuilder sb = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {
      String nextLine = "";
      while ((nextLine = reader.readLine()) != null) {
        sb.append(nextLine);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      in.close();
    }
    return sb.toString();
  }
}
