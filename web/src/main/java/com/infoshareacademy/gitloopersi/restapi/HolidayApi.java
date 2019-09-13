package com.infoshareacademy.gitloopersi.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.mapper.HolidayMapper;
import com.infoshareacademy.gitloopersi.service.HolidayService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/holiday")
public class HolidayApi {

  @Inject
  HolidayService holidayService;

  @Inject
  HolidayMapper holidayMapper;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @GET
  @Path("/id/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotification(@PathParam("id") int id) throws JsonProcessingException {
    logger.info(
        "Process of prepare response on request find holiday identified by id={} has been started",
        id);
    Holiday holidayEntity = holidayService.findHolidayById(id);
    com.infoshareacademy.gitloopersi.domain.api.Holiday holidayToJSON = holidayMapper
        .mapEntityToApi(holidayEntity);
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStr = objectMapper.writeValueAsString(holidayToJSON);
    return Response.ok()
        .entity(jsonStr)
        .build();
  }
}
