package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.EmployeeDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class EmployeeService {

  @EJB
  private EmployeeDaoBean employeeDaoBean;

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
