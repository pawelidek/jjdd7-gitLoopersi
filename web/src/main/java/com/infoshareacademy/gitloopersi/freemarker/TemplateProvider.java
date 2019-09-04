package com.infoshareacademy.gitloopersi.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;

<<<<<<< HEAD

@RequestScoped
public class TemplateProvider {
    private final String TEMPLATES_DIRECTORY_PATH = "WEB-INF/fm-templates";

    private Configuration configuration;

    @Inject
    private ConfigProvider configProvider;


    public Template getTemplate(ServletContext servletContext, String templateName) throws IOException {
        configuration = configProvider.getConfiguration();
        configuration.setServletContextForTemplateLoading(servletContext, TEMPLATES_DIRECTORY_PATH);
        return configuration.getTemplate(templateName);
    }
=======
@RequestScoped
public class TemplateProvider {

  private final String TEMPLATES_DIRECTORY_PATH = "WEB-INF/fm-templates";

  private Configuration configuration;

  @Inject
  private ConfigProvider configProvider;

  public Template getTemplate(ServletContext servletContext, String templateName)
      throws IOException {

    configuration = configProvider.getConfiguration();
    configuration.setServletContextForTemplateLoading(servletContext, TEMPLATES_DIRECTORY_PATH);
    return configuration.getTemplate(templateName);
  }

>>>>>>> 5bff9a63a26d39dfc7c65fc96a7ffdeb1c7b1cf3
}
