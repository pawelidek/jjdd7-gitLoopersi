package com.infoshareacademy.gitloopersi.service.apimanager;

import com.infoshareacademy.gitloopersi.dao.HolidayDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.domain.jsonapi.HolidayResponse;
import com.infoshareacademy.gitloopersi.web.mapper.HolidayMapper;
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
  private ParserService parserService;

  @EJB
  private HolidayMapper holidayMapper;

  @EJB
  private HolidayDaoBean holidayDaoBean;

  public void consume() throws IOException {

    init();
    logger.info("Api response prepared");
    Response response = webTarget.request().get();
    String resp = response.readEntity(String.class);

    List<HolidayResponse> holidayList = parserService.parseHolidaysFromApi(resp);
    loadDataToDataBase(holidayList);
  }

  private void init() {
    logger.info("Api consumer started");
    Client client = ClientBuilder.newClient();

    webTarget = client.target(URI).queryParam("api_key", APIKEY)
        .queryParam("country", "PL").queryParam("year", LocalDate.now().getYear());
  }

  private void loadDataToDataBase(List<HolidayResponse> holidayList) {
    logger.info("Load holidays to DB");
    List<Holiday> holidays = holidayMapper.mapApiToEntity(
        holidayList);
    holidays.forEach(holiday -> holidayDaoBean.addHoliday(holiday));
  }
}
