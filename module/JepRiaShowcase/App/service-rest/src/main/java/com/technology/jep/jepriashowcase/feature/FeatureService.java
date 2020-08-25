package com.technology.jep.jepriashowcase.feature;

import java.util.List;
import org.jepria.server.data.OptionDto;
import org.jepria.server.service.security.Credential;

public interface FeatureService {

  public void setFeatureResponsible(Integer featureId, Integer responsibleId, Credential credential);

  public List<OptionDto<Integer>> getFeatureOperator();

  public List<OptionDto<String>> getFeatureStatus();

}
