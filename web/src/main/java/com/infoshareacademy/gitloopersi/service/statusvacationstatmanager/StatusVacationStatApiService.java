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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class StatusVacationStatApiService {

  @Inject
  private StatusVacationStatMapper statusVacationStatMapper;

  @Inject
  private StatusVacationStatDaoBean statusVacationStatDaoBean;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Transactional
  public List<StatusVacationStatResponse> getStatusVacationStatJsonObjects() {

    logger.info("StatusVacationStat list is to be converted to StatusVacationStatResponse");

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
