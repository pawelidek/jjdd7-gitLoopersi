package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.entity.EmployeeVacationStat;
import com.infoshareacademy.gitloopersi.domain.jsonapi.EmployeeVacationStatResponse;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class EmployeeVacationStatMapper {

  @Transactional
  public EmployeeVacationStatResponse mapEmplyeeVacationStatEntityToApi(
      EmployeeVacationStat employeeVacationStat) {

    EmployeeVacationStatResponse employeeVacationStatResponse = new EmployeeVacationStatResponse();

    String fullName = String.format("%s %s", employeeVacationStat.getEmployee().getFirstName(),
        employeeVacationStat.getEmployee().getSecondName());

    employeeVacationStatResponse.setFullName(fullName);
    employeeVacationStatResponse.setQuantity(employeeVacationStat.getQuantity());

    return employeeVacationStatResponse;
  }
}
