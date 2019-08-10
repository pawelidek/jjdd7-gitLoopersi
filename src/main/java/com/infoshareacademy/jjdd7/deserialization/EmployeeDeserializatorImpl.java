package com.infoshareacademy.jjdd7.deserialization;

import com.infoshareacademy.jjdd7.domain.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDeserializatorImpl implements EmployeeDeserializator {
    @Override
    public List<Employee> deserialize(String fileName) {
        List<Employee> listOfEmployees = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            File file = new File(fileName);
            if (file.exists()) {
                listOfEmployees = (List<Employee>) objectInputStream.readObject();
            }
        } catch (Exception ex) {
            System.out.println("Deserialization has failed, please check input data");
        }
        return listOfEmployees;
    }
}
