package com.infoshareacademy.gitloopersi.domain.entity.statistic;

import com.infoshareacademy.gitloopersi.types.StatusType;
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
        name = "StatusVacation.findAll",
        query = "SELECT sv FROM StatusVacation sv"
    )
}
)
@Entity
@Table(name = "status_vacation")
public class StatusVacation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "status_type")
  @NotNull
  private StatusType statusType;

  @Column(name = "quantity")
  @NotNull
  private Integer quantity=0;

  public StatusVacation() {
  }

  public StatusVacation(
      @NotNull StatusType statusType,
      @NotNull Integer quantity) {
    this.statusType = statusType;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public StatusType getStatusType() {
    return statusType;
  }

  public void setStatusType(StatusType statusType) {
    this.statusType = statusType;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "StatusVacation{" +
        "id=" + id +
        ", statusType=" + statusType +
        ", quantity=" + quantity +
        '}';
  }
}
