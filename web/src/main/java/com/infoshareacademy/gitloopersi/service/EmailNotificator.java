package com.infoshareacademy.gitloopersi.service;

import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class EmailNotificator {

  public void sendMessage(String recipient, String emailContent, String subject)
      throws MessagingException {

    Properties mailProperties = System.getProperties();
    mailProperties.put("mail.smtp.port", "587");
    mailProperties.put("mail.smtp.auth", "true");
    mailProperties.put("mail.smtp.starttls.enable", "true");

    MimeMessage generateMailMessage;
    Session getMailSession = Session.getDefaultInstance(mailProperties, null);
    generateMailMessage = new MimeMessage(getMailSession);
    generateMailMessage.addRecipient(RecipientType.TO, new InternetAddress(recipient));
    generateMailMessage.setSubject(subject);
    generateMailMessage.setContent(emailContent, "text/html");

    Transport transport = getMailSession.getTransport("smtp");

    String username = "gitLoopersi";
    String password = "l00p3rs1";

    transport.connect("smtp.gmail.com", username, password);
    transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
    transport.close();
  }
}
