package com.infoshareacademy.gitloopersi.validator;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

@RequestScoped
public class EmployeeValidator {

  @EJB
  private EmployeeService employeeService;

  public boolean isMailUnique(String email) {
    return employeeService.getEmployeeByEmail(email) == null;
  }

  public boolean isMailUniqueOrCurrentUser(String email, Long id) {
    return (employeeService.getEmployeeByEmail(email) == null
        || employeeService.getEmployeeById(id).getEmail().equals(email));
  }

  public boolean isEmployeeDataValid(HttpServletRequest req, Employee employee) {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

    if (constraintViolations.size() > 0) {

      List<String> errorsList = constraintViolations.stream().map(ConstraintViolation::getMessage)
          .collect(Collectors.toList());

      req.getSession().setAttribute("errorsListFromValidator", errorsList);

      return false;
    } else {
      return true;
    }
  }

  public boolean isStartHireDateEarlierThanOrEqualToStartDate(LocalDate startHireDate,
      LocalDate startDate) {

    Timestamp timestampStartHireDate = Timestamp
        .valueOf(startHireDate.atTime(LocalTime.MIDNIGHT));
    Long startHireDateCount = timestampStartHireDate.getTime();

    Timestamp timestampStartDate = Timestamp.valueOf(startDate.atTime(LocalTime.MIDNIGHT));
    Long startDateCount = timestampStartDate.getTime();

    return startHireDateCount <= startDateCount;
  }

  public boolean areFormDatesValid(LocalDate startHireDate, LocalDate startDate) {

    return (startHireDate != null && startDate != null);
  }
}