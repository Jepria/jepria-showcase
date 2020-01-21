package com.technology.jep.jepriashowcase.feature.server.service;

import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_ASSIGN_RESPONSIBLE_FEATURE;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_ASSIGN_WORK_SEQUENCE_FEATURE;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.technology.jep.jepria.server.service.JepDataServiceServlet;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepria.shared.load.FindConfig;
import com.technology.jep.jepria.shared.record.JepRecord;
import com.technology.jep.jepriashowcase.feature.server.FeatureServerFactory;
import com.technology.jep.jepriashowcase.feature.server.dao.Feature;
import com.technology.jep.jepriashowcase.feature.shared.record.FeatureRecordDefinition;
import com.technology.jep.jepriashowcase.feature.shared.service.FeatureService;
 
@RemoteServiceRelativePath("ProtectedServices/FeatureService")
public class FeatureServiceImpl extends JepDataServiceServlet<Feature> implements FeatureService  {
 
  private static final long serialVersionUID = 1L;
 
  public FeatureServiceImpl() {
    super(FeatureRecordDefinition.instance, FeatureServerFactory.instance);
  }
  
  @Override
  public JepRecord update(FindConfig updateConfig) throws ApplicationException {
    JepRecord record = updateConfig.getTemplateRecord();
    
    logger.trace("BEGIN update(" + record + ")");
    JepRecord resultRecord = null;
    
    prepareFileFields(record);
    boolean isUpdateResponsible = false;
    boolean updateWorkSequence = false;
    
    isUpdateResponsible = isRole(ROLE_JRS_ASSIGN_RESPONSIBLE_FEATURE, false);
    updateWorkSequence = isRole(ROLE_JRS_ASSIGN_WORK_SEQUENCE_FEATURE, false);
 
    dao.update(record, getOperatorId(), isUpdateResponsible, updateWorkSequence);
    
    updateLobFields(record);
    resultRecord = findByPrimaryKey(recordDefinition.buildPrimaryKeyMap(record));
    clearFoundRecords(updateConfig);
    
    logger.trace("END update(" + resultRecord + ")");
    return resultRecord;
  }
  
  @Override
  public List<JepOption> getFeatureOperator(String featureOperatorName, Integer maxRowCount)
      throws ApplicationException {
    
    List<JepOption> result = null;
    try {
      result = dao.getFeatureOperator(featureOperatorName, maxRowCount, getOperatorId());
    } catch (Throwable th) {
      throw new ApplicationException(th.getLocalizedMessage(), th);
    }
    return result;
  }
  
  @Override
  public void setFeatureWorkSequence(Integer featureId, Integer workSequence)
      throws ApplicationException {
    try {
      dao.setFeatureWorkSequence(featureId, workSequence, getOperatorId());
    } catch (Throwable th) {
      throw new ApplicationException(th.getLocalizedMessage(), th);
    }
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
