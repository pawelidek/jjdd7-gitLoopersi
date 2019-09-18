package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class EmailBuilder {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private EmailNotificator emailNotificator;

  public void buildEmailMessage(Vacation vacation, Employee employee) {

    String recipient = "gitLoopersi@gmail.com";

    String subject =
        employee.getFirstName() + " " + employee.getSecondName() + " Requesting Vacation Days";

    String emailContent = "Dear Admin,<br><br>"
        + "I would like to kindly ask for vacation from " + vacation.getDateFrom() + " to "
        + vacation.getDateTo() + ".<br><br>"
        + "[colleague name] will cover for me on that day.<br><br>"
        + "Please count this day against my annual leave balance.<br><br>"
        + "Thank you for understanding.<br><br>"
        + "Best Regards,<br>"
        + employee.getFirstName() + " " + employee.getSecondName();

    try {
      logger.info("Email from {} {} was sent to boss", employee.getFirstName(),
          employee.getSecondName());
      emailNotificator.sendMessage(recipient, emailContent, subject);
    } catch (MessagingException e) {
      logger.error(e.getMessage());
    }
  }
}
