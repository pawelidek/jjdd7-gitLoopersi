package com.infoshareacademy.gitloopersi.service.statusvacationstatmanager;

import com.infoshareacademy.gitloopersi.dao.StatusVacationStatDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.StatusVacationStat;
import com.infoshareacademy.gitloopersi.domain.jsonapi.StatusVacationStatResponse;
import com.infoshareacademy.gitloopersi.web.mapper.StatusVacationStatMapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
public class StatusVacationStatApiService {

  @Inject
  private StatusVacationStatMapper statusVacationStatMapper;

  @Inject
  private StatusVacationStatDaoBean statusVacationStatDaoBean;

  @Transactional
  public List<StatusVacationStatResponse> getStatusVacationStatJsonObjects() {

    List<StatusVacationStat> statusVacationStats = statusVacationStatDaoBean.getStatusVacations();

    List<StatusVacationStatResponse> statusVacationStatResponses = new ArrayList<>();

    statusVacationStats.forEach(statusVacationStat -> {
      StatusVacationStatResponse statusVacationStatResponse = statusVacationStatMapper
          .mapStatusVacationStatEntityToApi(statusVacationStat);
      statusVacationStatResponses.add(statusVacationStatResponse);
    });

    return statusVacationStatResponses;
  }
}
