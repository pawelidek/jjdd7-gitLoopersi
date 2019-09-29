package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.web.view.VacationViewString;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class VacationViewStringMapper {

  @EJB
  private EmployeeViewStringMapper employeeViewStringMapper;

  @Transactional
  public VacationViewString mapEntityToView(Vacation vacation) {

    VacationViewString vacationViewString = new VacationViewString();

    vacationViewString.setId(vacation.getId());
    vacationViewString
        .setEmployee(employeeViewStringMapper.mapEntityToView(vacation.getEmployee()));
    vacationViewString.setDateFrom(vacation.getDateFrom().toString());
    vacationViewString.setDateTo(vacation.getDateTo().toString());
    vacationViewString.setDaysCount(vacation.getDaysCount());
    vacationViewString.setDeputy(vacation.getDeputy());
    vacationViewString.setVacationType(vacation.getVacationType());
    vacationViewString.setStatusType(vacation.getStatusType());
    vacationViewString.setCreateDate(vacation.getCreatingDate());

    return vacationViewString;
  }
}
