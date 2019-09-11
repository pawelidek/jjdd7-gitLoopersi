package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class VacationDefiningDao {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @PersistenceContext
  EntityManager entityManager;

  public void addVacation(Vacation vacation) {
    logger.info("Object vacation persist to DB");
    entityManager.persist(vacation);
  }

  public void deleteVacation(Vacation vacation) {
    logger.info("Object vacation delete from DB");
    entityManager.remove(vacation);
  }
}
