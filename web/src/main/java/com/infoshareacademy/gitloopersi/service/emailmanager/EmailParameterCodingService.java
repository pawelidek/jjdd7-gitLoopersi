package com.infoshareacademy.gitloopersi.service.emailmanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;
import javax.ejb.Stateless;

@Stateless
public class EmailParameterCodingService {

  public static String doEncode(Map<String, Object> messageParams)
      throws JsonProcessingException, UnsupportedEncodingException {

    String messageParamsJson = new ObjectMapper().writeValueAsString(messageParams);

    return Base64.getEncoder().encodeToString(messageParamsJson.getBytes("UTF-8"));
  }

  public static Map<String, Object> doDecode(String jsonParams) throws IOException {

    String paramJson = new String(Base64.getDecoder().decode(jsonParams.getBytes()));

    return new ObjectMapper().readValue(paramJson, Map.class);
  }
}
