package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.web.view.EmployeeView;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class EmployeeViewMapper {

  @EJB
  private TeamViewMapper teamViewMapper;

  @Transactional
  public EmployeeView mapEntityToView(Employee employee) {

    EmployeeView employeeView = new EmployeeView();

    employeeView.setId(employee.getId());
    employeeView.setFirstName(employee.getFirstName());
    employeeView.setSecondName(employee.getSecondName());
    employeeView.setEmail(employee.getEmail());
    employeeView.setStartDate(employee.getStartDate());
    employeeView.setStartHireDate(employee.getStartHireDate());
    employeeView.setAdmin(employee.isAdmin());
    employeeView.setTeam(teamViewMapper.mapEntityToView(employee.getTeam()));

    return employeeView;
  }
}
