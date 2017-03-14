package com.clouway.http;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class RegisterContactServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/").forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("contactName");
    String email = req.getParameter("email");
    String phone = req.getParameter("phone");
    registerContact(name,email,phone);
    doGet(req,resp);
  }

  private void registerContact(String name,String email,String phone){
    Entity contact = new Entity("contact");
    contact.setProperty("name",name);
    contact.setProperty("phone", phone);
    contact.setProperty("email", email);
    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    datastoreService.put(contact);
  }
}
