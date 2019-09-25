package com.infoshareacademy.gitloopersi.service.admininitializer;


import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.googleoauth.GoogleLogin;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.service.teammanager.TeamService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Transactional
@Singleton
@Startup
public class AdminInitializer {

  @EJB
  EmployeeService employeeService;

  @EJB
  TeamService teamService;

  private Logger logger = LoggerFactory.getLogger(GoogleLogin.class.getName());
  private final String ADMIN_FILE_NAME = "admin.properties";

  @PostConstruct
  protected void init() {

    Team team = new Team();
    team.setName("Admin");

    teamService.addTeam(team);

    Employee employee = new Employee();
    employee.setFirstName(getProperty("employee.name"));
    employee.setEmail(getProperty("employee.email"));
    employee.setSecondName(getProperty("employee.surname"));
    employee.setStartDate(LocalDate.parse("2019-08-24"));
    employee.setStartHireDate(LocalDate.parse("2019-08-24"));
    employee.setTeam(team);
    employeeService.addEmployee(employee, 1L);

  }

  private String getProperty(String property) {
    Properties properties = new Properties();
    try {
      properties.load(Objects.requireNonNull(Thread.currentThread()
          .getContextClassLoader().getResource(ADMIN_FILE_NAME))
          .openStream());
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
    return properties.getProperty(property);
  }


}
