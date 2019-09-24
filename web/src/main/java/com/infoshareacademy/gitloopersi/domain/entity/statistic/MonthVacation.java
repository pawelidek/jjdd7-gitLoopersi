package com.infoshareacademy.gitloopersi.domain.entity.statistic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries({
    @NamedQuery(
        name = "MonthVacation.findAll",
        query = "SELECT mv FROM MonthVacation mv"
    )
}
)
@Entity
@Table(name = "month_vacation")
public class MonthVacation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "month", unique = true)
  @NotNull
  private String month;

  @Column(name = "quantity")
  @NotNull
  private Integer quantity = 0;

  public MonthVacation() {
  }

  public MonthVacation(@NotNull String month,
      @NotNull Integer quantity) {
    this.month = month;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "MonthVacation{" +
        "id=" + id +
        ", month='" + month + '\'' +
        ", quantity=" + quantity +
        '}';
  }
}
