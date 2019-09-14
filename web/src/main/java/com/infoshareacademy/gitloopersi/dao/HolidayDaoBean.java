package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import java.time.LocalDate;
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
    logger.info("Holiday object {} is to be merged to DB", holidayToSave.toString());
    entityManager.merge(holidayToSave);
  }

  public void deleteHoliday(Integer id) {
    logger.info("Holiday object id={} is to be deleted in DB", id);
    Holiday holidayToDelete = getHolidayById(id);
    if (holidayToDelete != null) {
      entityManager.remove(holidayToDelete);
    } else {
      logger.warn("An attempt to delete non-existent holiday object id={} has been occured", id);
    }
  }

  public void updateHoliday(Holiday holiday) {
    logger.info("Holiday object id={} is to be updated in DB", holiday.getId());
    entityManager.merge(holiday);
  }

  public Holiday getHolidayById(Integer id) {
    logger.info("Holiday object id={} is to be get from DB", id);

    return entityManager.find(Holiday.class, id);
  }

  public List<Holiday> getAllHolidays() {
    logger.info("Holiday objects are to be get from DB");
    List<Holiday> foundHolidays = entityManager.createNamedQuery("Holiday.findAllHolidays")
        .getResultList();
    return foundHolidays;
  }
  public List<Holiday> getHolidaysInRange(LocalDate dateStart,LocalDate dateEnd) {
    logger.info("Holiday objects between dateStart={} and dateEnd={} are to be get from DB",
        dateStart, dateEnd);
    List<Holiday> foundHolidays = entityManager.createNamedQuery("Holiday.findHolidaysInRange")
        .setParameter("dateStart", dateStart)
        .setParameter("dateEnd", dateEnd)
        .getResultList();
    return foundHolidays;
  }
}
