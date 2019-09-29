package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.service.calendarmanager.CalendarService;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class CalendarVacationMapper {

  @EJB
  CalendarService calendarService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<Calendar> mapVacationDates(List<Vacation> teamVacationList) {

    logger.info("Vacation dates map on to individual parts of the date");
    List<Calendar> vacationList = new ArrayList<>();

    teamVacationList.forEach(dates -> {

      Integer counter = dates.getDaysCount();

      for (int i = 0; i < counter; i++) {
        Calendar date = new Calendar();
        date.setYear(dates.getDateFrom().getYear());
        date.setMonth(dates.getDateFrom().getMonthValue());
        date.setDay(dates.getDateFrom().getDayOfMonth());

        StringBuilder sb = new StringBuilder();
        sb.append(date.getYear());
        sb.append("-");
        if (date.getMonth() < 10) {
          sb.append("0");
        }
        sb.append(date.getMonth());
        sb.append("-");
        if (date.getDay() < 10) {
          sb.append("0");
        }
        sb.append(date.getDay());

        LocalDate parsedDate = LocalDate.parse(sb);
        LocalDate changedDate = parsedDate.plusDays(i);

        date.setYear(changedDate.getYear());
        date.setMonth(changedDate.getMonthValue());
        date.setDay(changedDate.getDayOfMonth());

        if (changedDate.getDayOfWeek() == DayOfWeek.SATURDAY
            || changedDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
          counter++;
        } else if (calendarService.checkHolidayByDate(changedDate)) {
          counter++;
        } else {
          vacationList.add(date);
        }
      }
    });
    return vacationList;
  }
}