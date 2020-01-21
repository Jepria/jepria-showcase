package com.technology.jep.jepriashowcase.requestprocess.client;
 
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.REQUESTPROCESS_MODULE_ID;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.technology.jep.jepria.client.ui.JepPresenter;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.form.detail.DetailFormPresenter;
import com.technology.jep.jepria.client.ui.form.list.ListFormPresenter;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactory;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactoryImpl;
import com.technology.jep.jepria.client.ui.plain.StandardClientFactoryImpl;
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;
import com.technology.jep.jepriashowcase.requestprocess.client.ui.form.detail.RequestProcessDetailFormViewImpl;
import com.technology.jep.jepriashowcase.requestprocess.client.ui.form.list.RequestProcessListFormViewImpl;
import com.technology.jep.jepriashowcase.requestprocess.shared.record.RequestProcessRecordDefinition;
import com.technology.jep.jepriashowcase.requestprocess.shared.service.RequestProcessService;
import com.technology.jep.jepriashowcase.requestprocess.shared.service.RequestProcessServiceAsync;
 
public class RequestProcessClientFactoryImpl
  extends StandardClientFactoryImpl<PlainEventBus, RequestProcessServiceAsync> {
 
  private static final IsWidget requestProcessDetailFormView = new RequestProcessDetailFormViewImpl();
  private static final IsWidget requestProcessListFormView = new RequestProcessListFormViewImpl();
 
  public static PlainClientFactoryImpl<PlainEventBus, JepDataServiceAsync> instance = null;
 
  public RequestProcessClientFactoryImpl() {
    super(REQUESTPROCESS_MODULE_ID, RequestProcessRecordDefinition.instance);
  }
 
  static public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getInstance() {
    if(instance == null) {
      instance = GWT.create(RequestProcessClientFactoryImpl.class);
    }
    return instance;
  }
 
  @Override
  public JepPresenter<PlainEventBus, RequestProcessClientFactoryImpl> createDetailFormPresenter(Place place) {
    return new DetailFormPresenter<RequestProcessDetailFormViewImpl, PlainEventBus, RequestProcessServiceAsync, RequestProcessClientFactoryImpl>(place, this);
  }
 
  @Override
  public JepPresenter<PlainEventBus, RequestProcessClientFactoryImpl>  createListFormPresenter(Place place) {
    return new ListFormPresenter<RequestProcessListFormViewImpl, PlainEventBus, RequestProcessServiceAsync, RequestProcessClientFactoryImpl>(place, this);
  }
 
  @Override
  public IsWidget getDetailFormView() {
    return requestProcessDetailFormView;
  }
 
  @Override
  public IsWidget getListFormView() {
    return requestProcessListFormView;
  }
 
  @Override
  protected RequestProcessServiceAsync createService() {
    return GWT.create(RequestProcessService.class);
  }
}
