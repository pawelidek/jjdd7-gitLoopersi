package com.infoshareacademy.gitloopersi.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries({
    @NamedQuery(
        name = "MonthVacation.findAll",
        query = "SELECT mv FROM MonthVacationStat mv"
    ),
    @NamedQuery(
        name = "MonthVacation.incrementQuantity",
        query = "UPDATE MonthVacationStat mv SET mv.quantity=mv.quantity+1 WHERE mv.month=:month"
    )
}
)
@Entity
@Table(name = "month_vacation", indexes = {
    @Index(columnList = "month", name = "month_hidx")
})
public class MonthVacationStat {

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

  public MonthVacationStat() {
  }

  public MonthVacationStat(@NotNull String month,
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
    return "MonthVacationStat{" +
        "id=" + id +
        ", month='" + month + '\'' +
        ", quantity=" + quantity +
        '}';
  }
}
