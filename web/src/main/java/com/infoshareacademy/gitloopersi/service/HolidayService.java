package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.ApiHolidayDao;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class HolidayService {

  @EJB
  private ApiHolidayDao apiHolidayDao;

  public List<Holiday> getHolidayList() {
    return apiHolidayDao.getHolidayList();
  }
}
