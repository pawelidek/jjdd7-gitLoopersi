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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class EmployeeVacationStatApiService {

  @Inject
  private EmployeeVacationStatMapper employeeVacationStatMapper;

  @Inject
  private EmployeeVacationStatDaoBean employeeVacationStatDaoBean;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Transactional
  public List<EmployeeVacationStatResponse> getEmployeeVacationStatJsonObjects() {

    logger.info("EmployeeVacationStat list is to be converted to EmployeeVacationStatResponse");

    List<EmployeeVacationStat> employeeVacationStats = employeeVacationStatDaoBean
        .getEmployeeVacations();

    List<EmployeeVacationStatResponse> employeeVacationStatResponses = new ArrayList<>();

    employeeVacationStats.forEach(employeeVacationStat -> {
      EmployeeVacationStatResponse employeeVacationStatResponse = employeeVacationStatMapper
          .mapEmplyeeVacationStatEntityToApi(employeeVacationStat);
      employeeVacationStatResponses.add(employeeVacationStatResponse);
    });

    return employeeVacationStatResponses;
  }
}
