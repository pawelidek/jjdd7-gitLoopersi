package com.infoshareacademy.gitloopersi.domain.entity.statistic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee_vacation")
public class EmployeeVacation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "user_id", unique = true)
  @NotNull
  private Long userId;

  @Column(name = "quantity")
  @NotNull
  private Integer quantity;

  public EmployeeVacation() {
  }

  public EmployeeVacation(@NotNull Long userId,
      @NotNull Integer quantity) {
    this.userId = userId;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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
        ", userId=" + userId +
        ", quantity=" + quantity +
        '}';
  }
}
