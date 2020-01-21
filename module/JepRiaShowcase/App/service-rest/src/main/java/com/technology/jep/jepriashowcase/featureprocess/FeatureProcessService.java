package com.technology.jep.jepriashowcase.featureprocess;

import com.technology.jep.jepriashowcase.featureprocess.dto.FeatureProcessDto;
import com.technology.jep.jepriashowcase.featureprocess.dto.FeatureProcessSearchDtoInternal;
import org.jepria.server.service.security.Credential;

import java.util.List;

public class FeatureProcessService {
  public List<FeatureProcessDto> findFeatureProcess(FeatureProcessSearchDtoInternal dto, Credential credential) {
    return (List<FeatureProcessDto>)FeatureProcessServerFactory.getInstance().getDao().find(dto, credential.getOperatorId());
  }
}
