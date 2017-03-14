package com.clouway.http;

import com.clouway.core.Contact;
import com.clouway.core.FreemarkerTemplate;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.common.io.ByteStreams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class FindContactServlet extends HttpServlet {
  private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  private final MemcacheService cashe= MemcacheServiceFactory.getMemcacheService();


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("findName");
    Map<String, List> result = findContactsMatching(name);
    FreemarkerTemplate template = new FreemarkerTemplate("index.html");
    OutputStream outputStream = resp.getOutputStream();
    ByteStreams.copy(template.process(result), outputStream);
    outputStream.flush();
  }

  private Map<String, List> findContactsMatching(String nameToMatch) {
    Query query = new Query("contact");
    PreparedQuery preparedQuery = datastore.prepare(query);
    Map<String, List> result = new LinkedHashMap<>();
    List<Contact> contactsList = new LinkedList<>();
    for (Entity contact : preparedQuery.asIterable()) {
      String name = (String) contact.getProperty("name");
      if (matches(name, nameToMatch)) {
        Contact matchedContact = new Contact((String) contact.getProperty("name"),
                (String) contact.getProperty("phone"),
                (String) contact.getProperty("email"));
        System.out.println(matchedContact);
        contactsList.add(matchedContact);
      }
    }
    result.put("contactList", contactsList);
    return result;
  }

  private boolean matches(String contact, String searchedContact) {
    return searchedContact.equals("") || contact.equals(searchedContact) || matchesPartially(contact, searchedContact);
  }

  private boolean matchesPartially(String contact, String searchedContact) {
    return contact.startsWith(searchedContact);
  }
}
