package com.infoshareacademy.gitloopersi.dao;


import com.infoshareacademy.gitloopersi.domain.Calendar;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class CalendarDaoBean {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @PersistenceContext
  EntityManager entityManager;

  public List<Calendar> getAllHolidaysDates() {
    logger.info("Holiday objects are to be get from DB");
    List<Calendar> foundHolidaysDates = entityManager.createQuery("Select h From Holiday h")
        .getResultList();
    return foundHolidaysDates;
  }

}
