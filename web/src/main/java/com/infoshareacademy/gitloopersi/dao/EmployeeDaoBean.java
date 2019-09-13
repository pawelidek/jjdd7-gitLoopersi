package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class EmployeeDaoBean {

  @PersistenceContext
  EntityManager entityManager;

  public void addEmployee(Employee employee) {
    entityManager.persist(employee);
  }

  public Employee editEmployee(Employee employee) {
    return entityManager.merge(employee);
  }

  public Employee getEmployeeById(Long id) {
    return entityManager.find(Employee.class, id);
  }

  public void deleteEmployeeById(Long id) {
    Employee employee = getEmployeeById(id);
    if (employee != null) {
      entityManager.remove(employee);
    }
  }

  public List<Employee> getEmployeesList() {
    Query query = entityManager
        .createNamedQuery("Employee.findAll");
    return query.getResultList();
  }
}