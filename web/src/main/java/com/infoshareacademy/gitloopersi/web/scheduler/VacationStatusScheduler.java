package com.infoshareacademy.gitloopersi.web.scheduler;

import com.infoshareacademy.gitloopersi.service.emailmanager.EmailVacationService;
import com.infoshareacademy.gitloopersi.service.emailmanager.VacationReminderMessageBuilder;
import com.infoshareacademy.gitloopersi.service.vacationmanager.VacationDefiningService;
import com.infoshareacademy.gitloopersi.types.StatusType;
import com.infoshareacademy.gitloopersi.web.view.VacationView;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class VacationStatusScheduler {

  private static final long THRESHOLD = 900000L;

  @EJB
  private VacationDefiningService vacationDefiningService;

  @EJB
  private EmailVacationService emailVacationService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Schedule(hour = "*", minute = "*/1", info = "Every 1 minute timer")
  public void checkStatusOfVacation() {

    LocalDate todayDate = LocalDate.now();

    List<VacationView> vacationViews = vacationDefiningService.getVacationsWithEmployeesList();

    List<VacationView> vacationViewList = new ArrayList<>();

    if (!vacationViews.isEmpty()) {

      for (VacationView vacationView : vacationViews) {

        Timestamp timestampToday = Timestamp.valueOf(todayDate.atTime(LocalTime.now()));
        Long todayCount = timestampToday.getTime();

        Long createCount = vacationView.getCreateDate().getTime();

        long resultTimeCount = todayCount - createCount;

        if (vacationView.getStatusType().equals(StatusType.REQUESTED)
            && resultTimeCount > THRESHOLD) {

          vacationViewList.add(vacationView);
        }
      }

      if (!vacationViewList.isEmpty()) {
        try {
          try {

            String subject = "Reminder of notified vacations";

            List<String> recipients = new ArrayList<>();
            recipients.add("gitLoopersi@gmail.com");

            Map<String, Object> messageParams = new HashMap<>();
            messageParams.put("vacations", vacationViewList);

            emailVacationService
                .prepareEmailAndSendMessage(messageParams, new VacationReminderMessageBuilder(),
                    subject, recipients);
          } catch (IOException e) {
            logger.error(e.getMessage());
          }
        } catch (MessagingException e) {
          logger.error(e.getMessage());
        }
      }
    }
  }
}
