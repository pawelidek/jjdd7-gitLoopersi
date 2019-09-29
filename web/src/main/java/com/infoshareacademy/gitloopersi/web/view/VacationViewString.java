package com.infoshareacademy.gitloopersi.web.view;

import com.infoshareacademy.gitloopersi.types.StatusType;
import com.infoshareacademy.gitloopersi.types.VacationType;
import java.util.Date;

public class VacationViewString {

  private Long id;
  private EmployeeViewString employee;
  private String dateFrom;
  private String dateTo;
  private Integer daysCount;
  private String deputy;
  private VacationType vacationType;
  private StatusType statusType;
  private Date createDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EmployeeViewString getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeViewString employee) {
    this.employee = employee;
  }

  public String getDateFrom() {
    return dateFrom;
  }

  public void setDateFrom(String dateFrom) {
    this.dateFrom = dateFrom;
  }

  public String getDateTo() {
    return dateTo;
  }

  public void setDateTo(String dateTo) {
    this.dateTo = dateTo;
  }

  public Integer getDaysCount() {
    return daysCount;
  }

  public void setDaysCount(Integer daysCount) {
    this.daysCount = daysCount;
  }

  public String getDeputy() {
    return deputy;
  }

  public void setDeputy(String deputy) {
    this.deputy = deputy;
  }

  public VacationType getVacationType() {
    return vacationType;
  }

  public void setVacationType(VacationType vacationType) {
    this.vacationType = vacationType;
  }

  public StatusType getStatusType() {
    return statusType;
  }

  public void setStatusType(StatusType statusType) {
    this.statusType = statusType;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
}
