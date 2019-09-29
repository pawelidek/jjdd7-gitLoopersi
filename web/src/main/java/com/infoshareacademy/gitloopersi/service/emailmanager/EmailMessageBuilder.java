package com.infoshareacademy.gitloopersi.service.emailmanager;

import java.io.IOException;
import java.util.Map;

public interface EmailMessageBuilder {

  String buildMessage(Map<String, Object> messageParams)
      throws IOException;
}
