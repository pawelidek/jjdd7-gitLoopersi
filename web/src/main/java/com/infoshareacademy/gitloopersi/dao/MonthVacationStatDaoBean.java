package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.MonthVacationStat;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class MonthVacationStatDaoBean {

  @PersistenceContext
  private EntityManager entityManager;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<MonthVacationStat> getMonthVacations() {

    logger.info("MonthVacationStat objects are to be get from DB");
    Query query = entityManager
        .createNamedQuery("MonthVacation.findAll");

    return query.getResultList();
  }

  public void updateMonthVacationIncrementQuantity(String month) {

    logger.info("MonthVacationStat object month={} is to be incremented in DB", month);

    Query query = entityManager.createNamedQuery("MonthVacation.incrementQuantity");
    query.setParameter("month", month).executeUpdate();
  }

  public void addMonthVacationStat(MonthVacationStat monthVacationStat) {

    logger.info("MonthVacationStat object month={} is to be saved in DB",
        monthVacationStat.getMonth());

    entityManager.persist(monthVacationStat);
  }
}
