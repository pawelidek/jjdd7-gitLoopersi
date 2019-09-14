package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.EmployeeDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class EmployeeService {

  @EJB
  private EmployeeDaoBean employeeDaoBean;

  @EJB
  private TeamService teamService;

  @Transactional
  public void addEmployee(Employee employee, Long teamId) {
    Team team = teamService.getTeamById(teamId);
    employee.setTeam(team);
    employeeDaoBean.addEmployee(employee);
  }

  @Transactional
  public Employee editEmployee(Employee employee, Long teamId) {
    Team team = teamService.getTeamById(teamId);
    employee.setTeam(team);
    return employeeDaoBean.editEmployee(employee);
  }

  @Transactional
  public Employee getEmployeeById(Long id) {
    return employeeDaoBean.getEmployeeById(id);
  }

  public void deleteEmployeeById(Long id) {
    employeeDaoBean.deleteEmployeeById(id);
  }

  public List<Employee> getEmployeesList() {
    return employeeDaoBean.getEmployeesList();
  }

}
