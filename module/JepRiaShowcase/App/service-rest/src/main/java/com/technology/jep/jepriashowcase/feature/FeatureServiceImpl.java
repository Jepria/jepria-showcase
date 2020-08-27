package com.technology.jep.jepriashowcase.feature;

import com.technology.jep.jepriashowcase.feature.dao.FeatureDao;
import java.util.List;
import org.jepria.server.data.OptionDto;
import org.jepria.server.service.security.Credential;

public class FeatureServiceImpl implements FeatureService {

  private FeatureDao dao;

  public FeatureServiceImpl(FeatureDao dao) {
    this.dao = dao;
  }

  public void setFeatureResponsible(Integer featureId, Integer responsibleId, Credential credential) {
    this.dao.setFeatureResponsible(featureId, responsibleId, credential.getOperatorId());
  }

  public List<OptionDto<Integer>> getFeatureOperator() {
    return this.dao.getFeatureOperator();
  }

  public List<OptionDto<String>> getFeatureStatus() {
    return this.dao.getFeatureStatus();
  }

}
