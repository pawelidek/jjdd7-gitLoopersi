package com.infoshareacademy.gitloopersi.domain.entity.statistic;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries({
    @NamedQuery(
        name = "EmployeeVacation.findAll",
        query = "SELECT ev FROM EmployeeVacation ev ORDER BY ev.quantity"
    )
}
)
@Entity
@Table(name = "employee_vacation")
public class EmployeeVacation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @OneToOne(mappedBy = "employeeVacation")
  private Employee employee;

  @Column(name = "quantity")
  @NotNull
  private Integer quantity=0;

  public EmployeeVacation() {
  }

  public EmployeeVacation(
      @NotNull Employee employee, @NotNull Integer quantity) {
    this.employee = employee;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "EmployeeVacation{" +
        "id=" + id +
        ", employee=" + employee +
        ", quantity=" + quantity +
        '}';
  }
}
