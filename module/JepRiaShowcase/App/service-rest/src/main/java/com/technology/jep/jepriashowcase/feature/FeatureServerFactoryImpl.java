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

  private FeatureService service;
  private EntityService entityService;
  private SearchService searchService;

  // Inject делаем в конструктор
  @Inject
  public FeatureServerFactoryImpl(FeatureDao dao) {
    super(dao, "jdbc/ITMDS");
  }

  public FeatureService getService() {
    if (null  == this.service) {
      this.service = new FeatureServiceImpl(getDao());
    }
    return this.service;
  }

  /**
   * @return сервис, воплощающий логику CRUD-операций (create, get-by-id, update, delete)
   */
  public EntityService getEntityService() {
    if (null == this.entityService) {
      this.entityService = new EntityServiceImpl(getDao(), new FeatureRecordDefinition());
    }
    return this.entityService;
  }

  /**
   * @return сервис, воплощающий логику поиска объектов сущности
   */
  public SearchService getSearchService(Supplier<HttpSession> session) {
    if (null == this.searchService) {
      this.searchService = new SearchServiceImpl(getDao(), new FeatureRecordDefinition(), session);
    }
    return this.searchService;
  }

}
