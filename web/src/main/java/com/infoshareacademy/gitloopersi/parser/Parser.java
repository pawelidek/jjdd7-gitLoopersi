package com.infoshareacademy.gitloopersi.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.gitloopersi.domain.api.Holiday;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Parser {

  private ObjectMapper objectMapper = new ObjectMapper();

  public List<Holiday> parseHolidays(String holidaysPath) throws IOException {

    return objectMapper.readValue(new File(holidaysPath), new TypeReference<List<Holiday>>() {
    });
  }
}
