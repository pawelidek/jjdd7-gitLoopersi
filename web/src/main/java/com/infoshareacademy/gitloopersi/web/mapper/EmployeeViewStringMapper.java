package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.web.view.EmployeeViewString;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class EmployeeViewStringMapper {

  @EJB
  private TeamViewMapper teamViewMapper;

  @Transactional
  public EmployeeViewString mapEntityToView(Employee employee) {

    EmployeeViewString employeeViewString = new EmployeeViewString();

    employeeViewString.setId(employee.getId());
    employeeViewString.setFirstName(employee.getFirstName());
    employeeViewString.setSecondName(employee.getSecondName());
    employeeViewString.setEmail(employee.getEmail());
    employeeViewString.setStartDate(employee.getStartDate().toString());
    employeeViewString.setStartHireDate(employee.getStartHireDate().toString());
    employeeViewString.setAdmin(employee.isAdmin());
    employeeViewString.setTeam(teamViewMapper.mapEntityToView(employee.getTeam()));

    return employeeViewString;
  }
}
