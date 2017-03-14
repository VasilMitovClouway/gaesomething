package com.clouway.core;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Main {
  public static void main(String[] args) {
    Sendgrid email = new Sendgrid("Alroy", "Alroy93102339640");
    email.setTo("qnislav.nachev@gmail.com")
            .setFrom("v.mitov.clouway@gmail.com")
            .setSubject("subject")
            .setText("Hello World!")
            .setHtml("Hello World !");
    email.send();
  }
}
