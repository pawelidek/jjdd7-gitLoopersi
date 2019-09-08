package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.entity.Employee;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EmployeeDao {

  void addEmployee(Employee employee);

  Employee editEmployee(Employee employee);

  Employee getEmployeeById(Long id);

  void deleteEmployeeById(Long id);

  List<Employee> getEmployeesList();

}