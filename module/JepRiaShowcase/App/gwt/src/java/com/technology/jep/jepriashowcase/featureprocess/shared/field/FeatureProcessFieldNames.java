package com.technology.jep.jepriashowcase.featureprocess.shared.field;
 
import com.technology.jep.jepria.shared.field.JepRecordFieldNames;
 
public class FeatureProcessFieldNames extends JepRecordFieldNames {
  
  //переведен в lowercase, чтобы нормально взаимодействовать с Feature
  //начиная с какая-то момента fieldNames генерируются в uppercases
  public static final String FEATURE_ID = "feature_id";
  
  public static final String FEATURE_PROCESS_ID = "FEATURE_PROCESS_ID";
  public static final String FEATURE_STATUS_NAME = "FEATURE_STATUS_NAME";
  public static final String FEATURE_STATUS_CODE = "FEATURE_STATUS_CODE";
  public static final String DATE_INS = "DATE_INS";
  public static final String OPERATOR_NAME = "OPERATOR_NAME";
}
