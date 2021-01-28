package com.technology.jep.jepriashowcase.goods;

import org.jepria.server.service.rest.EntityService;
import org.jepria.server.service.rest.SearchService;

import javax.servlet.http.HttpSession;
import java.util.function.Supplier;

public interface GoodsServerFactory {
  EntityService getEntityService();
  
  SearchService getSearchService(Supplier<HttpSession> session);
}
