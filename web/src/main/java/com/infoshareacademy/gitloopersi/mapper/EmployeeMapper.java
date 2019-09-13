package com.infoshareacademy.gitloopersi.mapper;

import com.infoshareacademy.gitloopersi.domain.api.Date;
import com.infoshareacademy.gitloopersi.domain.api.Employee;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class EmployeeMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private TeamMapper teamMapper;

  @Transactional
  public Employee mapEntityToApi(
      com.infoshareacademy.gitloopersi.domain.entity.Employee employee) {

    Employee employeeToJSON = new Employee();
    employeeToJSON.setFirstName(employee.getFirstName());
    employeeToJSON.setSecondName(employee.getSecondName());
    employeeToJSON.setTeam(teamMapper.mapEntityToApi(employee.getTeam()));

    Date startDate = new Date();
    startDate.setIso(employee.getStartDate().toString());
    employeeToJSON.setStartDate(startDate);

    Date startHireDate = new Date();
    startHireDate.setIso(employee.getStartHireDate().toString());
    employeeToJSON.setStartHireDate(startHireDate);

    logger.info("Employee has been mapped to API");

    return employeeToJSON;
  }
}
