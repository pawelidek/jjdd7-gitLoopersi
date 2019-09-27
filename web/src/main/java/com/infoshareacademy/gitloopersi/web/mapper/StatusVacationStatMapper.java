package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.entity.StatusVacationStat;
import com.infoshareacademy.gitloopersi.domain.jsonapi.StatusVacationStatResponse;
import javax.ejb.Stateless;

@Stateless
public class StatusVacationStatMapper {

  public StatusVacationStatResponse mapStatusVacationStatEntityToApi(
      StatusVacationStat statusVacationStat) {

    StatusVacationStatResponse statusVacationStatResponse = new StatusVacationStatResponse();

    statusVacationStatResponse.setQuantity(statusVacationStat.getQuantity());
    statusVacationStatResponse.setStatus(statusVacationStat.getStatusType().getType());

    return statusVacationStatResponse;
  }
}
