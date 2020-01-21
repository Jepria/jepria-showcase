package com.technology.jep.jepriashowcase.custom.shared;

/**
 * Общие константы модуля с произвольным располажением.
 */
public class CustomConstant {  
  /**
   * Адрес сервлета, через который осуществляется аутентификация.
   */
  public static final String AUTHENTICATION_SERVLET_URI = "authenticationServlet";
  /**
   * Префикс имени атрибута http-сессии, в котором передается URI перенаправления.
   */
  public static final String REDIRECT_TO_BASE_SESSION_ATTRIBUTE = "redirectTo_";
  /**
   * Имя параметра http-запроса, в котором передается полное имя атрибута сессии, содержащего URI перенаправления.
   */
  public static final String REDIRECT_TO_SESSION_REQUEST_PARAMETER = "redirectToSession";
}
