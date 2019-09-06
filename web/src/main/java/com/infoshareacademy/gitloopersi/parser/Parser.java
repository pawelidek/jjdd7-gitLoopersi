package com.infoshareacademy.gitloopersi.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.gitloopersi.domain.api.HolidayApi;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class Parser {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());
  private ObjectMapper objectMapper = new ObjectMapper();

  public List<HolidayApi> parseHolidays(File holidaysFile) throws IOException {

    logger.info("Parse data from file");
    JsonNode jsonNode = objectMapper.readTree(holidaysFile);

    return objectMapper.readValue(jsonNode.get("response").get("holidays").toString(),
        new TypeReference<List<HolidayApi>>() {
        });
  }

  public List<HolidayApi> parseHolidaysFromApi(String holidays) throws IOException {

    logger.info("Parse data from API");
    JsonNode jsonNode = objectMapper.readTree(holidays);

    return objectMapper.readValue(jsonNode.get("response").get("holidays").toString(),
        new TypeReference<List<HolidayApi>>() {
        });
  }
}