package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.TeamVacationStat;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TeamVacationStatDaoBean {

  @PersistenceContext
  private EntityManager entityManager;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<TeamVacationStat> getTeamVacations() {

    logger.info("TeamVacationStat objects are to be get from DB");
    Query query = entityManager
        .createNamedQuery("TeamVacation.findAll");

    return query.getResultList();
  }

  public void updateTeamVacationIncrementQuantity(Long id) {



    Query query = entityManager.createNamedQuery("TeamVacation.incrementQuantity");
    query.setParameter("id", id).executeUpdate();
  }
}
