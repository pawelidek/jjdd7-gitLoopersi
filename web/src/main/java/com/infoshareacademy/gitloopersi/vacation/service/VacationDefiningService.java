package com.infoshareacademy.gitloopersi.vacation.service;

import com.infoshareacademy.gitloopersi.dao.VacationDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class VacationDefiningService {

  @EJB
  private VacationDaoBean vacationDefiningDao;

  public void addVacation(Vacation vacation) {
    vacationDefiningDao.addVacation(vacation);
  }

  public void deleteVacation(Vacation vacation) {
    vacationDefiningDao.deleteVacation(vacation);
  }

  public List<Vacation> getVacationsList() {
    return vacationDefiningDao.getVacationsList();
  }
}
