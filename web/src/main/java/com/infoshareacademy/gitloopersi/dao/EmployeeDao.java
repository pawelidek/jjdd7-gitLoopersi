package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EmployeeDao {

  @PersistenceContext
  EntityManager entityManager;

  public Employee findEmployeeById(Long id) {
    return entityManager.find(Employee.class, id);
  }
}
