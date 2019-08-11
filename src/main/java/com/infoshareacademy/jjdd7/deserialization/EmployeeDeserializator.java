package com.infoshareacademy.jjdd7.deserialization;

import com.infoshareacademy.jjdd7.domain.Employee;

import java.util.List;

public interface EmployeeDeserializator {
    List<Employee> deserialize(String fileName);
}
