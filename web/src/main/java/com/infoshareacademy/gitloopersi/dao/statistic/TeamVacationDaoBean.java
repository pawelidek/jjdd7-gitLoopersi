package com.infoshareacademy.gitloopersi.dao.statistic;

import com.infoshareacademy.gitloopersi.domain.entity.statistic.TeamVacation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TeamVacationDaoBean {

  @PersistenceContext
  private EntityManager entityManager;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<TeamVacation> getTeamVacations() {

    logger.info("TeamVacation objects are to be get from DB");
    Query query = entityManager
        .createNamedQuery("TeamVacation.findAll");

    return query.getResultList();
  }

  public void updateTeamVacationIncrementQuantity(Long id) {



    Query query = entityManager.createNamedQuery("TeamVacation.incrementQuantity");
    query.setParameter("id", id).executeUpdate();
  }
}
