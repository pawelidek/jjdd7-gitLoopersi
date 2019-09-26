package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.statistic.MonthVacation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class MonthVacationDaoBean {

  @PersistenceContext
  private EntityManager entityManager;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<MonthVacation> getMonthVacations() {

    logger.info("MonthVacation objects are to be get from DB");
    Query query = entityManager
        .createNamedQuery("MonthVacation.findAll");

    return query.getResultList();
  }
}
