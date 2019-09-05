package com.infoshareacademy.gitloopersi.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.gitloopersi.domain.api.HolidayApi;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Parser {

  private ObjectMapper objectMapper = new ObjectMapper();

  public List<HolidayApi> parseHolidays(String holidaysPath) throws IOException {

    JsonNode jsonNode = objectMapper.readTree(holidaysPath);

    return objectMapper.readValue(jsonNode.get("response").get("holidays").toString(),
        new TypeReference<List<HolidayApi>>() {});
  }

  public List<HolidayApi> parseHolidaysFromApi(String holidays) throws IOException {

    JsonNode jsonNode = objectMapper.readTree(holidays);

    return objectMapper.readValue(jsonNode.get("response").get("holidays").toString(),
        new TypeReference<List<HolidayApi>>() {});
  }
}