package com.technology.jep.jepriashowcase.featureprocess;

import com.technology.jep.jepriashowcase.featureprocess.dao.FeatureProcessDao;
import com.technology.jep.jepriashowcase.featureprocess.dao.FeatureProcessDaoImpl;
import org.jepria.server.ServerFactory;
import org.jepria.server.service.rest.EntityService;
import org.jepria.server.service.rest.EntityServiceImpl;

public class FeatureProcessServerFactory extends ServerFactory<FeatureProcessDao> {

  private FeatureProcessServerFactory() {
    super(new FeatureProcessDaoImpl(), "jdbc/ITMDS");
  }

  public static FeatureProcessServerFactory getInstance() {
    return new FeatureProcessServerFactory();
  }

  public FeatureProcessService getService() {
    return new FeatureProcessService();
  }

  /**
   * @return сервис, воплощающий логику CRUD-операций (create, get-by-id, update, delete)
   */
  public EntityService getEntityService() {
    return new EntityServiceImpl(getDao(), new FeatureProcessRecordDefinition());
  }
}
