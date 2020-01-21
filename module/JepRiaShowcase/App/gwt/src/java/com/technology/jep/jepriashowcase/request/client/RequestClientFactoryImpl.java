package com.technology.jep.jepriashowcase.request.client;
 
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.REQUEST_MODULE_ID;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.technology.jep.jepria.client.ui.JepPresenter;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.form.list.ListFormPresenter;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactory;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactoryImpl;
import com.technology.jep.jepria.client.ui.plain.StandardClientFactoryImpl;
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;
import com.technology.jep.jepriashowcase.request.client.ui.form.detail.RequestDetailFormPresenter;
import com.technology.jep.jepriashowcase.request.client.ui.form.detail.RequestDetailFormViewImpl;
import com.technology.jep.jepriashowcase.request.client.ui.form.list.RequestListFormViewImpl;
import com.technology.jep.jepriashowcase.request.shared.record.RequestRecordDefinition;
import com.technology.jep.jepriashowcase.request.shared.service.RequestService;
import com.technology.jep.jepriashowcase.request.shared.service.RequestServiceAsync;
 
public class RequestClientFactoryImpl
  extends StandardClientFactoryImpl<PlainEventBus, RequestServiceAsync> {
 
  private static final IsWidget requestDetailFormView = new RequestDetailFormViewImpl();
  private static final IsWidget requestListFormView = new RequestListFormViewImpl();
 
  public static PlainClientFactoryImpl<PlainEventBus, JepDataServiceAsync> instance = null;
 
  public RequestClientFactoryImpl() {
    super(REQUEST_MODULE_ID, RequestRecordDefinition.instance);
  }
 
  static public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getInstance() {
    if(instance == null) {
      instance = GWT.create(RequestClientFactoryImpl.class);
    }
    return instance;
  }
 
  @Override
  public JepPresenter<PlainEventBus, RequestClientFactoryImpl> createDetailFormPresenter(Place place) {
    return new RequestDetailFormPresenter(place, this);
  }
 
  @Override
  public JepPresenter<PlainEventBus, RequestClientFactoryImpl> createListFormPresenter(Place place) {
    return new ListFormPresenter<RequestListFormViewImpl, PlainEventBus, RequestServiceAsync, RequestClientFactoryImpl>(place, this);
  }
 
  @Override
  public IsWidget getDetailFormView() {
    return requestDetailFormView;
  }
 
  @Override
  public IsWidget getListFormView() {
    return requestListFormView;
  }
 
  @Override
  protected RequestServiceAsync createService() {
    return GWT.create(RequestService.class);
  }
}
