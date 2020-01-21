package com.technology.jep.jepriashowcase.featureprocess.shared.service;
 
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepria.shared.service.data.JepDataService;

@RemoteServiceRelativePath("ProtectedServices/FeatureProcessService")
public interface FeatureProcessService extends JepDataService {
  List<JepOption> getFeatureStatus() throws ApplicationException;
}
