package com.clouway.core;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Contact {
  private final String name;
  private final String phone;
  private final String email;

  public Contact(String name, String phone, String email) {
    this.name = name;
    this.phone = phone;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "Contact{" +
            "name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
