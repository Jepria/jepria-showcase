package com.technology.jep.jepriashowcase.main.shared;

import com.technology.jep.jepria.shared.JepRiaConstant;


/**
 * Общие константы приложения.
 */
public class JepRiaShowcaseConstant extends JepRiaConstant {

  public static final String URL_EMBEDDED = "JepRiaShowcaseEmbedded.jsp?em=Goods&es=sh#sh:sm=Goods&ws=sh";
  public static final String SUCCESS = "Success!";
  
  /**
   * Адрес сервлета для программной авторизации на Tomcat-окружении
   */
  public static final String TOMCAT_AUTLOGON_URL = "/JepRiaShowcase/autoLogonServlet?username={0}&password={1}&url=/JepRiaShowcase/ProtectedPage.jsp";
  
  /**
   * Роли для модуля Feature
   */
  public static final String ROLE_JRS_EDIT_FEATURE = "JrsEditFeature";
  public static final String ROLE_JRS_ASSIGN_RESPONSIBLE_FEATURE = "JrsAssignResponsibleFeature";
  public static final String ROLE_JRS_EDIT_ALL_FEATURE = "JrsEditAllFeature";
  public static final String ROLE_JRS_ASSIGN_WORK_SEQUENCE_FEATURE = "JrsAssignWorkSequenceFeature";
  
  /**
   * Роли для модуля FeatureOperator
   */
  public static final String ROLE_JRS_OPERATOR_FEATURE = "JrsOperatorFeature";
}
