package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.StatusVacationStat;
import com.infoshareacademy.gitloopersi.types.StatusType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class StatusVacationStatDaoBean {

  @PersistenceContext
  private EntityManager entityManager;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<StatusVacationStat> getStatusVacations() {

    logger.info("StatusVacationStat objects are to be get from DB");
    Query query = entityManager
        .createNamedQuery("StatusVacation.findAll");

    return query.getResultList();
  }

  public void updateStatusVacationIncrementQuantity(StatusType statusType) {

    logger.info("StatusVacationStat object statusType={} is to be incremented in DB",
        statusType.getType());

    Query query = entityManager.createNamedQuery("StatusVacation.incrementQuantity");
    query.setParameter("statusType", statusType).executeUpdate();
  }

  public void addStatusVacation(StatusVacationStat statusVacationStat) {

    logger.info("StatusVacationStat object statusType={} is to be created in DB",
        statusVacationStat.getStatusType().getType());

    entityManager.persist(statusVacationStat);
  }
}
