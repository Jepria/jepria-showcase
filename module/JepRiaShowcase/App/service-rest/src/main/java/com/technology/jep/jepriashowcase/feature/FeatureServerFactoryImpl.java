package com.technology.jep.jepriashowcase.feature;

import com.technology.jep.jepriashowcase.feature.dao.FeatureDao;
import org.jepria.server.ServerFactory;
import org.jepria.server.service.rest.EntityService;
import org.jepria.server.service.rest.EntityServiceImpl;
import org.jepria.server.service.rest.SearchService;
import org.jepria.server.service.rest.SearchServiceImpl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.function.Supplier;

public class FeatureServerFactoryImpl extends ServerFactory<FeatureDao> implements FeatureServerFactory {

  private static FeatureService _service;
  private static EntityService  _entityService;
  private static SearchService  _searchService;

  private static FeatureServerFactoryImpl instance;

  FeatureDao _dao;

  private FeatureDao proxyDao;
  private String dataSourceJndiName = "jdbc/ITMDS";
  private String moduleName;

  @Inject
  public FeatureServerFactoryImpl(FeatureDao dao) {
    super(dao, "jdbc/ITMDS");
    _dao = dao;
  }

  public FeatureService getService() {
    if (null  == _service) {
      _service = new FeatureServiceImpl(getDao());
    }
    return _service;
  }

  /**
   * @return сервис, воплощающий логику CRUD-операций (create, get-by-id, update, delete)
   */
  public EntityService getEntityService() {
    if (null == _entityService) {
      _entityService = new EntityServiceImpl(getDao(), new FeatureRecordDefinition());
    }
    return _entityService;
  }

  /**
   * @return сервис, воплощающий логику поиска объектов сущности
   */
  public SearchService getSearchService(Supplier<HttpSession> session) {
    if (null == _searchService) {
      _searchService = new SearchServiceImpl(getDao(), new FeatureRecordDefinition(), session);
    }
    return _searchService;
  }

}
