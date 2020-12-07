package com.technology.jep.jepriashowcase.featureprocess;

import org.jepria.server.service.rest.EntityService;
import org.jepria.server.service.rest.SearchService;

import javax.servlet.http.HttpSession;
import java.util.function.Supplier;

public interface FeatureProcessServerFactory {
  public FeatureProcessService getService();
  public EntityService getEntityService();
}
