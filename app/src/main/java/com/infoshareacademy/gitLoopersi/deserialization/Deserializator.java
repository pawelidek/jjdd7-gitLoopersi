package com.infoshareacademy.gitLoopersi.deserialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Deserializator<T> {

  public List<T> deserialize(T t, String fileName) {
    List<T> listOfItems = new ArrayList<>();
    try (FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
      File file = new File(fileName);
      if (file.exists()) {
        listOfItems = (List<T>) objectInputStream.readObject();
      }
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("Deserialization has failed, please check input data!");
    }
    return listOfItems;
  }
}
