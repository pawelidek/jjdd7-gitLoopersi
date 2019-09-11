package com.infoshareacademy.gitloopersi.vacation.service;

import com.infoshareacademy.gitloopersi.dao.VacationDefiningDao;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class VacationDefiningService {

  @EJB
  private VacationDefiningDao vacationDefiningDao;

  public void addVacation(Vacation vacation) {
    vacationDefiningDao.addVacation(vacation);
  }

  public void deleteVacation(Vacation vacation) {
    vacationDefiningDao.deleteVacation(vacation);
  }
}
