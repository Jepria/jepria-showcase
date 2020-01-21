package com.technology.jep.jepriashowcase.featureoperator.shared.service;
 
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.technology.jep.jepria.shared.service.data.JepDataService;

import java.util.List;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.field.option.JepOption;

@RemoteServiceRelativePath("ProtectedServices/FeatureOperatorService")
public interface FeatureOperatorService extends JepDataService {
  List<JepOption> getFeatureOperator(String operatorName) throws ApplicationException;
}
