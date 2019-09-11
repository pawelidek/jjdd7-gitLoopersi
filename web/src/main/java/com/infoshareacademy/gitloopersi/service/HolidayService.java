package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.ApiHolidayDao;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class HolidayService {

  @Inject
  ApiHolidayDao apiHolidayDao;

  public Holiday findHolidayById(Integer id){
    return apiHolidayDao.getHolidayById(id);
  }

}
