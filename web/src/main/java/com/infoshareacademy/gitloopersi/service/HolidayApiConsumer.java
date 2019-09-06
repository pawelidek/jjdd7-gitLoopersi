package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.ApiHolidayDao;
import com.infoshareacademy.gitloopersi.domain.api.HolidayApi;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.mapper.HolidayMapper;
import com.infoshareacademy.gitloopersi.parser.Parser;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class HolidayApiConsumer {

  private WebTarget webTarget;
  private static final String APIKEY = "93deab6507d4b12925ee610aafe48024f98ad8e5";
  private static final String URI = "http://isa-proxy.blueazurit.com/calendar/holidays";
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private Parser parser;

  @EJB
  private HolidayMapper holidayMapper;

  @EJB
  private ApiHolidayDao apiHolidayDao;

  public List<HolidayApi> consume() throws IOException {

    init();
    logger.info("Api response prepared");
    Response response = webTarget.request().get();
    String resp = response.readEntity(String.class);

    return parser.parseHolidaysFromApi(resp);
  }

  private void init() {
    logger.info("Api consumer started");
    Client client = ClientBuilder.newClient();

    webTarget = client.target(URI).queryParam("api_key", APIKEY)
        .queryParam("country", "PL").queryParam("year", LocalDate.now().getYear());
  }

  public void loadDataToDataBase() {
    try {
      logger.info("Load holidays to DB");
      List<Holiday> holidays = holidayMapper.mapApiToEntity(consume());
      holidays.forEach(holiday -> apiHolidayDao.addHoliday(holiday));
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
  }
}
