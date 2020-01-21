package com.technology.jep.jepriashowcase.featureprocess.server;

import static com.technology.jep.jepriashowcase.featureprocess.server.FeatureProcessServerConstant.DATA_SOURCE_JNDI_NAME;

import com.technology.jep.jepria.server.ServerFactory;
import com.technology.jep.jepriashowcase.featureprocess.server.dao.FeatureProcess;
import com.technology.jep.jepriashowcase.featureprocess.server.dao.FeatureProcessDao;

public class FeatureProcessServerFactory extends ServerFactory<FeatureProcess> {

  private FeatureProcessServerFactory() {
    super(new FeatureProcessDao(), DATA_SOURCE_JNDI_NAME);
  }

  public static final FeatureProcessServerFactory instance = new FeatureProcessServerFactory();

}
