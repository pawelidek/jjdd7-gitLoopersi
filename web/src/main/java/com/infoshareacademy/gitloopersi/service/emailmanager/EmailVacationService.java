package com.infoshareacademy.gitloopersi.service.emailmanager;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class EmailVacationService {

  private static final String RECIPIENT = "gitLoopersi@gmail.com";
  private Logger logger = LoggerFactory.getLogger(getClass().getName());
  @EJB
  private EmailSenderService emailSenderService;

  public void buildEmailMessage(Vacation vacation, Employee employee) {

    String subject = String.format(
        "%s %s Requesting Vacation Days", employee.getFirstName(), employee.getSecondName());

    String emailContent = String.format("Dear Admin,<br><br>"
            + "I would like to kindly ask for vacation from %tF to %tF.<br><br>"
            + "%s will cover for me on that day.<br><br>"
            + "Please count this day against my annual leave balance.<br><br>"
            + "Thank you for understanding.<br><br>"
            + "Best Regards,<br>"
            + "%s %s", vacation.getDateFrom(), vacation.getDateTo(), vacation.getDeputy(),
        employee.getFirstName(), employee.getSecondName());

    try {
      logger.info("Email from {} {} was sent to boss", employee.getFirstName(),
          employee.getSecondName());
      emailSenderService.sendMessage(RECIPIENT, emailContent, subject);
    } catch (MessagingException e) {
      logger.error(e.getMessage());
    }
  }

  public void buildEmailVacationManage(Vacation vacation, Employee employee) {

    String subject = String.format(
        "%s %s %s your Vacation request", employee.getFirstName(), employee.getSecondName(),
        vacation.getStatusType().getType());

    String emailContent = String.format("Dear Employee,<br><br>"
            + "Your request for leave from %tF to %tF was %s by your manager.<br><br>"
            + "Thank you for understanding.<br><br>", vacation.getDateFrom(), vacation.getDateTo(),
        vacation.getStatusType().getType());

    try {
      logger.info("Email from {} {} was sent to employee", employee.getFirstName(),
          employee.getSecondName());
      emailSenderService.sendMessage(RECIPIENT, emailContent, subject);
    } catch (MessagingException e) {
      logger.error(e.getMessage());
    }
  }
}
