package com.infoshareacademy.gitloopersi.validator;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
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

  @EJB
  private UserMessagesService userMessagesService;

  public boolean isMailUnique(String email) {
    return employeeService.getEmployeeByEmail(email) == null;
  }

  public boolean isEmployeeDataValid(HttpServletRequest req, Employee employee) {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

    if (constraintViolations.size() > 0) {

      List<String> errorsList = constraintViolations.stream().map(ConstraintViolation::getMessage)
          .collect(Collectors.toList());

      for (int i = 0; i < constraintViolations.size(); i++) {
        userMessagesService
            .addErrorMessage(req.getSession(), errorsList.get(i));
      }

      return false;
    } else {
      return true;
    }
  }

  public boolean isStartHireDateEarlierThanOrEqualToStartDate(String startHireDate, String startDate) {

    LocalDate startHireDateParsed = LocalDate.parse(startHireDate);
    LocalDate startDateParsed = LocalDate.parse(startDate);

    Timestamp timestampStartHireDate = Timestamp.valueOf(startHireDateParsed.atTime(LocalTime.MIDNIGHT));
    Long startHireDateCount = timestampStartHireDate.getTime();

    Timestamp timestampStartDate = Timestamp.valueOf(startDateParsed.atTime(LocalTime.MIDNIGHT));
    Long startDateCount = timestampStartDate.getTime();

    return startHireDateCount <= startDateCount;
  }

  public boolean areDatesParseable(String startDate, String startHireDate) {
    return !((startDate.equals("") || startDate.equals(null))
        || (startHireDate.equals("") || startHireDate.equals(null)));
  }
}