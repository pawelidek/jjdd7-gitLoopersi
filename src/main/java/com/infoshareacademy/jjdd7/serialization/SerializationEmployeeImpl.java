package com.infoshareacademy.jjdd7.serialization;

import com.infoshareacademy.jjdd7.domain.Employee;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializationEmployeeImpl implements SerializationEmployee {
    @Override
    public void serialize(List<Employee> listOfEmployees, String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listOfEmployees);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error with serialization - FileNotFoundException");
        } catch (IOException e) {
            System.out.println("Error with serialization -IOException");
        }
    }
}
