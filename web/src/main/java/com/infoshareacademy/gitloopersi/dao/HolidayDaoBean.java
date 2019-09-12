package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class HolidayDaoBean {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @PersistenceContext
  EntityManager entityManager;

  public void saveHoliday(Holiday holidayToSave) {
    logger.info("Object holiday merge to DB");
    entityManager.merge(holidayToSave);
  }

  public void deleteHoliday(Integer id) {
    logger.info("Object holiday id={} is to delate in DB", id);
    entityManager.remove(getHolidayById(id));
  }

  public void updateHoliday(Holiday holiday) {
    logger.info("Object holiday id={} is to update in DB", holiday.getId());
    entityManager.merge(holiday);
  }

  public Holiday getHolidayById(Integer id) {
    logger.info("Object holiday id={} is to get from DB", id);
    Holiday foundHoliday = entityManager.find(Holiday.class, id);
    return foundHoliday;
  }

  public List<Holiday> getAllHolidays() {
    logger.info("Objects holiday are to get from DB");
    List<Holiday> foundHolidays = entityManager.createQuery(
        "SELECT h FROM Holiday h")
        .getResultList();
    return foundHolidays;
  }
}
