package com.infoshareacademy.gitloopersi.freemarker;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConfigProvider {

<<<<<<< HEAD
    private Configuration configuration;

    public Configuration getConfiguration() {
        if (configuration == null) {
            configuration = new Configuration(Configuration.VERSION_2_3_29);
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setLogTemplateExceptions(false);
            configuration.setWrapUncheckedExceptions(true);
        }
        return configuration;
    }
}
=======
  private Configuration configuration;

  public Configuration getConfiguration() {

    if (configuration == null) {
      configuration = new Configuration(Configuration.VERSION_2_3_29);
      configuration.setDefaultEncoding("UTF-8");
      configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
      configuration.setLogTemplateExceptions(false);
      configuration.setWrapUncheckedExceptions(true);
    }
    return configuration;
  }
}
>>>>>>> 5bff9a63a26d39dfc7c65fc96a7ffdeb1c7b1cf3
