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

  public List<Calendar> mapHolidaysDates(List<Holiday> holidayDateslist) {

    logger.info("Holidays dates map on to individual parts of the date");
    List<Calendar> holidaysList = new ArrayList<>();
    holidayDateslist.forEach(dates -> {
      Calendar date = new Calendar();
      date.setYear(dates.getDate().getYear());
      date.setMonth(dates.getDate().getMonthValue());
      date.setDay(dates.getDate().getDayOfMonth());
      holidaysList.add(date);
    });
    return holidaysList;
  }

}
