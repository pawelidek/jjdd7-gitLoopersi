package com.infoshareacademy.gitloopersi.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoshareacademy.gitloopersi.restapi.service.HolidayApiService;
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
public class HolidayController {

  @Inject
  HolidayApiService holidayApiService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @GET
  @Path("/id/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotification(@PathParam("id") int id) throws JsonProcessingException {
    logger.info(
        "Process of prepare response on request find holiday identified by id={} has been started",
        id);
    return Response.ok()
        .entity(holidayApiService.getHolidayJsonObjectById(id))
        .build();
  }
}
