package com.technology.jep.jepriashowcase.featureprocess.shared.record;

import static com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureProcessFieldNames.*;
import static com.technology.jep.jepria.shared.field.JepTypeEnum.*;
import com.technology.jep.jepria.shared.field.JepTypeEnum;
import com.technology.jep.jepria.shared.record.JepRecordDefinition;
 
import java.util.HashMap;
import java.util.Map;
 
public class FeatureProcessRecordDefinition extends JepRecordDefinition {
 
  private static final long serialVersionUID = 1L;
 
  public static final FeatureProcessRecordDefinition instance = new FeatureProcessRecordDefinition();
 
  private FeatureProcessRecordDefinition() {
    super(buildTypeMap()
      , new String[]{FEATURE_PROCESS_ID}
    );
  }
 
  private static Map<String, JepTypeEnum> buildTypeMap() {
    Map<String, JepTypeEnum> typeMap = new HashMap<String, JepTypeEnum>();
    typeMap.put(FEATURE_PROCESS_ID, INTEGER);
    typeMap.put(FEATURE_STATUS_NAME, STRING);
    typeMap.put(FEATURE_STATUS_CODE, STRING);
    typeMap.put(DATE_INS, DATE_TIME);
    typeMap.put(OPERATOR_NAME, STRING);
    return typeMap;
  }
}
