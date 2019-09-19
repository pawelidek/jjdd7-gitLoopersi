package com.infoshareacademy.gitloopersi.service.calendarmanager;

import com.infoshareacademy.gitloopersi.dao.HolidayDaoBean;
import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.web.mapper.CalendarHolidayMapper;
import com.infoshareacademy.gitloopersi.types.HolidayType;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class CalendarService {


  @EJB
  CalendarHolidayMapper calendarHolidayMapper;

  @EJB
  HolidayDaoBean holidayDaoBean;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<Calendar> findAllHolidaysDates() {
    logger.info("Load dates of holidays");

    List<Holiday> holidayList = holidayDaoBean.getHolidaysList();

    List<Holiday> holidayFilterList = new ArrayList<>();

    for (Holiday holiday : holidayList) {
      if (holiday.getHolidayType() == HolidayType.NATIONAL_HOLIDAY) {
        holidayFilterList.add(holiday);
      }
    }

    List<Calendar> calendars = calendarHolidayMapper.mapHolidaysDates(holidayFilterList);
    return calendars;

  }
}

