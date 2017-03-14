package com.clouway.core;

import com.clouway.http.ResourceServlet;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class FreemarkerTemplate {
  private final String templateName;

  public FreemarkerTemplate(String templateName) {
    this.templateName = templateName;
  }

  public InputStream process(Map<String, List> values) throws IOException {
    Configuration configuration = new Configuration();
    configuration.setTemplateLoader(new ClassTemplateLoader(ResourceServlet.class, ""));
    configuration.setDefaultEncoding("UTF-8");
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);//To be changed to RETHROW_HANDLER at end
    Template template = configuration.getTemplate(templateName);
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    try {
      template.process(values, new PrintWriter(bout));
    } catch (TemplateException e) {
      throw new IOException("Got error while trying to evaluate response", e);
    }

    return new ByteArrayInputStream(bout.toByteArray());

  }
}
