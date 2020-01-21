package com.technology.jep.jepriashowcase.featureprocess;

import com.technology.jep.jepriashowcase.featureprocess.dto.FeatureProcessCreateDto;
import com.technology.jep.jepriashowcase.featureprocess.dto.FeatureProcessDto;
import org.jepria.server.data.RecordDefinitionDtoImpl;

public class FeatureProcessRecordDefinition extends RecordDefinitionDtoImpl {

  public FeatureProcessRecordDefinition() {
    super(FeatureProcessCreateDto.class, FeatureProcessDto.class);
  }

}
