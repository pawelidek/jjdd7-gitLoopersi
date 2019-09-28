package com.infoshareacademy.gitloopersi.service.emailmanager;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class VacationRequestMessageBuilder extends EmailMessageAbstractBuilder implements
    EmailMessageBuilder {

  @Override
  public String buildMessage(Map<String, Object> messageParams)
      throws IOException {

    String hash = EmailParameterCodingService.doEncode(messageParams);
    URLConnection urlConnection = new URL(
        System.getProperty("APP_DOMAIN", "http://localhost:8080/") +
            "email-msg-request?params=" + URLEncoder.encode(hash, "UTF-8")).openConnection();
    return readStream(urlConnection.getInputStream());
  }
}
