package com.infoshareacademy.gitloopersi.service.emailmanager;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VacationRequestMessageBuilder extends EmailMessageAbstractBuilder implements
    EmailMessageBuilder {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  public String buildMessage(Map<String, Object> messageParams)
      throws IOException {

    String hash = EmailParameterCodingService.doEncode(messageParams);
    logger.info(System.getenv("APP_DOMAIN"));
    URLConnection urlConnection = new URL(
        System.getenv("APP_DOMAIN") +
            "email-msg-request?params=" + URLEncoder.encode(hash, "UTF-8")).openConnection();
    return readStream(urlConnection.getInputStream());
  }
}
