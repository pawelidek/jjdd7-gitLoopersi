package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.HolidayDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.types.HolidayType;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class HolidayService {

  @EJB
  HolidayDaoBean holidayDaoBean;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public void addHoliday(String name, LocalDate date, HolidayType holidayType,
      String description) {
    logger.info("New object holiday go to DAO to save it in DB");
    Holiday holidayToSave = new Holiday();
    holidayToSave.setName(name);
    holidayToSave.setDate(date);
    holidayToSave.setHolidayType(holidayType);
    holidayToSave.setDescription(description);
    holidayToSave.setCustom(true);
    holidayDaoBean.saveHoliday(holidayToSave);
  }

  public Holiday findHolidayById(Integer id) {
    logger.info("Object holiday id={} go to DAO to find it in DB", id);
    return holidayDaoBean.getHolidayById(id);
  }

  public void modifyHoliday(Integer id, String name, LocalDate date, HolidayType holidayType,
      String description) {
    logger.info("Object holiday id={} go to DAO to modify it in DB", id);
    Holiday holidayToChange = holidayDaoBean.getHolidayById(id);
    holidayToChange.setName(name);
    holidayToChange.setDate(date);
    holidayToChange.setHolidayType(holidayType);
    holidayToChange.setDescription(description);
    holidayToChange.setCustom(true);
    holidayDaoBean.updateHoliday(holidayToChange);
  }

  public void removeHoliday(Integer id) {
    logger.info("Object holiday id={} go to DAO to remove it in DB", id);
    holidayDaoBean.deleteHoliday(id);
  }

  public List<Holiday> findAllHolidays() {
    logger.info("Objects holidays go to DAO to find them in DB");
    return holidayDaoBean.getAllHolidays();
  }
}
