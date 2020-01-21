package com.technology.jep.jepriashowcase.featureoperator.server.service;


import com.technology.jep.jepriashowcase.featureoperator.server.FeatureOperatorServerFactory;
import com.technology.jep.jepriashowcase.featureoperator.server.dao.FeatureOperator;
import com.technology.jep.jepriashowcase.featureoperator.shared.record.FeatureOperatorRecordDefinition;
import com.technology.jep.jepriashowcase.featureoperator.shared.service.FeatureOperatorService;
import com.technology.jep.jepria.server.service.JepDataServiceServlet;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.field.option.JepOption;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
 
@RemoteServiceRelativePath("ProtectedServices/FeatureOperatorService")
public class FeatureOperatorServiceImpl extends JepDataServiceServlet<FeatureOperator> implements FeatureOperatorService  {
 
  private static final long serialVersionUID = 1L;
 
  public FeatureOperatorServiceImpl() {
    super(FeatureOperatorRecordDefinition.instance, FeatureOperatorServerFactory.instance);
  }

  @Override
  public List<JepOption> getFeatureOperator(String operatorName) throws ApplicationException {
    List<JepOption> result = null;
    try {
      result = dao.getFeatureOperator(operatorName);
    } catch (Throwable th) {
      throw new ApplicationException(th.getLocalizedMessage(), th);
    }
    return result;
  }
}
