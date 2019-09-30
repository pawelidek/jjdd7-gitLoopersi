package com.infoshareacademy.gitloopersi.service.statinitializer;

import com.infoshareacademy.gitloopersi.googleoauth.GoogleLogin;
import com.infoshareacademy.gitloopersi.service.monthvacationstatmanager.MonthVacationStatService;
import com.infoshareacademy.gitloopersi.service.statusvacationstatmanager.StatusVacationStatService;
import com.infoshareacademy.gitloopersi.types.StatusType;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class StatisticInitialization {

  @EJB
  private MonthVacationStatService monthVacationStatService;

  @EJB
  private StatusVacationStatService statusVacationStatService;

  private Logger logger = LoggerFactory.getLogger(GoogleLogin.class.getName());

  @PostConstruct
  protected void init() {

    logger.info("Month records are to be initially loaded");

    addRecordsStatMonthVacation();

    logger.info("Status records are to be initially loaded");

    addRecordsStatStatusVacation();
  }

  private void addRecordsStatMonthVacation() {

    List<String> months = List
        .of("January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December");
    months.forEach(month -> monthVacationStatService.addQuantityMonthVacationStat(month));
  }

  private void addRecordsStatStatusVacation() {

    List<StatusType> statusTypes = List.of(StatusType.ACCEPTED, StatusType.REJECTED);
    statusTypes
        .forEach(statusType -> statusVacationStatService.addQuantityStatusVacationStat(statusType));
  }
}
