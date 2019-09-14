package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class VacationDaoBean {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @PersistenceContext
  EntityManager entityManager;

  public void addVacation(Vacation vacation) {
    logger.info("Object {} vacation persist to DB", vacation.toString());
    entityManager.persist(vacation);
  }

  public Vacation getEmployeeById(Long id) {
    logger.info("Vacation object id={} is to be get from DB", id);
    return entityManager.find(Vacation.class, id);
  }

  public void deleteVacation(Long id) {
    logger.info("Object id = {} vacation delete from DB", id);
    Vacation vacation = getEmployeeById(id);
    if (vacation != null) {
      entityManager.remove(vacation);
    } else {
      logger.warn("An attempt to delete non-existing vacation object id={} has occurred", id);
    }
  }

  public List<Vacation> getVacationsList() {
    logger.info("Vacation objects are to be get from DB");
    Query query = entityManager.createNamedQuery("Vacation.findAll");
    return query.getResultList();
  }
}
