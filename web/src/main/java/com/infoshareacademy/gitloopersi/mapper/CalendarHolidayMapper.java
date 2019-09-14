package com.infoshareacademy.gitloopersi.mapper;


import com.infoshareacademy.gitloopersi.domain.Calendar;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class CalendarHolidayMapper {


  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<Calendar> mapHolidays(List<Holiday> holidayApiList) {

    logger.info("Map");
    List<Calendar> holidays = new ArrayList<>();
    holidayApiList.forEach(holidayApi -> {
      Calendar date = new Calendar();
      date.setYear(holidayApi.getDate().getYear());
      date.setMonth(holidayApi.getDate().getMonthValue());
      date.setDay(holidayApi.getDate().getDayOfMonth());
      holidays.add(date);
    });
    return holidays;
  }

}
