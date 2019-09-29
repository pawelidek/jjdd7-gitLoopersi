package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.entity.MonthVacationStat;
import com.infoshareacademy.gitloopersi.domain.jsonapi.MonthVacationStatResponse;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class MonthVacationStatMapper {

  @Transactional
  public MonthVacationStatResponse mapMonthVacationStatEntityToApi(
      MonthVacationStat employeeVacationStat) {

    MonthVacationStatResponse monthVacationStatResponse = new MonthVacationStatResponse();

    monthVacationStatResponse.setMonth(employeeVacationStat.getMonth());
    monthVacationStatResponse.setQuantity(employeeVacationStat.getQuantity());

    return monthVacationStatResponse;
  }
}
