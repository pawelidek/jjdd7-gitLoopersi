package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.HolidayDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.types.HolidayType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class HolidayService {

  @EJB
  HolidayDaoBean holidayDaoBean;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public void addHoliday(String name, String date, String type,
      String description) {
    logger.info("New holiday object [{},{},{},{}] go to DAO to be saved in DB", name, date, type,
        description);
    Holiday holidayToSave = new Holiday();
    holidayToSave.setName(name);
    LocalDate holidayDate = LocalDate
        .parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    holidayToSave.setDate(holidayDate);
    HolidayType holidayType = HolidayType.deserialize(type);
    holidayToSave.setHolidayType(holidayType);
    holidayToSave.setDescription(description);
    holidayToSave.setCustom(true);
    holidayDaoBean.addHoliday(holidayToSave);
  }

  public Holiday findHolidayById(Integer id) {
    logger.info("Holiday object id={} go to DAO to be found in DB", id);
    return holidayDaoBean.getHolidayById(id);
  }

  public void modifyHoliday(Integer id, String name, String date, String type,
      String description) {
    logger.info("Holiday object id={} go to DAO to be modified in DB", id);
    Holiday holidayToChange = holidayDaoBean.getHolidayById(id);
    holidayToChange.setName(name);
    LocalDate holidayDate = LocalDate
        .parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    holidayToChange.setDate(holidayDate);
    HolidayType holidayType = HolidayType.deserialize(type);
    holidayToChange.setHolidayType(holidayType);
    holidayToChange.setDescription(description);
    holidayToChange.setCustom(true);
    holidayDaoBean.editHoliday(holidayToChange);
  }

  public void removeHoliday(String id) {
    logger.info("Holiday object id={} go to DAO to be removed in DB", id);
    Integer idToDelete = Integer.parseInt(id);
    holidayDaoBean.deleteHoliday(idToDelete);
  }

  public List<Holiday> findAllHolidays() {
    logger.info("Objects holidays go to DAO to be found in DB");
    return holidayDaoBean.getHolidaysList();
  }

  public List<Holiday> findHolidaysInRange(String date1, String date2) {
    logger.info(
        "Query findHolidaysInRange go to DAO to search holidays between dateStart={} and dateEnd={}",
        date1, date2);
    LocalDate dateStart = LocalDate
        .parse(date1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    LocalDate dateEnd = LocalDate
        .parse(date2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    return holidayDaoBean.getHolidaysInRange(dateStart, dateEnd);
  }

  public List<Holiday> findHolidaysByPattern(String pattern) {
    logger.info(
        "Query findHolidaysByPattern go to DAO to search holiday names in pattern={}", pattern);
    return holidayDaoBean.getHolidaysByPattern(pattern);
  }

  public Holiday findHolidayByName(String name) {
    logger.info(
        "Query findHolidayByName go to DAO to search holiday by name=", name);
    return holidayDaoBean.getHolidayByName(name);
  }
}