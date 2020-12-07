package com.technology.jep.jepriashowcase.goods;

import com.technology.jep.jepriashowcase.goods.dao.GoodsDao;
import org.jepria.server.ServerFactory;
import org.jepria.server.service.rest.EntityService;
import org.jepria.server.service.rest.EntityServiceImpl;
import org.jepria.server.service.rest.SearchService;
import org.jepria.server.service.rest.SearchServiceImpl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.function.Supplier;

public class GoodsServerFactoryImpl extends ServerFactory<GoodsDao> implements GoodsServerFactory {

  private EntityService entityService;
  private SearchService searchService;

  @Inject
  public GoodsServerFactoryImpl(GoodsDao dao) {
    super(dao, "jdbc/ITMDS");
  }

  /**
   * @return сервис, воплощающий логику CRUD-операций (create, get-by-id, update, delete)
   */
  @Override
  public EntityService getEntityService() {
    if (null == this.entityService) {
      this.entityService = new EntityServiceImpl(getDao(), new GoodsRecordDefinition());
    }
    return this.entityService;
  }

  /**
   * @return сервис, воплощающий логику поиска объектов сущности
   */
  @Override
  public SearchService getSearchService(Supplier<HttpSession> session) {
    if (null == this.searchService) {
      this.searchService = new SearchServiceImpl(getDao(), new GoodsRecordDefinition(), session);
    }
    return this.searchService;
  }

}
