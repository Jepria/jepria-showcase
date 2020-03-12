package com.technology.jep.jepriashowcase.main.client;

import com.google.gwt.core.client.GWT;
import com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant;
import com.technology.jep.jepriashowcase.main.shared.text.JepRiaShowcaseText;

public class JepRiaShowcaseClientConstant extends JepRiaShowcaseConstant {
  // Идентификаторы модулей.
  public static final String CUSTOM_MODULE_ID = "Custom";
  public static final String SEARCH_MODULE_ID = "Search";
  public static final String SUPPLIER_MODULE_ID = "Supplier";
  public static final String GOODS_MODULE_ID = "Goods";
  public static final String ARSENIC_MODULE_ID = "Arsenic";
  public static final String SHOPGOODS_MODULE_ID = "ShopGoods";
  public static final String SIMPLE_MODULE_ID = "Simple";
  public static final String ALLSHOPGOODS_MODULE_ID = "AllShopGoods";
  public static final String REQUEST_MODULE_ID = "Request";
  public static final String REQUESTPROCESS_MODULE_ID = "RequestProcess";
  public static final String FEATURE_MODULE_ID = "Feature";
  public static final String FEATUREOPERATOR_MODULE_ID = "FeatureOperator";
  public static final String FEATUREPROCESS_MODULE_ID = "FeatureProcess";
  public static final String FEATUREPROCESS_REACT_MODULE_ID = "FeatureProcessReact";
  
  
  /**
   * Тексты клиентской части JepRiaShowcase.
   */
  public static JepRiaShowcaseText jepRiaShowcaseText = (JepRiaShowcaseText) GWT.create(JepRiaShowcaseText.class);
  
  public static final String URL_FULL_SCREEN = "Entry.jsp?em=Goods&es=sh#sh:sm=Goods&ws=sh";
  public static final String URL_CUSTOM_MODULE = "JepRiaShowcaseCustom.jsp?em=Custom&es=sh#sh:sm=Custom&ws=sh";
  public static final String URL_SEARCH_MODULE = "JepRiaShowcaseSearch.jsp?em=Search&es=sh#sh:sm=Search&ws=sh";
}
