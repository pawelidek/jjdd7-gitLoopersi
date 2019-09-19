package com.infoshareacademy.gitloopersi.service.apimanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.gitloopersi.domain.jsonapi.HolidayResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class ParserService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());
  private ObjectMapper objectMapper = new ObjectMapper();

  public List<HolidayResponse> parseHolidays(File holidaysFile) throws IOException {

    logger.info("Parse data from file");
    JsonNode jsonNode = objectMapper.readTree(holidaysFile);

    return objectMapper.readValue(jsonNode.get("response").get("holidays").toString(),
        new TypeReference<List<HolidayResponse>>() {
        });
  }

  public List<HolidayResponse> parseHolidaysFromApi(String holidays) throws IOException {

    logger.info("Parse data from API");
    JsonNode jsonNode = objectMapper.readTree(holidays);

    return objectMapper.readValue(jsonNode.get("response").get("holidays").toString(),
        new TypeReference<List<HolidayResponse>>() {
        });
  }
}