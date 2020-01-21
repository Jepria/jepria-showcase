package com.technology.jep.jepriashowcase.search.client;

import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.SEARCH_MODULE_ID;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.technology.jep.jepria.client.ui.JepPresenter;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactory;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactoryImpl;
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;
import com.technology.jep.jepriashowcase.search.client.ui.plain.SearchModulePresenter;
import com.technology.jep.jepriashowcase.search.client.ui.plain.SearchModuleViewImpl;
import com.technology.jep.jepriashowcase.search.shared.record.SearchRecordDefinition;
import com.technology.jep.jepriashowcase.search.shared.service.SearchService;
import com.technology.jep.jepriashowcase.search.shared.service.SearchServiceAsync;

/**
 * Реализация клиентской фабрики простого модуля.
 */
public class SearchClientFactoryImpl extends PlainClientFactoryImpl<PlainEventBus, SearchServiceAsync> {

  /**
   * Поле для реализации singleton'а клиентской фабрики модуля.
   */
  public static PlainClientFactoryImpl<PlainEventBus, JepDataServiceAsync> instance = null;
  
  public SearchClientFactoryImpl() {
    super(SearchRecordDefinition.instance);
  }

  static public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getInstance() {
    if(instance == null) {
      instance = GWT.create(SearchClientFactoryImpl.class);
    }
    return instance;
  }

  @Override
  public IsWidget getModuleView() {
    if(moduleView == null) {
      moduleView = new SearchModuleViewImpl();
    }
    return moduleView;
  }

  @Override
  protected SearchServiceAsync createService() {
    return GWT.create(SearchService.class);
  }

  @Override
  public JepPresenter<PlainEventBus, SearchClientFactoryImpl> createPlainModulePresenter(Place place) {
    return new SearchModulePresenter(SEARCH_MODULE_ID, place, this);
  }
  
}
