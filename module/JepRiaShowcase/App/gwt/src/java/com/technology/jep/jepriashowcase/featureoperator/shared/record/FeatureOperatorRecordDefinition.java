package com.technology.jep.jepriashowcase.featureoperator.shared.record;

import static com.technology.jep.jepria.shared.field.JepTypeEnum.DATE_TIME;
import static com.technology.jep.jepria.shared.field.JepTypeEnum.INTEGER;
import static com.technology.jep.jepria.shared.field.JepTypeEnum.STRING;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.*;

import java.util.HashMap;
import java.util.Map;

import com.technology.jep.jepria.shared.field.JepLikeEnum;
import com.technology.jep.jepria.shared.field.JepTypeEnum;
import com.technology.jep.jepria.shared.record.JepRecordDefinition;
 
public class FeatureOperatorRecordDefinition extends JepRecordDefinition {
 
  private static final long serialVersionUID = 1L;
 
  public static final FeatureOperatorRecordDefinition instance = new FeatureOperatorRecordDefinition();
 
  private FeatureOperatorRecordDefinition() {
    super(buildTypeMap()
      , new String[]{FEATURE_OPERATOR_ID}
    );
    
    setLikeMap(new HashMap<String, JepLikeEnum>(){{
      put(FEATURE_OPERATOR_NAME, JepLikeEnum.CONTAINS);
    }});
  }
 
  private static Map<String, JepTypeEnum> buildTypeMap() {
    Map<String, JepTypeEnum> typeMap = new HashMap<String, JepTypeEnum>();
    typeMap.put(FEATURE_OPERATOR_ID, INTEGER);
    typeMap.put(FEATURE_OPERATOR_NAME, STRING);
    typeMap.put(OPERATOR_NAME, STRING);
    typeMap.put(DATE_INS, DATE_TIME);
    return typeMap;
  }
  
  
}
