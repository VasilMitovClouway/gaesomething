package com.clouway.http;

import com.clouway.core.FreemarkerTemplate;
import com.google.common.io.ByteStreams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class HomeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    FreemarkerTemplate template = new FreemarkerTemplate("index.html");
    OutputStream outputStream = resp.getOutputStream();
    LinkedHashMap map=new LinkedHashMap(){{put("contactList",new LinkedList<>());}};
    ByteStreams.copy(template.process(map), outputStream);
    outputStream.flush();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
