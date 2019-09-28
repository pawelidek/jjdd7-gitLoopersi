package com.infoshareacademy.gitloopersi.service.employeevacationstatmanager;

import com.infoshareacademy.gitloopersi.dao.EmployeeVacationStatDaoBean;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeVacationStatService {

  @Inject
  EmployeeVacationStatDaoBean employeeVacationStatDaoBean;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public void incrementQuantityEmployeeVacationStat(Long id) {

    logger.info("EmployeeVacationStat object id={} goes to Dao to be incremented", id);

    employeeVacationStatDaoBean.updateEmployeeVacationIncrementQuantity(id);
  }
}
