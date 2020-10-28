package com.technology.jep.jepriashowcase.goods;

import com.technology.jep.jepriashowcase.feature.FeatureRecordDefinition;
import com.technology.jep.jepriashowcase.feature.FeatureServerFactory;
import com.technology.jep.jepriashowcase.feature.FeatureService;
import com.technology.jep.jepriashowcase.feature.FeatureServiceImpl;
import com.technology.jep.jepriashowcase.feature.dao.FeatureDao;
import com.technology.jep.jepriashowcase.goods.dao.GoodsDao;
import org.jepria.server.ServerFactory;
import org.jepria.server.service.rest.EntityService;
import org.jepria.server.service.rest.EntityServiceImpl;
import org.jepria.server.service.rest.SearchService;
import org.jepria.server.service.rest.SearchServiceImpl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.function.Supplier;

public class GoodsServerFactory extends ServerFactory<GoodsDao>  {

//  private FeatureService service;
  private EntityService entityService;
  private SearchService searchService;

  @Inject
  public GoodsServerFactory(GoodsDao dao) {
    super(dao, "jdbc/ITMDS");
  }

//  public FeatureService getService() {
//    if (null  == this.service) {
//      this.service = new FeatureServiceImpl(getDao());
//    }
//    return this.service;
//  }

  /**
   * @return сервис, воплощающий логику CRUD-операций (create, get-by-id, update, delete)
   */
  public EntityService getEntityService() {
    if (null == this.entityService) {
      this.entityService = new EntityServiceImpl(getDao(), new GoodsRecordDefinition());
    }
    return this.entityService;
  }

  /**
   * @return сервис, воплощающий логику поиска объектов сущности
   */
  public SearchService getSearchService(Supplier<HttpSession> session) {
    if (null == this.searchService) {
      this.searchService = new SearchServiceImpl(getDao(), new GoodsRecordDefinition(), session);
    }
    return this.searchService;
  }

}
