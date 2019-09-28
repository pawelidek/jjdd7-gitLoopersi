package com.infoshareacademy.gitloopersi.service.emailmanager;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.transaction.Transactional;

@Stateless
public class EmailVacationService {

  @EJB
  private EmailSenderService emailSenderService;

  public void prepareEmailAndSendMessage(Map<String, Object> messageParams,
      EmailMessageBuilder emailMessageBuilder, String subject, List<String> recipients)
      throws IOException, MessagingException {

    String msg = emailMessageBuilder.buildMessage(messageParams);

    emailSenderService.sendMessage(recipients, msg, subject);
  }
}
