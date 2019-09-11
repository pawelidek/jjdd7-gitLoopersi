package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.HolidayDao;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class HolidayService {

  @Inject
  HolidayDao holidayDao;

  public Holiday findHolidayById(Integer id){
    return holidayDao.getHolidayById(id);
  }

}
