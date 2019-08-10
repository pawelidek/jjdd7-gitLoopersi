package com.infoshareacademy.jjdd7.serialization;

import com.infoshareacademy.jjdd7.domain.Employee;

import java.util.List;

public interface SerializationEmployee {
    void serialize(List<Employee> listOfEmployees, String fileName);
}
