package com.infoshareacademy.gitloopersi.service.monthvacationstatmanager;

import com.infoshareacademy.gitloopersi.dao.MonthVacationDaoStatBean;
import com.infoshareacademy.gitloopersi.domain.entity.MonthVacationStat;
import com.infoshareacademy.gitloopersi.domain.jsonapi.MonthVacationStatResponse;
import com.infoshareacademy.gitloopersi.web.mapper.MonthVacationStatMapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
public class MonthVacationStatApiService {

  @Inject
  private MonthVacationStatMapper monthVacationStatMapper;

  @Inject
  private MonthVacationDaoStatBean monthVacationDaoStatBean;

  @Transactional
  public List<MonthVacationStatResponse> getMonthVacationJsonObjects() {

    List<MonthVacationStat> monthVacationStats = monthVacationDaoStatBean.getMonthVacations();

    List<MonthVacationStatResponse> monthVacationStatResponses = new ArrayList<>();

    monthVacationStats.forEach(monthVacationStat -> {
      MonthVacationStatResponse monthVacationStatResponse = monthVacationStatMapper
          .mapMonthVacationStatEntityToApi(monthVacationStat);
      monthVacationStatResponses.add(monthVacationStatResponse);
    });

    return monthVacationStatResponses;
  }
}
