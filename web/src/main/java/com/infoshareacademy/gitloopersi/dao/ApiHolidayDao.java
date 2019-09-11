package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

  public Holiday getHolidayById(Integer id){
    return entityManager.find(Holiday.class,id);
  }
}
