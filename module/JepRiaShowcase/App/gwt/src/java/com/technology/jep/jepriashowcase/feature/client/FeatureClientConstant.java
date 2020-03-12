package com.technology.jep.jepriashowcase.feature.client;
 
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATUREPROCESS_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATUREPROCESS_REACT_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATURE_MODULE_ID;

import com.google.gwt.core.client.GWT;
import com.technology.jep.jepriashowcase.feature.shared.FeatureConstant;
import com.technology.jep.jepriashowcase.feature.shared.text.FeatureText;
 
public class FeatureClientConstant extends FeatureConstant {
  public static FeatureText featureText = (FeatureText) GWT.create(FeatureText.class);
  
  public final static int FEATURE_RESPONSIBLE_ID_COMBOBOX_MIN_CHAR = 3;
  
  public final static String[] scopeModuleIds = {FEATURE_MODULE_ID, FEATUREPROCESS_MODULE_ID, FEATUREPROCESS_REACT_MODULE_ID};
}
