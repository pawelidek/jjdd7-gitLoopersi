package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.entity.Holiday;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HolidayDaoBean {

  @PersistenceContext
  EntityManager entityManager;

  public void addHoliday(Holiday holidayToSave){
    entityManager.persist(holidayToSave);

  }

  public void updateHoliday(Holiday holiday){
    entityManager.merge(holiday);
  }

  public Holiday getHolidayById(Integer id){
    Holiday foundHoliday = (Holiday) entityManager.createQuery(
        "SELECT h FROM Holiday h WHERE h.id=:id")
        .setParameter("id", id)
        .getSingleResult();
    return foundHoliday;
  }

  public List<Holiday> getAllHolidays(){
    List<Holiday> foundHolidays = entityManager.createQuery(
        "SELECT h FROM Holiday h")
        .getResultList();
    return foundHolidays;
  }
}
