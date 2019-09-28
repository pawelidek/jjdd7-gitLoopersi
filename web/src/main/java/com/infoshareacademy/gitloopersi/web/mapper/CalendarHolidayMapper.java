package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class CalendarHolidayMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<Calendar> mapHolidaysDates(List<Holiday> holidayDatesList) {

    logger.info("Holidays dates map on to individual parts of the date");
    List<Calendar> holidaysList = new ArrayList<>();
    holidayDatesList.forEach(dates -> {
      Calendar date = new Calendar();
      date.setYear(dates.getDate().getYear());
      date.setMonth(dates.getDate().getMonthValue());
      date.setDay(dates.getDate().getDayOfMonth());
      holidaysList.add(date);
    });
    return holidaysList;
  }

  public List<Calendar> mapVacationDates(List<Vacation> teamVacationList) {

    logger.info("Vacation dates map on to individual parts of the date");
    List<Calendar> vacationList = new ArrayList<>();

    teamVacationList.forEach(dates -> {

      for (int i = 0; i < dates.getDaysCount(); i++) {
        Calendar date = new Calendar();
        date.setYear(dates.getDateFrom().getYear());
        date.setMonth(dates.getDateFrom().getMonthValue());
        date.setDay(dates.getDateFrom().plusDays(i).getDayOfMonth());
        vacationList.add(date);
      }
    });
    return vacationList;
  }
}