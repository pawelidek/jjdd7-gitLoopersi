package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class HolidayDaoBean {

  @PersistenceContext
  EntityManager entityManager;
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public void addHoliday(Holiday holiday) {
    logger.info("Holiday object {} is to be merged to DB", holiday.toString());
    entityManager.merge(holiday);
  }

  public void editHoliday(Holiday holiday) {
    logger.info("Holiday object id={} is to be updated in DB", holiday.getId());
    entityManager.merge(holiday);
  }

  public Holiday getHolidayById(Integer id) {
    logger.info("Holiday object id={} is to be get from DB", id);
    return entityManager.find(Holiday.class, id);
  }

  public void deleteHoliday(Integer id) {
    logger.info("Holiday object id={} is to be deleted from DB", id);
    Holiday holidayToDelete = getHolidayById(id);
    if (holidayToDelete != null) {
      entityManager.remove(holidayToDelete);
    } else {
      logger.warn("An attempt to delete non-existing holiday object id={} has occured", id);
    }
  }

  public List<Holiday> getHolidaysList() {
    logger.info("Holiday objects are to be get from DB");
    Query query = entityManager
        .createNamedQuery("Holiday.findAll");
    return query.getResultList();
  }

  public List<Holiday> getHolidaysInRange(LocalDate dateStart, LocalDate dateEnd) {
    logger.info("Holiday objects between dateStart={} and dateEnd={} are to be get from DB",
        dateStart, dateEnd);
    List<Holiday> foundHolidays = entityManager.createNamedQuery("Holiday.findHolidaysInRange")
        .setParameter("dateStart", dateStart)
        .setParameter("dateEnd", dateEnd)
        .getResultList();
    return foundHolidays;
  }

  public List<Holiday> getHolidaysByPattern(String pattern) {
    logger.info("Holiday objects started in pattern={} are to be get from DB", pattern);
    List<Holiday> foundHolidays = entityManager.createNamedQuery("Holiday.findHolidaysByPattern")
        .setParameter("pattern", "%" + pattern)
        .getResultList();
    return foundHolidays;
  }

  //  public Holiday getHolidayByName(String name) {
//    logger.info("Holiday object named={} are to be get from DB", name);
//    Holiday foundHoliday = (Holiday) entityManager.createNamedQuery("Holiday.findHolidayByName")
//        .setParameter("name", name)
//        .getSingleResult();
//    return foundHoliday;
//  }
  public Holiday getHolidayByName(String name) {
    logger.info("Holiday object named={} are to be get from DB", name);
    List<Holiday> foundHolidays = entityManager.createNamedQuery("Holiday.findHolidayByName")
        .setParameter("name", name)
        .getResultList();
    logger.info("List of holidays{}", foundHolidays);
    if (!foundHolidays.isEmpty()) {
      return foundHolidays.get(0);
    } else {
      return null;
    }
  }
}