package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HolidayDaoBean {

  @PersistenceContext
  EntityManager entityManager;

  public void saveHoliday(Holiday holidayToSave){
    entityManager.persist(holidayToSave);
  }

  public void deleteHoliday(Integer id){
    entityManager.remove(getHolidayById(id));
  }

  public void updateHoliday(Holiday holiday){
    entityManager.merge(holiday);
  }

  public Holiday getHolidayById(Integer id){
    Holiday foundHoliday = entityManager.find(Holiday.class,id);
    return foundHoliday;
  }

  public List<Holiday> getAllHolidays(){
    List<Holiday> foundHolidays = entityManager.createQuery(
        "SELECT h FROM Holiday h")
        .getResultList();
    return foundHolidays;
  }
}
