package com.technology.jep.jepriashowcase.featureprocess.server.service;


import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.technology.jep.jepria.server.service.JepDataServiceServlet;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepriashowcase.featureprocess.server.FeatureProcessServerFactory;
import com.technology.jep.jepriashowcase.featureprocess.server.dao.FeatureProcess;
import com.technology.jep.jepriashowcase.featureprocess.shared.record.FeatureProcessRecordDefinition;
import com.technology.jep.jepriashowcase.featureprocess.shared.service.FeatureProcessService;
 
@RemoteServiceRelativePath("ProtectedServices/FeatureProcessService")
public class FeatureProcessServiceImpl extends JepDataServiceServlet<FeatureProcess> implements FeatureProcessService  {
 
  private static final long serialVersionUID = 1L;
 
  public FeatureProcessServiceImpl() {
    super(FeatureProcessRecordDefinition.instance, FeatureProcessServerFactory.instance);
  }
  
  @Override
  public List<JepOption> getFeatureStatus() throws ApplicationException {
    List<JepOption> result = null;
    try {
      result = dao.getFeatureStatus();
    } catch (Throwable th) {
      throw new ApplicationException(th.getLocalizedMessage(), th);
    }
    return result;
  }
}
