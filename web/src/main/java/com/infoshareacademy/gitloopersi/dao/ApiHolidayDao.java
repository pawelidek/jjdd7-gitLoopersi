package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.entity.Holiday;
import com.infoshareacademy.gitloopersi.mapper.HolidayMapper;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ApiHolidayDao {

  @EJB
  HolidayMapper holidayMapper;

  @PersistenceContext
  EntityManager entityManager;

}
