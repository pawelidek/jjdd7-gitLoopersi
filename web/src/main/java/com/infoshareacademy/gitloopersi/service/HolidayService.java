package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.HolidayDaoBean;
import com.infoshareacademy.gitloopersi.entity.Holiday;
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
    Holiday holidayToSave = new Holiday(name, date, holidayType, description);
    holidayDaoBean.saveHoliday(new Holiday());
  }

  public Holiday findHolidayById(Integer id) {
    return holidayDaoBean.getHolidayById(id);
  }

  public void modifyHoliday(Holiday changedHoliday){
    holidayDaoBean.updateHoliday(changedHoliday);
  }

  public List<Holiday> findAllHolidays() {
    return holidayDaoBean.getAllHolidays();
  }
}
