package com.infoshareacademy.gitloopersi.service;


import com.infoshareacademy.gitloopersi.dao.CalendarDaoBean;
import com.infoshareacademy.gitloopersi.domain.Calendar;
import com.infoshareacademy.gitloopersi.domain.Holiday;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class CalendarService {

  @EJB
  CalendarDaoBean calendarDaoBean;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<Calendar> findAllHolidaysDates() {
    logger.info("Objects holidays dates go to DAO to be found in DB");
    return calendarDaoBean.getAllHolidaysDates();
  }


}
