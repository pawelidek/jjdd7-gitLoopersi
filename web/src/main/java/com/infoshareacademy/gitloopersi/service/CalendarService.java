package com.infoshareacademy.gitloopersi.service;


import com.infoshareacademy.gitloopersi.dao.CalendarDaoBean;
import com.infoshareacademy.gitloopersi.domain.Calendar;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.mapper.CalendarHolidayMapper;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class CalendarService {

  @EJB
  CalendarDaoBean calendarDaoBean;

  @EJB
  CalendarHolidayMapper calendarHolidayMapper;



  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public void findAllHolidaysDates(List<Holiday> holidayList) {
    logger.info("...");

    List<Calendar> calendars = calendarHolidayMapper.mapHolidays(holidayList);
    calendars.forEach(calendar -> calendarDaoBean.saveHoliday(calendar));
  }


  public List<Calendar> findAllHolidays() {
    logger.info("...");
    return calendarDaoBean.getAllHolidaysDates();
  }

}
