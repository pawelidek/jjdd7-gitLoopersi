package com.infoshareacademy.gitloopersi.service.employeemanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoshareacademy.gitloopersi.domain.jsonapi.EmployeeResponse;
import com.infoshareacademy.gitloopersi.web.mapper.EmployeeMapper;
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