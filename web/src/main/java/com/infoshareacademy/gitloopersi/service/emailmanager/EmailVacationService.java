package com.infoshareacademy.gitloopersi.service.emailmanager;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.web.view.VacationView;
import java.util.List;
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

  public void buildEmailVacationScheduler(List<VacationView> vacationViews) {

    String subject = "A reminder of unacceptable vacations";

    String emailContent = String.valueOf(buildTableOfVacations(vacationViews));

    try {
      logger.info("Email was sent to admin");
      emailSenderService.sendMessage(RECIPIENT, emailContent, subject);
    } catch (MessagingException e) {
      logger.error(e.getMessage());
    }
  }

  private StringBuilder buildTableOfVacations(List<VacationView> vacationViews) {

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("Dear Admin,<br><br>" +
        "A list of unacceptable holidays:<br><br>");

    stringBuilder.append("<table border='1'>" +
        "<tr align='center'><th>ID</th>" +
        "<th>Date from</th>" +
        "<th>Date to</th>" +
        "<th>Days count</th>" +
        "<th>Deputy</th>" +
        "<th>Vacation type</th>" +
        "<th>Status type</th></tr>");

    for (VacationView vacationView : vacationViews) {
      stringBuilder.append(
          String.format("<tr align='center'><td>%o</td>" +
                  "<td>%tF</td>" +
                  "<td>%tF</td>" +
                  "<td>%o</td>" +
                  "<td>%s</td>" +
                  "<td>%s</td>" +
                  "<td>%s</td></tr>", vacationView.getId(), vacationView.getDateFrom(),
              vacationView.getDateTo(), vacationView.getDaysCount(), vacationView.getDeputy(),
              vacationView.getVacationType().getType(), vacationView.getStatusType())
      );
    }
    stringBuilder.append("</table>");

    return stringBuilder;
  }
}
