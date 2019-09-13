package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ApiHolidayDao {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @PersistenceContext
  EntityManager entityManager;

  public void addHoliday(Holiday holiday) {
    logger.info("Object holiday merge to DB");
    entityManager.merge(holiday);
  }

  public List<Holiday> getHolidayList() {
    logger.info("Search all holidays in DB");
    Query query = entityManager.createQuery("SELECT h FROM Holiday h");
    return query.getResultList();
  }
}
