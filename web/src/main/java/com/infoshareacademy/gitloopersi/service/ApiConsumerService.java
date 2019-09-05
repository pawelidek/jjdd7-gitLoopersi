package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.domain.api.HolidayApi;
import com.infoshareacademy.gitloopersi.entity.Holiday;
import com.infoshareacademy.gitloopersi.mapper.HolidayMapper;
import com.infoshareacademy.gitloopersi.parser.Parser;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@Singleton
@Startup
public class ApiConsumerService {

  private WebTarget webTarget;

  @Inject
  private Parser parser;

  @EJB
  private HolidayMapper holidayMapper;

  Logger logger = Logger.getLogger(getClass().getName());

  @PostConstruct
  protected void init() {
    logger.severe("Api consumer started");
    Client client = ClientBuilder.newClient();

    String apikey = "93deab6507d4b12925ee610aafe48024f98ad8e5";

    webTarget = client.target(
        "http://isa-proxy.blueazurit.com/calendar/holidays").queryParam("api_key", apikey)
        .queryParam("country", "PL").queryParam("year", LocalDate.now().getYear());

    try {
      holidayMapper.mapApiToEntity(loadData());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<HolidayApi> loadData() throws IOException {

    Response response = webTarget.request().get();
    String resp = response.readEntity(String.class);

    return parser.parseHolidaysFromApi(resp);
  }

}
