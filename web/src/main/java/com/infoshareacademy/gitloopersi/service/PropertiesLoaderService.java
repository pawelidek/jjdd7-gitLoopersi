package com.infoshareacademy.gitloopersi.service;

import java.io.IOException;
import java.util.Properties;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class PropertiesLoaderService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public Properties loadMailProperties() throws IOException {

    Properties mailProperties = new Properties();
    String pathname = "sendmail.properties";
    mailProperties.load(Thread.currentThread()
        .getContextClassLoader().getResource(pathname)
        .openStream());
    logger.info("Load {}", pathname);
    return mailProperties;
  }

  public Properties loadCredentialsProperties() throws IOException {

    Properties credentialsProperties = new Properties();
    String pathname = "credentials.properties";
    credentialsProperties.load(Thread.currentThread()
        .getContextClassLoader().getResource(pathname)
        .openStream());
    logger.info("Load {}", pathname);
    return credentialsProperties;
  }
}
