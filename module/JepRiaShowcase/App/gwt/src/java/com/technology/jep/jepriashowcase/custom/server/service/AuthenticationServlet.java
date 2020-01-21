package com.technology.jep.jepriashowcase.custom.server.service;

import static com.technology.jep.jepria.shared.util.JepRiaUtil.isEmpty;
import static com.technology.jep.jepriashowcase.custom.shared.CustomConstant.REDIRECT_TO_SESSION_REQUEST_PARAMETER;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет предназначен для перехода на заданный URI после аутентификации через Sso.<br/>
 * Для перехода к Sso сервлет закрыт настройками безопасности в web.xml.<br>
 */
public class AuthenticationServlet extends HttpServlet{
  
  private static final long serialVersionUID = 1L;

  /**
   * Вызывает метод {@link #doGet(HttpServletRequest, HttpServletResponse)}.
   * @param request запрос
   * @param response ответ
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    doGet(request, response);
  }
  
  /**
   * Выполняет перенаправление на сохранённый в параметрах сессии URI.<br/>
   * URI перенаправления извлекается из атрибута сессии, заданного в параметре
   * <code>REDIRECT_TO_SESSION_REQUEST_PARAMETER</code>. Если параметр пуст или не задан,
   * перенаправляет на context path приложения.
   * @param request запрос
   * @param response ответ
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    
    String url = request.getContextPath();
    String redirectToSession = request.getParameter(REDIRECT_TO_SESSION_REQUEST_PARAMETER);
    
    if (!isEmpty(redirectToSession)){
      Object attributeValue = request.getSession().getAttribute(redirectToSession);
      if (!isEmpty(attributeValue)){
        url = (String)attributeValue;
      }
    }
    
    response.sendRedirect(url);
  }

}
