package com.technology.jep.jepriashowcase.feature;

import java.util.function.Supplier;
import javax.servlet.http.HttpSession;
import org.jepria.server.service.rest.EntityService;
import org.jepria.server.service.rest.SearchService;

public interface FeatureServerFactory {
  public FeatureService getService();
  public EntityService getEntityService();
  public SearchService getSearchService(Supplier<HttpSession> session);
}
