package com.infoshareacademy.jjdd7.deserialization;

import com.infoshareacademy.jjdd7.domain.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class DeserializationEmployeeImpl implements DeserializationEmployee {
    @Override
    public List<Employee> deserialize(String fileName) {
        List<Employee> listOfEmployees = new ArrayList<>();
        try {
            File file = new File(fileName);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                listOfEmployees = (List<Employee>) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error with deserialization - IOE");
        } catch (IOException e) {
            System.out.println("Error with deserialization - Class not found");
        }
        return listOfEmployees;
    }
}
