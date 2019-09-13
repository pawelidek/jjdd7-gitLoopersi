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
    logger.info("Object holiday id={} go to DAO to be found in DB", id);
    return holidayDaoBean.getHolidayById(id);
  }

  public void modifyHoliday(Integer id, String name, String date, String type,
      String description) {
    logger.info("Object holiday id={} go to DAO to be modified in DB", id);
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
    logger.info("Object holiday id={} go to DAO to be removed in DB", id);
    Integer idToDelete = Integer.parseInt(id);
    holidayDaoBean.deleteHoliday(idToDelete);
  }

  public List<Holiday> findAllHolidays() {
    logger.info("Objects holidays go to DAO to be found in DB");
    return holidayDaoBean.getHolidaysList();
  }
}
