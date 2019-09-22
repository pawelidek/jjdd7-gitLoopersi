package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.web.view.VacationView;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class VacationViewMapper {

  @EJB
  private EmployeeViewMapper employeeViewMapper;

  @Transactional
  public VacationView mapEntityToView(Vacation vacation) {

    VacationView vacationView = new VacationView();

    vacationView.setId(vacation.getId());
    vacationView.setEmployee(employeeViewMapper.mapEntityToView(vacation.getEmployee()));
    vacationView.setDateFrom(vacation.getDateFrom());
    vacationView.setDateTo(vacation.getDateTo());
    vacationView.setDaysCount(vacation.getDaysCount());
    vacationView.setDeputy(vacation.getDeputy());
    vacationView.setVacationType(vacation.getVacationType());
    vacationView.setStatusType(vacation.getStatusType());

    return vacationView;
  }
}