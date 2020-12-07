package com.technology.jep.jepriashowcase.featureprocess;

import com.technology.jep.jepriashowcase.featureprocess.dao.FeatureProcessDao;
import com.technology.jep.jepriashowcase.featureprocess.dao.FeatureProcessDaoImpl;
import org.jepria.server.ServerFactory;
import org.jepria.server.service.rest.EntityService;
import org.jepria.server.service.rest.EntityServiceImpl;

import javax.inject.Inject;

public class FeatureProcessServerFactory extends ServerFactory<FeatureProcessDao> {

  @Inject
  private FeatureProcessServerFactory(FeatureProcessDao dao) {
    super(dao, "jdbc/ITMDS");
  }

  public FeatureProcessService getService() {
    return new FeatureProcessService(getDao());
  }

  /**
   * @return сервис, воплощающий логику CRUD-операций (create, get-by-id, update, delete)
   */
  public EntityService getEntityService() {
    return new EntityServiceImpl(getDao(), new FeatureProcessRecordDefinition());
  }
}
