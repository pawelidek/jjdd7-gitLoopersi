package com.infoshareacademy.gitloopersi.service.monthvacationstatmanager;

import com.infoshareacademy.gitloopersi.dao.MonthVacationStatDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.MonthVacationStat;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class MonthVacationStatService {

  @Inject
  MonthVacationStatDaoBean monthVacationStatDaoBean;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public void incrementQuantityMonthVacationStat(String month) {

    logger.info("MonthVacationStat object month={} goes to Dao to be incremented", month);

    monthVacationStatDaoBean.updateMonthVacationIncrementQuantity(month);
  }

  public void addQuantityMonthVacationStat(String month) {

    logger.info("MonthVacationStat object month={} goes to Dao to be created", month);

    MonthVacationStat monthVacationStat = new MonthVacationStat(month, 0);
    monthVacationStatDaoBean.addMonthVacationStat(monthVacationStat);
  }
}
