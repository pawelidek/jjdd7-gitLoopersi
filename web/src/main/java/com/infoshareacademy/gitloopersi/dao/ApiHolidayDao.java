package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.entity.Holiday;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ApiHolidayDao {

  @PersistenceContext
  EntityManager entityManager;

  public void addHoliday(Holiday holiday) {
    entityManager.persist(holiday);
  }
}
