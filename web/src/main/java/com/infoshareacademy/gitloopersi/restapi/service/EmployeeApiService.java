package com.infoshareacademy.gitloopersi.restapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoshareacademy.gitloopersi.domain.api.EmployeeResponse;
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
  public EmployeeResponse getEmployeeJsonObjectById(Long id) throws JsonProcessingException {
    return employeeMapper.mapEntityToApi(employeeService.getEmployeeById(id));
  }
}