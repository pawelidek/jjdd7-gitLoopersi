package com.infoshareacademy.gitloopersi.restapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.mapper.EmployeeMapper;
import com.infoshareacademy.gitloopersi.service.EmployeeService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class EmployeeApiService {

  @EJB
  private EmployeeService employeeService;

  @EJB
  private EmployeeMapper employeeMapper;

  @Transactional
  public String getEmployeeJsonObjectById(Long id) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();

    Employee employeeById = employeeService.getEmployeeById(id);

    return objectMapper.writeValueAsString(employeeMapper.mapEntityToApi(employeeById));
  }
}
