package com.infoshareacademy.gitloopersi.service.statusvacationstatmanager;

import com.infoshareacademy.gitloopersi.dao.StatusVacationStatDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.StatusVacationStat;
import com.infoshareacademy.gitloopersi.types.StatusType;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatusVacationStatService {

  @Inject
  StatusVacationStatDaoBean statusVacationStatDaoBean;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public void incrementQuantityStatusVacationStat(StatusType statusType) {

    logger.info("StatusVacationStat object statusType={} goes to Dao to be incremented",
        statusType.getType());

    statusVacationStatDaoBean.updateStatusVacationIncrementQuantity(statusType);
  }

  public void addQuantityStatusVacationStat(StatusType statusType) {

    logger.info("StatusVacationStat object statusType={} goes to Dao to be created", statusType);

    StatusVacationStat statusVacationStat = new StatusVacationStat(statusType, 0);
    statusVacationStatDaoBean.addStatusVacation(statusVacationStat);
  }
}
