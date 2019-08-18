package com.infoshareacademy.gitLoopersi.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Serializator<T> {
    public void serialize(List<T> listOfItems, String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(listOfItems);
        } catch (IOException e) {
            System.out.println("Serialization has failed, please check output data");
        }
    }
}
