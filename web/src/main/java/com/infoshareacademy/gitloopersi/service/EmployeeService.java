package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.EmployeeDao;
import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class EmployeeService {

  @EJB
  private EmployeeDao employeeDao;

  public void addEmployee(Employee employee) {
    employeeDao.addEmployee(employee);
  }

  public Employee editEmployee(Employee employee) {
    return employeeDao.editEmployee(employee);
  }

  public Employee getEmployeeById(Long id) {
    return employeeDao.getEmployeeById(id);
  }

  public void deleteEmployeeById(Long id) {
    employeeDao.deleteEmployeeById(id);
  }

  public List<Employee> getEmployeesList() {
    return employeeDao.getEmployeesList();
  }

}
