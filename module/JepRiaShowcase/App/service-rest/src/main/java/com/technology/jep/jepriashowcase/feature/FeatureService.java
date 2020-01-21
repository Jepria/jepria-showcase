package com.technology.jep.jepriashowcase.feature;

import org.jepria.server.data.OptionDto;
import org.jepria.server.service.security.Credential;

import java.util.List;

public class FeatureService {

  public void setFeatureResponsible(Integer featureId, Integer responsibleId, Credential credential) {
    FeatureServerFactory.getInstance().getDao().setFeatureResponsible(featureId, responsibleId, credential.getOperatorId());
  }

  public List<OptionDto<Integer>> getFeatureOperator() {
    return FeatureServerFactory.getInstance().getDao().getFeatureOperator();
  }

  public List<OptionDto<String>> getFeatureStatus() {
    return FeatureServerFactory.getInstance().getDao().getFeatureStatus();
  }
}
