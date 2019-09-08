package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.entity.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EmployeeDaoBean implements EmployeeDao {

  @PersistenceContext
  EntityManager entityManager;

  @Override
  public void addEmployee(Employee employee) {
    entityManager.persist(employee);
  }

  @Override
  public Employee editEmployee(Employee employee) {
    return entityManager.merge(employee);
  }

  @Override
  public Employee getEmployeeById(Long id) {
    return entityManager.find(Employee.class, id);
  }

  @Override
  public void deleteEmployeeById(Long id) {
    Employee employee = getEmployeeById(id);
    if (employee != null) {
      entityManager.remove(employee);
    }
  }

  @Override
  public List<Employee> getEmployeesList() {
    Query query = entityManager.createQuery("select e from Employee e");
    return query.getResultList();
  }
}