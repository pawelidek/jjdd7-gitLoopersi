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

@Path("/holiday")
public class HolidayApi {

  @Inject
  HolidayService holidayService;

  @Inject
  HolidayMapper holidayMapper;

  @GET
  @Path("/id/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotification(@PathParam("id") int id) throws JsonProcessingException {
    Holiday holidayEntity = holidayService.findHolidayById(id);
    com.infoshareacademy.gitloopersi.domain.api.HolidayApi holidayToJSON = holidayMapper
        .mapEntityToApi(holidayEntity);
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStr = objectMapper.writeValueAsString(holidayToJSON);
    return Response.ok()
        .entity(jsonStr)
        .build();
  }
}
