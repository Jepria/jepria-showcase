package com.technology.jep.jepriashowcase.featureoperator.client;

import com.google.gwt.core.client.GWT;
import com.technology.jep.jepriashowcase.featureoperator.shared.FeatureOperatorConstant;
import com.technology.jep.jepriashowcase.featureoperator.shared.text.FeatureOperatorText;
 
public class FeatureOperatorClientConstant extends FeatureOperatorConstant {
 
  public final static int FEATUREOPERATOR_OPERATOR_ID_COMBOBOX_MIN_CHAR = 3;
  
  public static FeatureOperatorText featureOperatorText = (FeatureOperatorText) GWT.create(FeatureOperatorText.class);
  
}
