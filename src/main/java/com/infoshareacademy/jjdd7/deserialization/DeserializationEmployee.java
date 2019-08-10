package com.infoshareacademy.jjdd7.deserialization;

import com.infoshareacademy.jjdd7.domain.Employee;

import java.util.List;

public interface DeserializationEmployee {
    List<Employee> deserialize(String fileName);
}
