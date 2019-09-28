package com.infoshareacademy.gitloopersi.service.teamvacationstatmanager;

import com.infoshareacademy.gitloopersi.dao.TeamVacationStatDaoBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TeamVacationStatService {

  @Inject
  TeamVacationStatDaoBean teamVacationStatDaoBean;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public void incrementQuantityTeamVacationStat(Long id) {

    logger.info("TeamVacationStat object id={} goes to Dao to be incremented", id);

    teamVacationStatDaoBean.updateTeamVacationIncrementQuantity(id);
  }
}
