package com.technology.jep.jepriashowcase.custom.client.ui.plain;

public class Credential {
  private String login;
  private String password;
  
  public Credential(String login, String password) {
    setLogin(login);
    setPassword(password);
  }
  
  public String getLogin() {
    return login;
  }
  
  public void setLogin(String login) {
    this.login = login;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
}
