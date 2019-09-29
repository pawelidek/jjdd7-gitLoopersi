package com.infoshareacademy.gitloopersi.web.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.infoshareacademy.gitloopersi.service.emailmanager.EmailVacationService;
import com.infoshareacademy.gitloopersi.service.emailmanager.VacationReminderMessageBuilder;
import com.infoshareacademy.gitloopersi.service.vacationmanager.VacationDefiningService;
import com.infoshareacademy.gitloopersi.types.StatusType;
import com.infoshareacademy.gitloopersi.web.view.VacationViewString;
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

    List<VacationViewString> vacationViews = vacationDefiningService
        .getVacationsWithEmployeesStringList();

    List<VacationViewString> vacationViewList = new ArrayList<>();

    if (!vacationViews.isEmpty()) {

      for (VacationViewString vacationViewString : vacationViews) {

        Timestamp timestampToday = Timestamp.valueOf(todayDate.atTime(LocalTime.now()));
        Long todayCount = timestampToday.getTime();

        Long createCount = vacationViewString.getCreateDate().getTime();

        long resultTimeCount = todayCount - createCount;

        if (vacationViewString.getStatusType().equals(StatusType.REQUESTED)
            && resultTimeCount > THRESHOLD) {

          vacationViewList.add(vacationViewString);
        }
      }

      if (!vacationViewList.isEmpty()) {
        try {

          String subject = "Reminder of notified vacations";

          List<String> recipients = new ArrayList<>();
          recipients.add("gitLoopersi@gmail.com");

          logger.info(vacationViewList.toString());

          ObjectMapper objectMapper = new ObjectMapper();
          objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
          String vacationToJson = objectMapper.writeValueAsString(vacationViewList);

          Map<String, Object> messageParams = new HashMap<>();
          messageParams.put("vacations", vacationToJson);

          emailVacationService
              .prepareEmailAndSendMessage(messageParams, new VacationReminderMessageBuilder(),
                  subject, recipients);

        } catch (IOException | MessagingException e) {
          logger.error(e.getMessage());
        }
      }
    }
  }
}
