package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.HolidayDao;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class HolidayService {

  @Inject
  HolidayDao holidayDao;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public Holiday findHolidayById(Integer id){
    logger.info("Object holiday id={} go to DAO to find it in DB",id);
    return holidayDao.getHolidayById(id);
  }

}
