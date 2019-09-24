package com.infoshareacademy.gitloopersi.googleoauth;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleLogin {


  private static Logger logger = LoggerFactory.getLogger(GoogleLogin.class.getName());
  private static final List<String> SCOPES = List.of("openid", "email", "profile");
  private static final String OAUTH_FILE_NAME = "oauth.properties";

  static String buildRedirectUri(HttpServletRequest req) {
    GenericUrl url = new GenericUrl(req.getRequestURL().toString());
    url.setRawPath(getProperty("redirect.url"));
    return url.build();
  }

  static GoogleAuthorizationCodeFlow buildFlow() {
    return new GoogleAuthorizationCodeFlow.Builder(
        new NetHttpTransport(),
        JacksonFactory.getDefaultInstance(),getProperty("client.id"),
        getProperty("secret"), SCOPES)
        .setAccessType("online")
        .build();
  }

  private static String getProperty(String property) {
    Properties properties = new Properties();
    try {
      properties.load(Objects.requireNonNull(Thread.currentThread()
          .getContextClassLoader().getResource(OAUTH_FILE_NAME))
          .openStream());
    } catch (IOException e) {
      logger.warn(e.getMessage());
    }
    return properties.getProperty(property);
  }
}
