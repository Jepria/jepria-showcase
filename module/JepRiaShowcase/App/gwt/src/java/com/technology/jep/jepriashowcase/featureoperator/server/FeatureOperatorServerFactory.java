package com.technology.jep.jepriashowcase.featureoperator.server;

import static com.technology.jep.jepriashowcase.featureoperator.server.FeatureOperatorServerConstant.DATA_SOURCE_JNDI_NAME;

import com.technology.jep.jepria.server.ServerFactory;
import com.technology.jep.jepriashowcase.featureoperator.server.dao.FeatureOperator;
import com.technology.jep.jepriashowcase.featureoperator.server.dao.FeatureOperatorDao;

public class FeatureOperatorServerFactory extends ServerFactory<FeatureOperator> {

  private FeatureOperatorServerFactory() {
    super(new FeatureOperatorDao(), DATA_SOURCE_JNDI_NAME);
  }

  public static final FeatureOperatorServerFactory instance = new FeatureOperatorServerFactory();

}
