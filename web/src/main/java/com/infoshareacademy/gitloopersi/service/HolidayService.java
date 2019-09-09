package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.HolidayDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.types.HolidayType;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class HolidayService {

  @EJB
  HolidayDaoBean holidayDaoBean;

  public void addHoliday(String name, LocalDate date, HolidayType holidayType,
      String description) {
    Holiday holidayToSave = new Holiday();
    holidayToSave.setName(name);
    holidayToSave.setDate(date);
    holidayToSave.setHolidayType(holidayType);
    holidayToSave.setDescription(description);
    holidayToSave.setCustom(true);
    holidayDaoBean.saveHoliday(holidayToSave);
  }

  public Holiday findHolidayById(Integer id) {
    return holidayDaoBean.getHolidayById(id);
  }

  public void modifyHoliday(Holiday changedHoliday){
    holidayDaoBean.updateHoliday(changedHoliday);
  }

  public void removeHoliday(Integer id){
    holidayDaoBean.deleteHoliday(id);
  }

  public List<Holiday> findAllHolidays() {
    return holidayDaoBean.getAllHolidays();
  }
}
