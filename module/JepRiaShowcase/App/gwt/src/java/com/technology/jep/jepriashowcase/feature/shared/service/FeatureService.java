package com.technology.jep.jepriashowcase.feature.shared.service;
 
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepria.shared.service.data.JepDataService;
 
@RemoteServiceRelativePath("ProtectedServices/FeatureService")
public interface FeatureService extends JepDataService {
  List<JepOption> getFeatureOperator(String featureOperatorName, Integer maxRowCount) throws ApplicationException;

  void setFeatureWorkSequence(Integer featureId, Integer workSequence)
      throws ApplicationException;
  
  List<JepOption> getFeatureStatus() throws ApplicationException;
}
