package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.EmployeeDao;
import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class EmployeeService {

  @EJB
  private EmployeeDao employeeDao;

  public Employee findEmployeeById(Long id) {
    return employeeDao.findEmployeeById(id);
  }
}
