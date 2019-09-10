package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.EmployeeDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class EmployeeService {

  @EJB
  private EmployeeDaoBean employeeDaoBean;

  public void addEmployee(Employee employee) {
    employeeDaoBean.addEmployee(employee);
  }

  public Employee editEmployee(Employee employee) {
    return employeeDaoBean.editEmployee(employee);
  }

  public Employee getEmployeeById(Long id) {
    return employeeDaoBean.getEmployeeById(id);
  }

  public void deleteEmployeeById(Long id) {
    employeeDaoBean.deleteEmployeeById(id);
  }

  public List<Employee> getEmployeesList() {
    return employeeDaoBean.getEmployeesList();
  }

}
