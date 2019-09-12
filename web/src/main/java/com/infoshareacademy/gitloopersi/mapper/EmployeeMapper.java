package com.infoshareacademy.gitloopersi.mapper;

import com.infoshareacademy.gitloopersi.domain.api.DateApi;
import com.infoshareacademy.gitloopersi.domain.api.EmployeeApi;
import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class EmployeeMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public EmployeeApi mapEntityToApi(Employee employeeEntity) {

    EmployeeApi employeeToJSON = new EmployeeApi();
    employeeToJSON.setFirstName(employeeEntity.getFirstName());
    employeeToJSON.setSecondName(employeeEntity.getSecondName());
    employeeToJSON.setTeam(employeeEntity.getTeam());

    DateApi startDateApi = new DateApi();
    startDateApi.setIso(employeeEntity.getStartDate().toString());
    employeeToJSON.setStartDate(startDateApi);

    DateApi startHireDateApi = new DateApi();
    startHireDateApi.setIso(employeeEntity.getStartHireDate().toString());
    employeeToJSON.setStartHireDate(startHireDateApi);

    logger.info("Employee has been mapped to API");

    return employeeToJSON;
  }
}
