package com.technology.jep.jepriashowcase.feature;

import com.technology.jep.jepriashowcase.feature.dao.FeatureDao;
import com.technology.jep.jepriashowcase.feature.dao.FeatureDaoImpl;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import org.jepria.compat.server.dao.transaction.TransactionFactory;
import org.jepria.server.ServerFactory;
import org.jepria.server.service.rest.EntityService;
import org.jepria.server.service.rest.EntityServiceImpl;
import org.jepria.server.service.rest.SearchService;
import org.jepria.server.service.rest.SearchServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.function.Supplier;

public class FeatureServerFactoryImpl extends ServerFactory<FeatureDao> implements FeatureServerFactory {

  private static FeatureService _service;
  private static EntityService  _entityService;
  private static SearchService  _searchService;

  private static FeatureServerFactoryImpl instance;

  FeatureDaoImpl _dao;

  private FeatureDao proxyDao;
  private String dataSourceJndiName = "jdbc/ITMDS";
  private String moduleName;

  @Inject
  public FeatureServerFactoryImpl(FeatureDaoImpl dao) {
    super(dao, "jdbc/ITMDS");
    _dao = dao;
  }

//  public static FeatureServerFactory getInstance() {
//    if (null == instance) {
//      instance = new FeatureServerFactoryImpl();
//    }
//    return instance;
//  }

  public static String test() {
    return "foo";
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
