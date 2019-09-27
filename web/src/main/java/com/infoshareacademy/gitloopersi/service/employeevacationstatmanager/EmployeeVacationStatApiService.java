package com.infoshareacademy.gitloopersi.service.employeevacationstatmanager;

import com.infoshareacademy.gitloopersi.dao.EmployeeVacationStatDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.EmployeeVacationStat;
import com.infoshareacademy.gitloopersi.domain.jsonapi.EmployeeVacationStatResponse;
import com.infoshareacademy.gitloopersi.web.mapper.EmployeeVacationStatMapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
public class EmployeeVacationStatApiService {

  @Inject
  private EmployeeVacationStatMapper employeeVacationStatMapper;

  @Inject
  private EmployeeVacationStatDaoBean employeeVacationStatDaoBean;

  @Transactional
  public List<EmployeeVacationStatResponse> getEmployeeVacationStatJsonObjects() {

    List<EmployeeVacationStat> employeeVacationStats = employeeVacationStatDaoBean.getEmployeeVacations();

    List<EmployeeVacationStatResponse> employeeVacationStatResponses = new ArrayList<>();

    employeeVacationStats.forEach(employeeVacationStat -> {
      EmployeeVacationStatResponse employeeVacationStatResponse = employeeVacationStatMapper.mapEmplyeeVacationStatEntityToApi(employeeVacationStat);
      employeeVacationStatResponses.add(employeeVacationStatResponse);
    });

    return employeeVacationStatResponses;
  }
}
