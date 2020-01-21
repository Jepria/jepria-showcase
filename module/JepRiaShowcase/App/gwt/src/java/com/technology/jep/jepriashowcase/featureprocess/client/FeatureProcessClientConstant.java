package com.technology.jep.jepriashowcase.featureprocess.client;

import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.*; 
 
import com.google.gwt.core.client.GWT;
import com.technology.jep.jepriashowcase.featureprocess.shared.FeatureProcessConstant;
import com.technology.jep.jepriashowcase.featureprocess.shared.text.FeatureProcessText;
 
public class FeatureProcessClientConstant extends FeatureProcessConstant {
 
  public static FeatureProcessText featureProcessText = (FeatureProcessText) GWT.create(FeatureProcessText.class);
  
}
