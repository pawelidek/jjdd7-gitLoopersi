package com.infoshareacademy.gitloopersi.service.calendarmanager;

import com.infoshareacademy.gitloopersi.dao.HolidayDaoBean;
import com.infoshareacademy.gitloopersi.dao.VacationDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.types.HolidayType;
import com.infoshareacademy.gitloopersi.web.mapper.CalendarHolidayMapper;
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

  @EJB
  VacationDaoBean vacationDaoBean;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<Calendar> findAllHolidaysDates() {
    logger.info("Load holiday dates");

    List<Holiday> holidayList = holidayDaoBean.getHolidaysList();

    List<Holiday> holidayFilterList = new ArrayList<>();

    for (Holiday holiday : holidayList) {
      if (holiday.getHolidayType() == HolidayType.NATIONAL_HOLIDAY) {
        holidayFilterList.add(holiday);
      }
    }

    return calendarHolidayMapper.mapHolidaysDates(holidayFilterList);

  }

  public List<Calendar> findTeamEmployeesVacation(Long teamId) {
    logger.info("Load vacation dates");

    List<Vacation> employeeVacationList = vacationDaoBean
        .getVacationsListForTeam(teamId);

    return calendarHolidayMapper.mapVacationDates(employeeVacationList);
  }

//    public List<Calendar> findTeamEmployeeVacation(Long teamId, Long employeeId) {
//    logger.info("Load vacation dates");
//
//    List<Vacation> employeeVacationList = vacationDaoBean
//        .getVacationsListForTeamEmployee(teamId, employeeId);
//
//    return calendarHolidayMapper.mapVacationDates(employeeVacationList);
//  }

//  public List<List<Calendar>> findTeamEmployeesVacation(Long teamId) {
//    logger.info("Load vacation dates");
//
//    List<Vacation> employeeVacationList = vacationDaoBean
//        .getVacationsListForTeam(teamId);
//
//    List<Calendar> vacationCalendar = calendarHolidayMapper.mapVacationDates(employeeVacationList);
//
//    List<List<Calendar>> vacationList = new ArrayList<>();
//
//    for (Calendar calendar : vacationCalendar) {
//      vacationList.add(vacationCalendar);
//    }
//
//    return vacationList;
//  }
}

