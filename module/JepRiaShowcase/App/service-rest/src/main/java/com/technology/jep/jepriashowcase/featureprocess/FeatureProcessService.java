package com.technology.jep.jepriashowcase.featureprocess;

import com.technology.jep.jepriashowcase.featureprocess.dao.FeatureProcessDao;
import com.technology.jep.jepriashowcase.featureprocess.dto.FeatureProcessDto;
import com.technology.jep.jepriashowcase.featureprocess.dto.FeatureProcessSearchDtoInternal;
import org.jepria.server.service.security.Credential;

import java.util.List;

public class FeatureProcessService {
  
  private FeatureProcessDao dao;
  
  public FeatureProcessService(FeatureProcessDao dao) {
    this.dao = dao;
  }
  
  public List<FeatureProcessDto> findFeatureProcess(FeatureProcessSearchDtoInternal dto, Credential credential) {
    return (List<FeatureProcessDto>)dao.find(dto, credential.getOperatorId());
  }
}
