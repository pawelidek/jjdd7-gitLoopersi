package com.infoshareacademy.jjdd7.serialization;

import com.infoshareacademy.jjdd7.domain.Employee;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class EmployeeSerializatorImpl implements EmployeeSerializator {
    @Override
    public void serialize(List<Employee> listOfEmployees, String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(listOfEmployees);
        } catch (Exception ex) {
            System.out.println("Serialization has failed, please check output data");
        }
    }
}
